package ch.vathib.aquarium.aquarium;

import ch.vathib.aquarium.living.FishType;
import ch.vathib.aquarium.living.SexualOrientation;

import java.io.*;

public class FileGestion {

    public static void saveAquarium(Aquarium aquarium) {
        try
        {
            FileOutputStream out = new FileOutputStream(Main.path + File.separator + Main.folder, false);
            ObjectOutputStream writer = new ObjectOutputStream(out);

            writer.writeObject(aquarium);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Aquarium readAquarium() {
        try {
            FileInputStream in = new FileInputStream(Main.path + File.separator + Main.folder);
            ObjectInputStream reader = new ObjectInputStream(in);

            return (Aquarium) reader.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveRound(String string) {
        try {
            File file = new File(Main.path + File.separator + "Javaquarium\\round.fish");
            file.createNewFile();

            FileWriter writer = new FileWriter(file, true);
            writer.write(string);
            writer.close();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addFish(Aquarium aquarium) {
        String ligne;
        try {

            File file = new File(Main.path + File.separator + "Javaquarium\\config.txt");

            if (file.createNewFile()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("1 algues 10 ans\n");
                fileWriter.write("10 algues 4 ans\n");
                fileWriter.write("Lin, Femelle, Merou, 1 ans\n");
                fileWriter.write("Bernard, Mâle, Thon, 17 ans\n");
                fileWriter.write("Bernadette, Femelle, Thon, 10 ans\n");

                fileWriter.close();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            while ((ligne = bufferedReader.readLine()) != null) {
                String[] arrays = ligne.split(" ");

                if (arrays[0].matches("^\\d|^\\d\\d")) {
                    aquarium.addSeaweed(Integer.parseInt(arrays[0]), Integer.parseInt(arrays[2]));
                } else {
                    String[] arrays1 = ligne.split(", ");
                    int age = Integer.parseInt(arrays1[3].split(" ")[0]);
                    aquarium.addFish(arrays1[0], arrays1[1].equalsIgnoreCase("Femelle") ? SexualOrientation.FEMALE : SexualOrientation.MALE, FishType.getByString(arrays1[2]), age);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

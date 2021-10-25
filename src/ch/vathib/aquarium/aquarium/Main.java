package ch.vathib.aquarium.aquarium;

import ch.vathib.aquarium.living.FishType;
import ch.vathib.aquarium.living.SexualOrientation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Aquarium aquarium = new Aquarium();
    public static String path;
    public static String folder;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String folder = "Javaquarium\\save.fish";

        path = System.getProperty("user.home") + File.separator + "Documents";
        File file = new File(path + File.separator + folder);

        if (file.exists() && file.isFile()) {
                System.out.println("Une sauvegarde existe, voulez vous la chargez ? (yes | no)");
                String s = scanner.next();
                if (s.equalsIgnoreCase("yes")) {
                    aquarium = FileGestion.readAquarium();
                    System.out.println("Sauvegarde chargé");
                } else if (s.equalsIgnoreCase("no")) {
                    init();
                } else {
                    System.out.println("Ecrivez yes ou no");
                    System.exit(0);
                }
        } else if (file.createNewFile()) {
            init();
        } else {
            System.out.println("La sauvegarde ne peut pas être créer");
        }

        aquarium.viewInfos();
        while (true) {
            System.out.println("Ecrivez \"pass\" pour passer le tour | \"add\" pour ajouter des poissons/algues ou \"exit\" pour quitter");
            String s = scanner.next();
            if (s.equalsIgnoreCase("pass")) {
                aquarium.passTurn();
                aquarium.viewInfos();
                System.out.println("\n===== tour" + aquarium.getRound() + " ====");
            } else if (s.equalsIgnoreCase("add")) {
                System.out.println("Tap:\n 1: Ajoutez des poissons\n 2: Ajoutez des algues");
                s = scanner.next();
                if (s.equalsIgnoreCase("1")) {
                    System.out.println("Tap:\n" +
                            "1: Merou\n" +
                            "2: Thon\n" +
                            "3: Poisson-clown\n" +
                            "4: Sole\n" +
                            "5: Bar\n" +
                            "6: Carpe");
                    FishType type = FishType.getByIndex(scanner.nextInt());
                    System.out.println("Tap: name");
                    String name = scanner.next();
                    System.out.println("Tap: \n" +
                            "1: Femelle\n" +
                            "2: Mâle");
                    SexualOrientation sexualOrientation = scanner.next().equals("1") ? SexualOrientation.FEMALE : SexualOrientation.MALE;
                    System.out.println("Tap: age");
                    int age = scanner.nextInt();

                    aquarium.addFish(name, sexualOrientation, type, age);

                    System.out.println("Poisson ajouter avec succès");
                } else if (s.equalsIgnoreCase("2")) {
                    System.out.println("Ecrivez: le nombre d'algues à ajouter");
                    int number = scanner.nextInt();
                    System.out.println("Ecrivez: l'age des algues");
                    int age = scanner.nextInt();
                    aquarium.addSeaweed(number, age);
                }
            } else if (s.equalsIgnoreCase("exit")) {
                FileGestion.saveAquarium(aquarium);
                System.exit(0);
            }
        }
    }

    public static void init() {
        FileGestion.addFish(aquarium);
        System.out.println("Aquarium initialisé");
    }
}
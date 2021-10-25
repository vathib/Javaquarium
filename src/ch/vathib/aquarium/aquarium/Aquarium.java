package ch.vathib.aquarium.aquarium;

import ch.vathib.aquarium.living.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium implements Serializable {
    private List<Fish> fishList = new ArrayList<>();
    private ArrayList<Seaweed> seaweedList = new ArrayList<>();
    private int round = 1;

    public Aquarium() {}

    public void passTurn() {
        round++;

        FileGestion.saveRound("\nLes poissons ont fains et perdent 1pv:");

        // Les poissons ont fain et perdent 1 pv
        for (Fish fish : fishList) {
            fish.hungry();
        }
        // Les seaweed grandisses et gagne 1 pv
        for (Seaweed seaweed : seaweedList) {
            seaweed.grow();
        }
        FileGestion.saveRound("\nLes algues grandises et gagne 1pv:");

        grow(fishList);
        grow(seaweedList);

        FileGestion.saveRound("\nLes poissons se repoduisent:");
        reproductionFish(fishList);
        FileGestion.saveRound("\nLes algues se repoduisent:");
        reproductionSeaweed(seaweedList);

        FileGestion.saveRound("\nLes poissons mangent:");
        eating();

        FileGestion.saveRound("\nSuppression des morts:");
        deleteDie(seaweedList);
        deleteDie(fishList);
    }

    public void eating() {
        Random r = new Random();

        for (Fish fish : fishList) {
            if (fish.getHealth() <= 5) {
                if (fish.getType().isCarnivorous()) {
                    if (fishList.size() <= 1) return;
                    int i = r.nextInt(fishList.size());
                    fish.eat(fishList.get(i));
                } else {
                    if (seaweedList.size() < 1) return;
                    int i = r.nextInt(seaweedList.size());
                    fish.eat(seaweedList.get(i));
                }
            }
        }
    }

    public void reproductionFish(List<Fish> fishList1) {
        List<Fish> newFishList = new ArrayList<>();
        Random r = new Random();

        for (Fish fish : fishList1) {
            int i = r.nextInt(fishList1.size());
            Fish newFish = null;
            newFish = (Fish) fish.reproduct(fishList1.get(i));
            if (newFish != null) newFishList.add(newFish);
        }
        if (!newFishList.isEmpty()) {
            this.fishList.addAll(newFishList);
        }
    }

    public void reproductionSeaweed(List<Seaweed> seaweedList1) {
        List<Seaweed> newSeaweedList = new ArrayList<>();
        Random r = new Random();

        for (Seaweed seaweed : seaweedList1) {
            int i = r.nextInt(seaweedList1.size());
            Seaweed newSeaweed = null;
            newSeaweed = (Seaweed) seaweed.reproduct(seaweedList1.get(i));
            if (newSeaweed != null) newSeaweedList.add(newSeaweed);
        }
        if (!newSeaweedList.isEmpty()) {
            FileGestion.saveRound(newSeaweedList.size() + " algues naissent");
            this.seaweedList.addAll(newSeaweedList);
        }
    }

    public void deleteDie(List<? extends Alive> list) {
        List<Alive> dieList = new ArrayList<>();
        for (Alive alive : list) {
            if (alive.getHealth() <= 0 || alive.getAge() >= 20) {
                dieList.add(alive);
            }
        }

        if (dieList.isEmpty()) return;
        FileGestion.saveRound(dieList.size() + (list.get(0) instanceof Fish ? " poissons" : " algues") + " sont mort");
        list.removeAll(dieList);
    }

    public void changeSex(List<Fish> list) {
        for (Fish fish : list) {
            if (fish.getAge() == 10) {
                fish.changeSex();
            }
        }
    }

    public void grow(List<? extends Alive> alives) {
        for (Alive alive : alives) {
            alive.setAge(alive.getAge() + 1);
        }
    }

    public void viewInfos() {

        System.out.printf("======================TOUR %s====================== \n", round);
        System.out.println("Nombres d'alge: " + seaweedList.size()+"\n");
        for (Fish fish : fishList) {
            // Affichage fish
            String s = String.format("Nom: %-13s | Sexe: %-8s | Race: %-13s | Health: %-3s | Age: %-3s\n", fish.getName(), fish.getSex(), fish.getType().getName(), fish.getHealth(), fish.getAge());
            System.out.printf(s);
        }
        System.out.print("\n------------------------------------------------\n\n");
    }

    public void addFish(String name, SexualOrientation sex, FishType type, int age) {
        fishList.add(new Fish(name, sex, type, age));
    }

    public void addSeaweed() {
        seaweedList.add(new Seaweed());
    }

    public void addSeaweed(int number) {
        for (int i = 0; i < number; i++) {
            seaweedList.add(new Seaweed());
        }
    }

    public void addSeaweed(int number, int age) {
        for (int i = 0; i < number; i++) {
            seaweedList.add(new Seaweed(age));
        }
    }

    public int getRound() {
        return round;
    }

    public ArrayList<Seaweed> getSeaweedList() {
        return seaweedList;
    }

    public List<Fish> getFishList() {
        return fishList;
    }
}
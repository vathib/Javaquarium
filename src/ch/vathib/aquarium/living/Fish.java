package ch.vathib.aquarium.living;

import ch.vathib.aquarium.aquarium.FileGestion;

import java.io.Serializable;
import java.util.Random;

public class Fish extends Alive implements Serializable {

    private SexualOrientation sex;
    private final FishType type;

    public Fish(String name, SexualOrientation sex, FishType type, int age) {
        super(age, name);
        this.sex = sex;
        this.type = type;
    }

    public void eat(Fish fish) {
        if (fish.getType() == this.getType()) return;
        this.health =+ 5;
        fish.setHealth(fish.getHealth() - 4);

        if (fish.getHealth() <= 0) {
            FileGestion.saveRound(fish.getName() + " meurt manger par " + this.getName());
        }
    }

    public void eat(Seaweed seaweed) {
        this.health =+ 3;
        seaweed.setHealth(seaweed.getHealth() - 2);
    }

    @Override
    public Alive reproduct(Alive alive) {
        if (!(alive instanceof Fish)) return null;

        if ((this.type.getSex() == Sex.MONO_SEXUAL ||  this.type.getSex() == Sex.HERMAPHRODITE) && this.sex != ((Fish) alive).sex && this.getType() == ((Fish) alive).getType()) {
            return reproduction();
        }
        if (this.type.getSex() == Sex.HERMAPHRODITE_OPPORTUNISTE && this.getType() == ((Fish) alive).getType() && this != alive) {
            if (this.sex == ((Fish) alive).sex) {
                this.sex = this.sex == SexualOrientation.FEMALE ? SexualOrientation.MALE : SexualOrientation.FEMALE;
            }
            return reproduction();
        }
        return null;
    }

    private Alive reproduction() {
        Random r = new Random();
        int i = r.nextInt(2);
        SexualOrientation sex = i < 1 ? SexualOrientation.FEMALE : SexualOrientation.MALE;
        FileGestion.saveRound(this.getName() + " donne naissance à " + this.name + " Junior");
        return new Fish(this.name + " Junior", sex, this.type, 0);
    }

    public void changeSex() {
        this.sex = this.sex == SexualOrientation.FEMALE ? SexualOrientation.MALE : SexualOrientation.FEMALE;
    }

    public void hungry() {
        this.health--;
        if (this.health <= 0) {
            FileGestion.saveRound(this.getName() + " meurt de faim");
        }
    }

    public boolean hasHungree() {
        return health <= 5;
    }

    public String getName() {
        return this.name;
    }

    public SexualOrientation getSex() {
        return this.sex;
    }

    public FishType getType() {
        return type;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAge() {
        return age;
    }

    public void grow() {
        this.age++;
    }
}
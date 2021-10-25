package ch.vathib.aquarium.living;

import java.io.Serializable;

public abstract class Alive implements Serializable {

    protected int age;
    protected int health;
    protected String name;
    protected final int DIE_AGE = 20;

    public Alive(int age, String name) {
        this.age = age;
        this.health = 10;
        this.name = name;
    }

    public void grow() {
        this.age++;
    }

    public boolean isDie() {
        return this.health <= 0 || this.age > DIE_AGE;
    }

    public abstract Alive reproduct(Alive alive);

    public void hungry() {
        this.health--;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

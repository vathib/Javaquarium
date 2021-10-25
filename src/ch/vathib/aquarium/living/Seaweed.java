package ch.vathib.aquarium.living;

public class Seaweed extends Alive{

    public Seaweed() {
        super(0, "Seaweed");
    }

    public Seaweed(int health, boolean t) {
        super(0, "Seaweed");
        this.health = health;
    }

    public Seaweed(int age) {
        super(age, "Seaweed");
    }

    @Override
    public Alive reproduct(Alive alive) {
        if (this.health >= 10) {
            this.health = Math.round(this.health / 2);
            return new Seaweed(this.health / 2, true);
        }
        return null;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAge() {
        return age;
    }

    public void grow() {
        health++;
    }
}
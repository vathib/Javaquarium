package ch.vathib.aquarium.living;

public enum FishType {
    MEROU("carnivorous", "Merou", Sex.HERMAPHRODITE, 1),
    THON("carnivorous", "Thon", Sex.MONO_SEXUAL, 2),
    POISON_CLOWN("carnivorous", "Poisson-clown", Sex.HERMAPHRODITE_OPPORTUNISTE, 3),
    SOLE("herbivorous", "Sole", Sex.HERMAPHRODITE_OPPORTUNISTE, 4),
    BAR("herbivorous", "Bar", Sex.HERMAPHRODITE, 5),
    CARPE("herbivorous", "Carpe", Sex.MONO_SEXUAL, 6);

    private final String alimentation;
    private final String name;
    private final Sex sex;
    private final int index;

    FishType(String alimentation, String name, Sex sex, int index) {
        this.alimentation = alimentation;
        this.name = name;
        this.sex = sex;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public static FishType getByString(String string) {
        for (FishType f : FishType.values()) {
            if (f.name.equalsIgnoreCase(string)) {
                return f;
            }
        }
        return null;
    }

    public static FishType getByIndex(int index) {
        for (FishType f : FishType.values()) {
            if (f.index == index) {
                return f;
            }
        }
        return null;
    }

    public boolean isCarnivorous() {
        if (alimentation.equals("carnivorous")) {
            return true;
        } else {
            return false;
        }
    }
}
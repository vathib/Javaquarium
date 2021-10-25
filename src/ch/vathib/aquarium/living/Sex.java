package ch.vathib.aquarium.living;

public enum Sex {
    MONO_SEXUAL("mono-sexuel"),
    HERMAPHRODITE("hermaphrodite"),
    HERMAPHRODITE_OPPORTUNISTE("hermaphrodite opportuniste");

    private final String name;

    Sex(String name) {
        this.name = name;
    }
}
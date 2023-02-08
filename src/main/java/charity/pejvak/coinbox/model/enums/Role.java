package charity.pejvak.coinbox.model.enums;

public enum Role {
    USER("user"),ADMIN("admin");
    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    private String getName() {
        return name;
    }
}

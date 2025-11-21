package voltik.qpa.czopekhookreborn.feature.module;

public enum Categories {
    MISC("Misc");

    private final String displayName;
    Categories(String displayName) {
        this.displayName = displayName;

    }

    public String getDisplayName() {
        return displayName;
    }
}

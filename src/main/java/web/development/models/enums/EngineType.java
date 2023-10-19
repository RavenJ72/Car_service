package web.development.models.enums;

public enum EngineType {
    GASOLINE(1),
    DIESEL(2),
    ELECTRIC(3),
    HYBRID(4);


    private final int engineTypeCode;

    EngineType(int engineTypeCode) {
        this.engineTypeCode = engineTypeCode;
    }

    public int getEngineTypeCode() {
        return engineTypeCode;
    }
}

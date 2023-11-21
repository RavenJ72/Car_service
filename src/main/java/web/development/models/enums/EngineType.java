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

    public static Integer getEngineTypeCodeFromString(String engineTypeName) {
        for (EngineType engineType : EngineType.values()) {
            if (engineType.name().equalsIgnoreCase(engineTypeName)) {
                return engineType.getEngineTypeCode();
            }
        }
        return null;
    }

    public static EngineType fromEngineTypeCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (EngineType engineType : EngineType.values()) {
            if (engineType.getEngineTypeCode() == code) {
                return engineType;
            }
        }
        throw new IllegalArgumentException("No EngineType with code " + code + " found");
    }
}

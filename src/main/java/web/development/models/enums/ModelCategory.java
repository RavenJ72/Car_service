package web.development.models.enums;

public enum ModelCategory {
    CAR(1),
    BUSS(2),
    TRUCK(3),
    MOTORCYCLE(4);

    private final int modelCategoryCode;

    ModelCategory(int modelCategoryCode) {
        this.modelCategoryCode = modelCategoryCode;
    }

    public int getModelCategoryCode() {
        return modelCategoryCode;
    }

    public static Integer getModelCategoryCodeFromString(String modelCategoryName) {
        for (ModelCategory modelCategory : ModelCategory.values()) {
            if (modelCategory.name().equalsIgnoreCase(modelCategoryName)) {
                return modelCategory.getModelCategoryCode();
            }
        }
        return null;
    }

    public static ModelCategory fromModelCategoryCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ModelCategory modelCategory : ModelCategory.values()) {
            if (modelCategory.getModelCategoryCode() == code) {
                return modelCategory;
            }
        }
        throw new IllegalArgumentException("No ModelCategory with code " + code + " found");
    }
}

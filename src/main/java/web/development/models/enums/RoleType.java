package web.development.models.enums;

public enum RoleType {
    USER(1),
    ADMIN(2);

    private final int userRoleTypeCode;

    RoleType(int userRoleTypeCode) {
        this.userRoleTypeCode = userRoleTypeCode;
    }

    public int getUserRoleTypeCode() {
        return userRoleTypeCode;
    }

    public static RoleType fromString(String str) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.name().equalsIgnoreCase(str)) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Invalid RoleType: " + str);
    }

    @Override
    public String toString() {
        return this.name().toString();
    }
}

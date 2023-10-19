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
}

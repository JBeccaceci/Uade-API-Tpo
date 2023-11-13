package uade.tpo.config;

public class JWTAuthInfo {
    private String userName;
    private String userId;
    private String role;
    private String userType;

    public JWTAuthInfo(String userName, String userId, String role, String userType) {
        this.userName = userName;
        this.userId = userId;
        this.role = role;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "JWTAuthInfo{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}

package co.hblabs.aouth.models;

// POJO
public class UserRequest {
    public String email = "";
    public String firstName = "";
    public String lastName = "";
    public String password = "";

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

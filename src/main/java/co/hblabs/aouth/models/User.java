package co.hblabs.aouth.models;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

// POJO
public class User implements Cloneable {
    private String id = "";
    private String password = "";
    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private Date createdAt = null;


    public User(){
        this.id = UUID.randomUUID().toString();
        this.createdAt = Calendar.getInstance().getTime();
    }    
    
    public String getEmail() {
        return email;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public void setEmail(String email) throws Exception {
        ensureString("email", email);
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) throws Exception {
        ensureString("firstName", firstName);
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) throws Exception {
        ensureString("lastName", lastName);
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        ensureString("password", password);
        this.password = password;
    }

    public void removePassword() {
        this.password = "";
    }

    private void ensureString(String propName, String value) throws Exception {
        if (value.isEmpty() || value.equals("") || value == null) {
            String message = String.format("property %s is missing", propName);
            throw new Exception(message);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

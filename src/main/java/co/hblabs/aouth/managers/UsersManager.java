package co.hblabs.aouth.managers;

import java.util.ArrayList;
import java.util.List;

import co.hblabs.aouth.models.UserRequest;
import co.hblabs.aouth.models.User;

public class UsersManager {

    private static UsersManager instance;
    private List<User> userList = new ArrayList<User>();

    private UsersManager(){}

    public static UsersManager getInstance() {
        if (instance == null) {
            instance = new UsersManager();
        }
        return instance;
    }

    public User saveUser(UserRequest reqUser) throws Exception {
        User user = new User();

        user.setFirstName(reqUser.firstName);
        user.setLastName(reqUser.lastName);
        user.setEmail(reqUser.email);
        user.setPassword(reqUser.password);
        this.userList.add(user);

        return removeUserPassword(user);
    }

    protected User findByEmail(String email, boolean withPassword) throws Exception {
        for (User user: this.userList) {
            if (user.getEmail().equals(email)) {
                // Lo encontre
                if (!withPassword) {
                    return removeUserPassword(user);
                }
                return user;
            }
        }

        String message = String.format("user with email %s not found", email);
        throw new Exception(message);
    }

    public User findByEmailUNSAFE(String email) throws Exception {
       return this.findByEmail(email, true);
    }

    public User findByEmailSAFE(String email) throws Exception {
        return this.findByEmail(email, false);
    }

    private User removeUserPassword(User user) throws CloneNotSupportedException {
        // typescasting luego de clonado
        User clonedUser = (User) user.clone();
        clonedUser.removePassword();
        return clonedUser;
    }

}

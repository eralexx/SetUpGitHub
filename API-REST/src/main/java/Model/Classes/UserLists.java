package Model.Classes;

import java.util.ArrayList;
import java.util.List;

public class UserLists {
     public static List<User> RegisteredUsers = new ArrayList<User>();
     public static List<User> OnlineUsers = new ArrayList<User>();


    public void setRegisteredUsers(List<User> registeredUsers) {
        RegisteredUsers = registeredUsers;
    }

    public void setOnlineUsers(List<User> onlineUsers) {
        OnlineUsers = onlineUsers;
    }

    public List<User> getRegisteredUsers(){
        return this.RegisteredUsers;
    }

    public List<User> getOnlineUsers(){
        return this.OnlineUsers;
    }

    public User getUserById(int idPlayer) {
        return  RegisteredUsers.stream()
                .filter(item -> (item.getId()==idPlayer))
                .findFirst().get();
    }
}

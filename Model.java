import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Model class has a inner class, Profile, that holds all of the users data.
 * Holds friends list of user.
 * */
public class Model extends Observable
{
    private String name, status, password;
    private ImageIcon image;
    private ArrayList<String> friends;

    public Model(){
        name = ""; status = ""; password = "";
        friends = new ArrayList<>();
        image = null;
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return password; }

    public void setImage(File image) { this.image = new ImageIcon(String.valueOf(image)); }
    public ImageIcon getImage() { return image; }


    public void addFriends(String name){
        friends.add(name);
    }
    public ArrayList<String> getFriendsList(){ return friends; }
    public boolean isFriendsWith(String name){
        return friends.contains(name);
    }


    public void update(Observable o, Object arg)
    {
        setChanged();
        notifyObservers();
    }
}

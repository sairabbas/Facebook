import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    String name, status, fileName;
    BufferedImage picture;
    ArrayList<Database> friendDatabases;

    //Constructor
    public Database() {
        name = ""; status = ""; fileName = "default.png";
        friendDatabases = new ArrayList<>();
        try{
            picture = ImageIO.read(new File(fileName));
        } catch(IOException e){ System.out.println("Profile pic not found"); }
    }

    public BufferedImage getProfilePicture() {
        return picture;
    }

    public void setProfilePicture(BufferedImage newPicture) {
        picture = newPicture;
    }

    public void setName(String firstName, String lastName) {
        name = firstName + " " + lastName;
    }

    public String getName() {
        return name;
    }

    public void setStatus(String stat) {
        status = stat;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Database> getFriends() {
        return friendDatabases;
    }

    public void addFriend(Database p) {
        friendDatabases.add(p);
    }

    public void removeFriend(Database p) {
        friendDatabases.remove(p);
    }

    public String toString() {
        String line = "";

        for(Database friend : friendDatabases)
            line += friend.name + ", ";

        return "[Name: " + name
                + ", Status: " + status
                + "Friends: "+ line
                + "]";
    }

    public Database getProfile(Database p)
    {
        return p;
    }
}
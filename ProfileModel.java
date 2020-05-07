import javafx.beans.Observable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileModel extends java.util.Observable {

    String name, status, fileName;
    BufferedImage picture;
    ArrayList<ProfileModel> friendProfileModels;

    //Constructor
    public ProfileModel() {
        name = ""; status = ""; fileName = "default.png";
        friendProfileModels = new ArrayList<>();
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


    public ArrayList<ProfileModel> getFriends() {
        return friendProfileModels;
    }


    public void addFriend(ProfileModel p) {
        friendProfileModels.add(p);
    }


    public void removeFriend(ProfileModel p) {
        friendProfileModels.remove(p);
    }


    public String toString() {
        String line = "";

        for(ProfileModel friend :friendProfileModels)
            line += friend.name + ", ";

        return "[Name: " + name
                + ", Status: " + status
                + "Friends: "+ line
                + "]";
    }


    public ProfileModel getProfile(ProfileModel p)
    {
        return p;
    }

    public void update(Observable o, Object args){ }

    @Override
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}// end Profile
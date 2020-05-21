import javax.swing.*;
import java.io.File;
import java.util.Observable;

/**
 * Model class has a inner class, Profile, that holds all of the users data.
 * */
public class Model extends Observable
{
    private String name, status, password;
    private ImageIcon image;

    public Model(){
        name = ""; status = ""; password = "";
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

    public void update(Observable o, Object arg)
    {
        setChanged();
        notifyObservers();
    }
}

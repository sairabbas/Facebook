import javax.swing.*;
import java.util.Observable;

public class Model extends Observable
{
    Profile profile = new Profile();
    public class Profile
    {
        private String name, status, password;
        private ImageIcon image;
        public void setName(String name) { this.name = name; }
        public String getName() { return name; }
        public void setStatus(String status) { this.status = status; }
        public String getStatus() { return status; }
        public void setPassword(String password) { this.password = password; }
        public String getPassword() { return password; }
        public void setImage(ImageIcon image) { this.image = image; }
        public ImageIcon getImage() { return image; }
    }
    public void update(Observable o, Object arg)
    {
        setChanged();
        notifyObservers();
    }
}

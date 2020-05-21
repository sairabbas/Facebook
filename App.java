import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Daniel Tran & Sair Abbas*/
public class App
{
    public static void main(String[] args)
    {
        Manager manager = new Manager();

        //TODO: TEST CASE 1: Add Friends
        Model m1 = new Model();
        Model m2 = new Model();
        Model m3 = new Model();
        Model m4 = new Model();

        manager.addAccount("Daniel Tran", "123", new File("asd.pmg"));
        manager.addAccount("Sair Abbas", "123", new File("asd.pmg"));
        manager.addAccount("Josh Sjah", "123", new File("asd.pmg"));
        manager.addAccount("Alexis Arroyo", "123", new File("asd.pmg"));

        //Declare view object
        View view = new View();
    }
}

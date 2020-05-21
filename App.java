import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Daniel Tran & Sair Abbas*/
public class App
{
    public static void main(String[] args)
    {
        Manager manager = new Manager();

        //Declare view object
        View view = new View();

        //TODO: TEST CASE 1: Add Friends
        Model m1 = new Model();
        Model m2 = new Model();
        Model m3 = new Model();
        Model m4 = new Model();


        manager.addProfile(m1);
        manager.addProfile(m2);
        manager.addProfile(m3);
        manager.addProfile(m4);

        manager.createFriendship(m1,m2);
        manager.createFriendship(m2,m3);
    }
}

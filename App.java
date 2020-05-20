import java.io.IOException;

/**
 * @author : Daniel Tran & Sair Abbas*/
public class App
{
    public static void main(String[] args)
    {
        //TODO: TEST CASE 1: CHECK IF GRAPH WORKS FOR ONE USER
        Manager manager = new Manager();

        //Declare model object
        Model.Profile model1 = new Model();
        Model.Profile model2 = new Model();
        Model.Profile model3 = new Model();

        model1.profile.setName("Daniel Tran");
        model1.profile.setStatus("online");

        model2.profile.setName("Sair Abbas");
        model2.profile.setStatus("offline");

        model3.profile.setName("Josh Sjah");
        model3.profile.setStatus("afk");

        manager.addProfile(model1);
        manager.addProfile(model2);
        manager.addProfile(model3);

        manager.searchProfile("Daniel Tran"); //Should return Model obj1
        manager.getProfilesCount(); //Should be 3

        //Declare view object
        View view = new View();
    }
}

public class TestProfile
{
    public static void main(String[] args)
    {
        ProfileModel model = new ProfileModel();
        ProfileManager manager = new ProfileManager();
        NetworkView view = new NetworkView(model);


        javax.swing.SwingUtilities.invokeLater(view::createAndShowGUI);

    }
}


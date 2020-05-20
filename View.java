import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer
{
    //Declare and initialize model
    Model model = new Model();
    Manager manager = new Manager();
    //Declare application frame and panels
    JFrame frame;
    JPanel home;
    JPanel create;
    JPanel login;
    JPanel dashboard;
    JPanel edit;
    //Window frame size constants
    final int WINDOW_WIDTH = 700;
    final int WINDOW_HEIGHT = 450;

    //Display application home page
    public View()
    {
        Home();
        Create();
        Login();
        Dashboard(new Model());
        model.addObserver(this);
        frame = new JFrame();
        frame.setTitle("MockFB");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(dashboard);
        //frame.pack();
        frame.setVisible(true);
    }

    //Construct application home page
    public void Home()
    {
        Create();
        Login();

        home = new JPanel();
        home.setLayout(null);

        JLabel image = new JLabel();
        image.setBounds(287,0,125,125);
        ImageIcon logo = new ImageIcon("logo.jpg");
        Image img = logo.getImage();
        Image sized = img.getScaledInstance(image.getWidth(),image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon sizedImage = new ImageIcon(sized);
        image.setIcon(sizedImage);
        home.add(image);

        JLabel greetingLabel = new JLabel("Welcome to MockFB!");
        greetingLabel.setBounds(285,135,215,20);
        home.add(greetingLabel);

        JLabel createLabel = new JLabel("Create A New Account");
        createLabel.setBounds(180,175,140,20);
        home.add(createLabel);

        JButton createButton = new JButton("Create");
        createButton.setBounds(200,205,100,20);
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(create);
                frame.setVisible(true);
            }
        });
        home.add(createButton);

        JLabel loginLabel = new JLabel("Log Into Account");
        loginLabel.setBounds(380,175,140,20);
        home.add(loginLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(385,205,100,20);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Login();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(login);
                frame.setVisible(true);
            }
        });
        home.add(loginButton);
    }

    //Construct application create profile page
    public void Create()
    {
        create = new JPanel();
        create.setLayout(null);

        JLabel greetingLabel = new JLabel("Create A New Account");
        greetingLabel.setBounds(285,25,215,20);
        create.add(greetingLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(240,80,80,25);
        create.add(nameLabel);

        JTextField nameTextField = new JTextField(10);
        nameTextField.setBounds(283,80,165,25);
        create.add(nameTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(218,120,80,25);
        create.add(passwordLabel);

        JTextField passwordTextField = new JTextField(10);
        passwordTextField.setBounds(283,120,165,25);
        create.add(passwordTextField);

        JLabel imageLabel = new JLabel("Select Profile Image:");
        imageLabel.setBounds(153, 160, 127, 25);
        create.add(imageLabel);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setBounds(290, 151, 300, 275);
        create.add(fileChooser);

        JButton createButton = new JButton("Create");
        createButton.setBounds(188, 200, 100, 25);
        createButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //model.setName(nameTextField.getText());
                //model.setPassword(passwordTextField.getText());
                manager.addAccount(nameTextField.getText(),
                        passwordTextField.getText(),
                        fileChooser.getSelectedFile());
                frame.getContentPane().removeAll();
                Login();
                frame.add(login);
                frame.setVisible(true);
            }
        });
        create.add(createButton);

        JButton previousButton = new JButton("Previous...");
        previousButton.setBounds(50, 350, 100, 25);
        previousButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Home();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(home);
                frame.setVisible(true);
            }
        });
        create.add(previousButton);
    }

    //Construct application login page
    public void Login()
    {
        login = new JPanel();
        login.setLayout(null);

        JLabel greetingLabel = new JLabel("Log Into Account");
        greetingLabel.setBounds(285,25,215,20);
        login.add(greetingLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(240,100,80,25);
        login.add(nameLabel);

        JTextField nameTextField = new JTextField(10);
        nameTextField.setBounds(283,100,165,25);
        login.add(nameTextField);

        JLabel imageLabel = new JLabel("Password:");
        imageLabel.setBounds(218, 140, 127, 25);
        login.add(imageLabel);

        JTextField passwordTextField = new JTextField(10);
        passwordTextField.setBounds(283,140,165,25);
        login.add(passwordTextField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(286, 180, 100, 25);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = nameTextField.getText();
                if(manager.login(name, passwordTextField.getText()))
                {
                    Model currentUser = manager.searchProfile(name);
                    Dashboard(currentUser);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(dashboard);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        login.add(loginButton);

        JButton previousButton = new JButton("Previous...");
        previousButton.setBounds(50, 350, 100, 25);
        previousButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Home();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(home);
                frame.setVisible(true);
            }
        });
        login.add(previousButton);
    }


    JButton button;
    /**
     * Dashboard takes user's current info and displays
     * name, status, and friends list
     * @param user the current user
     * */
    public void Dashboard(Model user)
    {
        dashboard = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Resize Profile Picture
        JLabel image = new JLabel();
        image.setBounds(0,0,200,200);
        ImageIcon logo = new ImageIcon("logo.jpg");
        Image img = logo.getImage();
        Image sized = img.getScaledInstance(image.getWidth(),image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon sizedImage = new ImageIcon(sized);
        image.setIcon(sizedImage);

        //Profile picture
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(10,10,0,10);
        dashboard.add(image, c);

        //Name
        c.insets = new Insets(225,10,0,10);
        dashboard.add(new JLabel("Name: Sair Abbas"), c);

        //Status
        c.insets = new Insets(250,10,0,10);
        dashboard.add(new JLabel("Status: Online"), c);

        //Friends
        c.insets = new Insets(275,10,0,10);
        dashboard.add(new JLabel("Friends: Daniel Tran"), c);

        //Edit
        c.insets = new Insets(300,10,0,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        JButton editButton = new JButton("Edit Profile");
        editButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Edit();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(edit);
                frame.setVisible(true);
            }
        });
        dashboard.add(editButton, c);

        //Logout
        c.insets = new Insets(335,10,0,10);
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Home();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(home);
                frame.setVisible(true);
            }
        });
        dashboard.add(logoutButton, c);

        //Search Bar
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10,10,0,10);
        dashboard.add(new JTextField(12), c);
        c.insets = new Insets(35,10,0,10);
        dashboard.add(new JButton("Search Name"), c);

        //Feed
        int topImage = 73;
        int topInfo = 92;
        int topAdd = 105;
        c.insets = new Insets(topImage,10,0,10);
        for (int i = 0; i < 3; i++)
        {
            JLabel image1 = new JLabel();
            image1.setBounds(0,0,95,95);
            ImageIcon logo1 = new ImageIcon("logo.jpg");
            Image img1 = logo1.getImage();
            Image sized1 = img1.getScaledInstance(image1.getWidth(),image1.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon sizedImage1 = new ImageIcon(sized1);
            image1.setIcon(sizedImage1);
            dashboard.add(image1, c);
            JLabel nameLabel = new JLabel("Name: Daniel Tran");
            JLabel statusLabel = new JLabel("Status: Online");
            JLabel friendsLabel = new JLabel("Friends: Sair Abbas");
            c.insets = new Insets(topInfo ,125,0,10);
            dashboard.add(nameLabel, c);
            topInfo = topInfo + 20;
            c.insets = new Insets(topInfo ,125,0,10);
            dashboard.add(statusLabel, c);
            topInfo = topInfo + 20;
            c.insets = new Insets(topInfo ,125,0,10);
            dashboard.add(friendsLabel, c);
            JButton addButton = new JButton("+ Add Friend");
            c.insets = new Insets(topAdd ,260,0,10);
            dashboard.add(addButton, c);
            topImage = topImage + 100;
            c.insets = new Insets(topImage ,10,0,10);
            topInfo = topInfo + 61;
            topAdd = topAdd + 102;
        }
    }
    //Edit profile application page
    public void Edit()
    {

    }

    @Override
    public void update(Observable o, Object arg)
    {
        frame.revalidate();
        frame.repaint();
    }
}
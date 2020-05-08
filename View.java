import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class View{

    private Database model;
    private JPanel mainPanel;
    private JPanel friendListPanel; //A panel to hold friend's list

    /**
     * Constructor builds the panels and labels, has database in order to display.
     * Runs frame when object is created.
     * @param model holds the data
     * */
    public View(Database model){
        this.model = model;
        int WINDOW_WIDTH = 780;
        int WINDOW_HEIGHT = 400;

        //Create frame that takes button method
        JFrame frame = new HandleActionEventsForJButton();

        //Build friend list panel
        JLabel label = new JLabel("Friends List");
        buildFriendListPanel();

        //Box for friend list
        Box bv = Box.createVerticalBox();
        bv.add(label);
        bv.add(friendListPanel);

        //Add components to frame
        frame.add(bv, BorderLayout.EAST);
        frame.add(mainPanel, BorderLayout.CENTER);

        //Display the window
        frame.pack();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void LoginView() {
        final int WINDOW_WIDTH = 750;
        final int WINDOW_HEIGHT = 750;
        //Create a login window
        JFrame loginWindow = new JFrame();
        //Set the title
        loginWindow.setTitle("MockFB");
        //Set the size of the window
        loginWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //Specify what when the close button is clicked
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create login instructions label
        JLabel instructionLabel = new JLabel("Create A Profile To Join Network");
        instructionLabel.setBounds(270,150,215,20);
        //Create name label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(270,200,80,25);
        //Create login text field 10 characters wide
        JTextField nameTextField = new JTextField(10);
        nameTextField.setBounds(308,200,165,25);
        //Create image label
        JLabel imageLabel = new JLabel("Select Image:");
        imageLabel.setBounds(270, 228, 175, 25);
        //Create login button with caption
        JButton joinButton = new JButton("Join");
        joinButton.setBounds(270, 275, 100, 25);
        //Create select image file button
        JFileChooser fileButton = new JFileChooser();
        fileButton.setBounds(370, 228, 700, 700);
        //Create JPanel object to reference labels
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        //Add labels to panel
        loginPanel.add(instructionLabel);
        loginPanel.add(nameLabel);
        loginPanel.add(nameTextField);
        loginPanel.add(imageLabel);
        loginPanel.add(joinButton);
        loginPanel.add(fileButton);
        //Add panel to login window
        loginWindow.add(loginPanel);
        //Display the login window
        loginWindow.setVisible(true);
    }

    /**
     * Displays friend's info and their relationships. Has search field
     * */
    public void buildMainPanel(){
        //mainPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Search:");
        JTextField searchText = new JTextField(10);

        //Match label with text
        searchLabel.setLabelFor(searchText);

        //Box for search
        Box bh = Box.createHorizontalBox();
        bh.add(searchLabel);
        bh.add(searchText);
        mainPanel.add(bh, BorderLayout.SOUTH);
    }

    /**
     * Shows the friends list and aligns it downward
     * */
    public void buildFriendListPanel(){
        friendListPanel = new JPanel();
        mainPanel = new JPanel();
        buildFriends(); //Gets a sample of friends
        ArrayList<Database> f = model.getFriends();
        //TODO:Make every friend a button
        Box bv = Box.createVerticalBox();       //Create box for friend list
        Box bv2 = Box.createVerticalBox();      //Create box friend's friend list
        JButton button;                         //Button for friend name
        for(int i = 0; i < f.size(); i++){
            button = new JButton(f.get(i).getName()); //A button with a name
            bv.add(button);                                   //Adds button vertically
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                        //Button pressed gives user info
                    String action = e.getActionCommand();
                    for(int i = 0; i < model.friendDatabases.size(); i++){          //Loops thru whole database (IN PROGRESS)
                        if(action.equals(model.friendDatabases.get(i).getName())){  //Check if button name == a name in database
                            Database d = model.getProfile(model.friendDatabases.get(i)); //Get Profile object
                            JPanel friendProfile = new JPanel();                    //Create panel
                            JLabel name = new JLabel(d.getName());                  //Create name label
                            JLabel status = new JLabel(d.getStatus());              //Create status label

                            //TESTING
                            System.out.println("Friend: " + d.getName());

                            for(int j = 0; j < d.friendDatabases.size(); j++){      //Get friend's friend list
                                JButton fButton = new JButton(d.friendDatabases.get(j).getName()); //Button for friend's friend name
                                bv2.add(fButton);
                                //TESTING
                                System.out.println("Friend of Friend:" + d.friendDatabases.get(i).getName());
                            }
                            mainPanel.add(name);
                            mainPanel.add(status);
                        }
                    }
                }
            });
        }

        //TODO:When you click on a friend, show their info
        //Add buttons to panel
        friendListPanel.add(bv);
        mainPanel.add(bv2);
    }

    public void buildFriends(){
        ArrayList<Database> friends = new ArrayList<>();

        //Create friends
        for(int i = 0; i < 5; i++) {
            friends.add(new Database());
            friends.get(i).setName("Daniel", "Tran #" + i);
            friends.get(i).setStatus("online");
        }

        //Add friends of Daniel Tran #1
        model.addFriend(friends.get(1)); //DT0, DT1
        model.addFriend(friends.get(2)); //DT0, DT2
    }

    public class HandleActionEventsForJButton extends JFrame implements ActionListener{

        HandleActionEventsForJButton() {
            //Set flow layout for the frame
            this.getContentPane().setLayout(new BorderLayout(10, 5)); //hGap, vGap
            //buildUserPanel();

            //Build the labels
            JLabel firstName = new JLabel("First Name: ");
            JLabel lastName = new JLabel("Last Name: ");
            JLabel status = new JLabel("Status: ");

            //Build the profile pic
            ImageIcon img = new ImageIcon(model.getProfilePicture());
            Image scaleImg = img.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT);
            ImageIcon newImg = new ImageIcon(scaleImg);
            JLabel image = new JLabel(newImg);

            //Build the buttons
            JButton createButton = new JButton("Create Profile");
            JButton editButton = new JButton("Edit Profile");
            JButton addButton = new JButton("Add Friend");
            JButton joinButton = new JButton("Join Network");
            JButton leaveButton = new JButton("Leave Network");

            //Align the buttons vertically
            Box bv = Box.createVerticalBox();
            bv.add(createButton);
            bv.add(editButton);
            bv.add(addButton);
            bv.add(joinButton);
            bv.add(leaveButton);
            bv.add(image);
            bv.add(firstName);
            bv.add(lastName);
            bv.add(status);

            //Set action listeners for buttons
            createButton.addActionListener(this);
            editButton.addActionListener(this);
            addButton.addActionListener(this);
            joinButton.addActionListener(this);
            leaveButton.addActionListener(this);

            //Add buttons to the frame
            add(bv, BorderLayout.WEST); //Buttons stored in bv align down and to the left
        }

        /**
         * When a button pressed, calls a method/class object to run a new window or page.
         * @param ae the act of clicking button
         * */
        @Override
        public void actionPerformed(ActionEvent ae) {
            String action = ae.getActionCommand();

            //Opens a new Frame to enter user info
            if (action.equals("Create Profile")) {
                new ProfileForm(model);
            }
            else if (action.equals("Edit Profile")) {
                System.out.println("Edit Button pressed!");
            }
            else if (action.equals("Add Friend")) {
                System.out.println("Add Button pressed!");
            }
            else if (action.equals("Join Network")) {
                LoginView();
            }
            else if (action.equals("Leave Network")) {
                System.out.println("Leave Button pressed!");
            }
        }
    }

}


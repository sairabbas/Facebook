import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class NetworkView implements Observer {

    private ProfileModel model;

    public NetworkView(ProfileModel model){
        this.model = model;
        model.addObserver(this);
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

    public void createAndShowGUI(){
        JFrame frame = new HandleActionEventsForJButton();

        JLabel search = new JLabel("Search:");
        ImageIcon img = new ImageIcon(model.getProfilePicture());
        Image scaleImg = img.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT);
        ImageIcon newImg = new ImageIcon(scaleImg);
        JLabel image = new JLabel(newImg);
        //Set up text fields
        JTextField searchText = new JTextField("", 10);
        search.setLabelFor(searchText);

        //Add text and image components to frame
        frame.add(search);
        frame.getContentPane().add(searchText);
        frame.getContentPane().add(image);
        frame.setPreferredSize(new java.awt.Dimension(50,50));

        //Display the window
        frame.pack();
        frame.setSize(780,400);
        frame.setVisible(true);

    }

    public class HandleActionEventsForJButton extends JFrame implements ActionListener{

        public HandleActionEventsForJButton() {

            // set flow layout for the frame
            this.getContentPane().setLayout(new FlowLayout());

            JButton createButton = new JButton("Create Profile");
            JButton editButton = new JButton("Edit Profile");
            JButton addButton = new JButton("Add Friend");
            JButton joinButton = new JButton("Join Network");
            JButton leaveButton = new JButton("Leave Network");

            //set action listeners for buttons
            createButton.addActionListener(this);
            editButton.addActionListener(this);
            addButton.addActionListener(this);
            joinButton.addActionListener(this);
            leaveButton.addActionListener(this);

            //add buttons to the frame
            add(createButton);
            add(editButton);
            add(addButton);
            add(joinButton);
            add(leaveButton);

        }

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
                System.out.println("Join Button pressed!");
            }
            else if (action.equals("Leave Network")) {
                System.out.println("Leave Button pressed!");
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("Network Updated.");
    }
}


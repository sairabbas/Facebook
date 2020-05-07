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


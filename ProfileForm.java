import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileForm{

    Database model;
    JFrame f;
    JPanel panel;

    /**
     * Constructor has essential frame components and is what runs the window
     * @param model the object that stores all the data
     * */
    public ProfileForm(Database model){
        this.model = model;

        JFrame f = new JFrame("Create Profile");
        panel = new JPanel(new SpringLayout());

        JLabel firstName = new JLabel("First Name: ");
        JLabel lastName = new JLabel("Last Name: ");
        JLabel stat = new JLabel("Status: ");

        JTextField first = new JTextField(10);
        JTextField last = new JTextField(10);
        JTextField status = new JTextField(10);

        //Decorate windows
        JFrame.setDefaultLookAndFeelDecorated(true);
        f.setContentPane(panel);
        panel.setOpaque(true);

        //Add text and labels
        panel.add(firstName);
        firstName.setLabelFor(firstName);
        panel.add(first);
        panel.add(lastName);
        lastName.setLabelFor(lastName);
        panel.add(last);
        panel.add(stat);
        stat.setLabelFor(status);
        panel.add(status);

        //Lay out the panel
        SpringUtilities.makeCompactGrid(panel,
                3, 2,          //rows, cols
                6, 6,      //initX, initY
                6, 6);       //xPad, yPad

        //Make button and give it action listener
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = e.getActionCommand();

                if(action.equals("Submit")){
                    String fn = first.getText();
                    String ln = last.getText();
                    String s = status.getText();

                    model.setName(fn, ln);
                    model.setStatus(s);
                    System.out.println("Name: " + model.getName()
                            + ", Status: " + model.getStatus());
                }
            }
        });
        f.getContentPane().add(button);

        //Display window and set size
        f.pack();

        f.setSize(400,250);
        f.setVisible(true);
    }

}

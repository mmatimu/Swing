package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class PassTest extends JPanel implements ActionListener {

	private static String OK = "ok";

	private static String HELP = "help";

	private JFrame FrameControl; //needed for dialogs

	private JPasswordField FieldPassword;

	public PassTest(JFrame f) {
		//This is the default FlowLayout.
		FrameControl = f;

		FieldPassword = new JPasswordField(10);

		FieldPassword.setActionCommand(OK);

		FieldPassword.addActionListener(this);

		JLabel tag= new JLabel("Enter Password: ");

		tag.setLabelFor(FieldPassword);

		JComponent buttonOverlay = createButtonPanel();

		JPanel txtOverlay = new JPanel(new FlowLayout(FlowLayout.TRAILING));

		txtOverlay.add(tag);

		txtOverlay.add(FieldPassword);

		add(txtOverlay);

		add(buttonOverlay);
	}
	protected JComponent createButtonPanel() {

		JPanel x = new JPanel(new GridLayout(0,1));

		JButton CBttn = new JButton("OK");

		JButton HBttn = new JButton("Help");

		CBttn.setActionCommand(OK);

		HBttn.setActionCommand(HELP);

		CBttn.addActionListener(this);

		HBttn.addActionListener(this);

		x.add(CBttn);

		x.add(HBttn);

		return x;
	}
	public void actionPerformed(ActionEvent e) {

		String cmprompt = e.getActionCommand();

		if (OK.equals(cmprompt)) { 

			char[] input = FieldPassword.getPassword();

			if (PassCheck(input)) {

				JOptionPane.showMessageDialog(FrameControl,

						"Congrats! Your password is correct.");

			} else {
				JOptionPane.showMessageDialog(FrameControl,

						"Invalid password. Please Try again.",

						"Error Message",

						JOptionPane.ERROR_MESSAGE);
			}

			Arrays.fill(input, '0');

			FieldPassword.selectAll();

			resetFocus();

		} else { 
			JOptionPane.showMessageDialog(FrameControl,

					"Search source code for the string \"correctPassword\" in order to get the password.");
		}
	}
	//Compares the passed in array against the correct password.
	private static boolean PassCheck(char[] Enter) {

		boolean Pass = true;

		char[] RightPass = { 'b', 'u', 'g', 'a', 'b', 'o', 'o' };

		if (Enter.length != RightPass.length) {

			Pass = false;

		} else {

			Pass = Arrays.equals (Enter, RightPass);

		}

		Arrays.fill(RightPass,'0');

		return Pass;
	}

	protected void resetFocus() {

		FieldPassword.requestFocusInWindow();
	}
	//Creates the GUI and Displays it on screen.
	private static void DisplayGUI() {

		JFrame boarder = new JFrame("Identification Authenticator");

		boarder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final PassTest newContentPane = new PassTest(boarder);

		newContentPane.setOpaque(true); 

		boarder.setContentPane(newContentPane);

		boarder.addWindowListener(new WindowAdapter() {

			public void windowActivated(WindowEvent e) {

				newContentPane.resetFocus();
			}
		});
		//Show the Window
		boarder.pack();

		boarder.setVisible(true);
	}
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				UIManager.put("swing.boldMetal", Boolean.FALSE);

				DisplayGUI();
			}
		});
	}
}


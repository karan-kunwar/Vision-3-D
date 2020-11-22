package renderer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.TitledBorder;

public class welcome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome window = new welcome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// Tips by Anii:
	// Frame.dispose
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] args= new String[3];
		args[0]="hi";
		
		//frame.setUndecorated(true);
        //frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        frame.setVisible(true);
        
        
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected= comboBox.getSelectedItem().toString();
				
			}
		});
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cube", "Diamond", "Pyramid", "Octahedron","Icosahedron","Dodecahedron"}));
		
		
		comboBox.setBounds(200, 200, 200, 24);
		frame.getContentPane().add(comboBox);
		
		JButton btnHello = new JButton("Manual Mode");
		btnHello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					args[1]=comboBox.getSelectedItem().toString();
					args[2]="true";
					Display.main(args);
				}catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		
		btnHello.setBounds(200, 250, 200, 25);
		frame.getContentPane().add(btnHello);
		
		JButton btnNewButton = new JButton("Free Rotation Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					args[1]=comboBox.getSelectedItem().toString();
					args[2]="false";
					Display.main(args);
				}catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(200, 300, 200, 25);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(24, 57, 178, -39);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel layeredPane = new JPanel();
		layeredPane.setBounds(5, 17, 168, -61);
		panel.add(layeredPane);
		layeredPane.setLayout(null);
			
//		TRIED TO ADD IMAGE - KK (not working)
//		JLabel label = new JLabel();
//        label.setIcon(new ImageIcon("applelogo.png"));// your image here
//        panel.add(label);
//        frame.setLocationRelativeTo(null);
//        frame.pack();
//        frame.setVisible(true);
	}
}

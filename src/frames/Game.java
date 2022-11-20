package frames;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import classes.Context;
import classes.HandleSettingsFile;
import panels.HomePanel;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;

	private static Container container;
	private static final Context context = new Context();
 
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Game frame = new Game();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 720);
		setLocationRelativeTo(null);
		setTitle("Arty Agent");
	    setResizable(false);

	    ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\favicon.png");
		setIconImage(icon.getImage());

		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(0, 0, 0));
		
		container = this.getContentPane();
		
		HandleSettingsFile.writeFileIfNotExists();

		HomePanel homePanel = HomePanel.getInstance();
		homePanel.setBounds(0, 0, 600, 720);
		contentPane.add(homePanel);		
	}
	
	public static Container getContainer() {
		return container;
	}

	public static Context getContext() {
		return context;
	}
}

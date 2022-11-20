package panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import classes.Context;
import classes.HandleSettingsFile;
import classes.StartState;
import frames.Game;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JLabel labelHomeScore;
	private static HomePanel instance;
	
	public static HomePanel getInstance() {
		if (instance == null) {
			instance = new HomePanel();
		}
		
		return instance;
	}
	
	private HomePanel() {
		setLayout(null);
		setBackground(new Color(0, 0, 0));
		
		Border border = BorderFactory.createLineBorder(Color.WHITE, 7);
		Cursor cursorHand = new Cursor(Cursor.HAND_CURSOR);
		Cursor cursorDefault = new Cursor(Cursor.DEFAULT_CURSOR);
 		
		labelHomeScore = new JLabel();
		labelHomeScore.setHorizontalAlignment(SwingConstants.CENTER);
		labelHomeScore.setForeground(Color.WHITE);
		labelHomeScore.setFont(new Font("Arial", Font.BOLD, 15));
		labelHomeScore.setBounds(190, 10, 200, 30);
		add(labelHomeScore);
		
		JLabel labelTitle = new JLabel("ARTY AGENT");
		labelTitle.setForeground(Color.WHITE);
		labelTitle.setFont(new Font("Segoe Print", Font.BOLD, 48));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(98, 0, 382, 181);
		add(labelTitle);
		
		JLabel labelHomeDemo_1 = new JLabel("", JLabel.CENTER);
		labelHomeDemo_1.setBounds(120, 150, 150, 150);
		add(labelHomeDemo_1);
		labelHomeDemo_1.setBorder(border);
		
		JLabel labelHomeDemo_2 = new JLabel("", JLabel.CENTER);
		labelHomeDemo_2.setBounds(315, 190, 120, 120);
		add(labelHomeDemo_2);
		labelHomeDemo_2.setBorder(border);
		
		JLabel labelHomeDemo_3 = new JLabel("", JLabel.CENTER);
		labelHomeDemo_3.setBounds(151, 351, 100, 100);
		add(labelHomeDemo_3);
		labelHomeDemo_3.setBorder(border);
		
		JLabel labelHomeDemo_4 = new JLabel("", JLabel.CENTER);
		labelHomeDemo_4.setBounds(300, 370, 100, 100);
		add(labelHomeDemo_4);
		labelHomeDemo_4.setBorder(border);
 
		labelHomeDemo_1.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\pack0\\1.png"));
		labelHomeDemo_2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\pack0\\2.png"));
		labelHomeDemo_3.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\pack0\\3.png"));
		labelHomeDemo_4.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\pack0\\4.png"));
		
		JButton buttonSettings = new JButton();
		buttonSettings.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\settings.png"));
		buttonSettings.setBorderPainted(false); 
		buttonSettings.setContentAreaFilled(false); 
		buttonSettings.setFocusPainted(false); 
		buttonSettings.setOpaque(false);
		buttonSettings.addActionListener(e -> {
			Container parent = Game.getContainer();
			parent.removeAll();
			SettingsPanel settingsPanel = new SettingsPanel();
			settingsPanel.setBounds(0, 0, 600, 720);
			parent.add(settingsPanel);
			parent.repaint();
		});
		buttonSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		buttonSettings.setBounds(145, 525, 130, 130);
		add(buttonSettings);
		
		JButton buttonPlay = new JButton();
		buttonPlay.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\play.png"));
		buttonPlay.setBorderPainted(false); 
		buttonPlay.setContentAreaFilled(false); 
		buttonPlay.setFocusPainted(false); 
		buttonPlay.setOpaque(false);
		buttonPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		buttonPlay.addActionListener(e -> {
			Context context = Game.getContext();
			StartState startState = new StartState();
			startState.doAction(context, Game.getContainer());
			System.out.println(context.getState().toString());
		});
		buttonPlay.setBounds(310, 515, 150, 150); 
		add(buttonPlay);
		
		HomePanel.setLabelScore(HandleSettingsFile.getSpecifiedSetting("last_score"), HandleSettingsFile.getSpecifiedSetting("best_score"));
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 720);
    }
	
	public static void setLabelScore(String score, String _bestScore) {
		labelHomeScore.setText("Last: " + score + "  |  Best : " + _bestScore);
	}
}

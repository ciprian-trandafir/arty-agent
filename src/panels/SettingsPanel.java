package panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import classes.HandleSettingsFile;
import frames.Game;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final JCheckBox buttonSounds;
	
	public SettingsPanel() {
		setLayout(null);
		setBackground(new Color(0, 0, 0));
		
		Cursor cursorHand = new Cursor(Cursor.HAND_CURSOR);
		Cursor cursorDefault = new Cursor(Cursor.DEFAULT_CURSOR);
		
 		ImageIcon icon_sound_on = new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\sound_on.png"),
 				icon_sound_off = new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\sound_off.png");
 		
		buttonSounds = new JCheckBox();
		buttonSounds.setBounds(140, 350, 130, 130);
		add(buttonSounds);
		buttonSounds.setBorderPainted(false); 
		buttonSounds.setContentAreaFilled(false); 
		buttonSounds.setFocusPainted(false); 
		buttonSounds.setOpaque(false);
		buttonSounds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		buttonSounds.addActionListener(e -> {
			if (buttonSounds.isSelected()) {
				HandleSettingsFile.writeFileData("sound", "true");
				buttonSounds.setIcon(icon_sound_on);
			} else {
				HandleSettingsFile.writeFileData("sound", "false");
				buttonSounds.setIcon(icon_sound_off);
			}
		});
		
		if (Boolean.parseBoolean(HandleSettingsFile.getSpecifiedSetting("sound"))) {
			buttonSounds.setIcon(icon_sound_on);
			buttonSounds.setSelected(true);
		} else {
			buttonSounds.setIcon(icon_sound_off);
		}
		
		JButton buttonBackToHome = new JButton();
		buttonBackToHome.setBounds(140, 200, 130, 130);
		buttonBackToHome.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\back.png"));
		buttonBackToHome.setBorderPainted(false); 
		buttonBackToHome.setContentAreaFilled(false); 
		buttonBackToHome.setFocusPainted(false); 
		buttonBackToHome.setOpaque(false);
		add(buttonBackToHome);
		buttonBackToHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		buttonBackToHome.addActionListener(e -> {
			Container parent = Game.getContainer();
			parent.removeAll();
			HomePanel homePanel = HomePanel.getInstance();
			homePanel.setBounds(0, 0, 600, 720);
			parent.add(homePanel);
			parent.repaint();
		});
		
		JButton buttonInfos = new JButton();
		buttonInfos.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\info.png"));
		buttonInfos.setBorderPainted(false); 
		buttonInfos.setContentAreaFilled(false); 
		buttonInfos.setFocusPainted(false); 
		buttonInfos.setOpaque(false);
		buttonInfos.addActionListener(e -> JOptionPane.showMessageDialog(null, "� Ciprian Trandafir, CTI 22C33B, December 2021", "IP Project", JOptionPane.INFORMATION_MESSAGE));
		buttonInfos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		buttonInfos.setBounds(300, 200, 130, 130);
		add(buttonInfos);
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 720);
    }
}

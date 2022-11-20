package panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import classes.*;
import frames.Game;
import interfaces.Iterator;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int bestScore = Integer.parseInt(HandleSettingsFile.getSpecifiedSetting("best_score"));
	private final JProgressBar progressBar;
	private final JLabel findedCharacter, character_1, character_2, character_3, character_4, labelScore;
	private String finded_character, character1, character2, character3, character4;
	private final boolean playSound = Boolean.parseBoolean(HandleSettingsFile.getSpecifiedSetting("sound"));
	private final GameOverText gameOverText = new GameOverText();
	private final Iterator iterator;
	private final Timer timer;
	private final CareTaker careTaker;
	private final Originator originator;
	
	public GamePanel() {
		setLayout(null);
		setBackground(new Color(0, 0, 0));
		
		Border border = BorderFactory.createLineBorder(Color.WHITE, 7);
		Cursor cursorHand = new Cursor(Cursor.HAND_CURSOR);
		Cursor cursorDefault = new Cursor(Cursor.DEFAULT_CURSOR);

    	gameOverText.setBounds(0, 0, 600, 720);
    	
		labelScore = new JLabel();
		labelScore.setBounds(10, 10, 82, 30);
		add(labelScore);
		labelScore.setText("Score : 0");
		labelScore.setForeground(Color.WHITE);
		
		findedCharacter = new JLabel();
		findedCharacter.setBounds(200, 10, 160, 160);
		add(findedCharacter);
		findedCharacter.setBorder(border);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(60);
		progressBar.setForeground(Color.GREEN);
		progressBar.setValue(100);
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(53, 623, 480, 40);
		add(progressBar);
		
		character_1 = new JLabel();
		character_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleClickAction(character1);
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		character_1.setBounds(97, 203, 160, 160);
		add(character_1);
		character_1.setBorder(border);
		
		character_2 = new JLabel();
		character_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleClickAction(character2);
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		character_2.setBounds(323, 203, 160, 160);
		add(character_2);
		character_2.setBorder(border);
		
		character_3 = new JLabel();
		character_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleClickAction(character3);
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		character_3.setBounds(97, 405, 160, 160);
		add(character_3);
		character_3.setBorder(border);
		
		character_4 = new JLabel();
		character_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				handleClickAction(character4);
			}
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		character_4.setBounds(323, 405, 160, 160);
		add(character_4);
		character_4.setBorder(border);
		
		timer = new Timer();
		TimerTask task = new TimerTask() {
	        @Override
	        public void run() {
        		progressBar.setValue(progressBar.getValue() - 1);
        		
	            if (progressBar.getValue() == 0) {
					showGameOver();
	            }
	        }
	    };
	    timer.schedule(task, 0, 1000);

		JButton buttonBack = new JButton();
		buttonBack.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\icons\\back_small.png"));
		buttonBack.setBorderPainted(false); 
		buttonBack.setContentAreaFilled(false); 
		buttonBack.setFocusPainted(false); 
		buttonBack.setOpaque(false);
		buttonBack.addActionListener(e -> {
			timer.cancel();
			gameFinished();
		});
		buttonBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(cursorHand);
			}
			public void mouseExited(MouseEvent e) {
				setCursor(cursorDefault);
			}
		});
		buttonBack.setBounds(520, 5, 60, 60);
		add(buttonBack);
		
		PicturesRepository picturesRepository = new PicturesRepository();
		iterator = picturesRepository.getIterator();

		originator = new Originator();
		careTaker = new CareTaker();

		originator.setState("0");
		careTaker.add(originator.saveStateToMemento());

		handleCharacters();
	}
	
	public void playSound(String name) {
		AudioPlayer audioPlayer = new AudioPlayer();
		audioPlayer.play("wav", name);
	}
	
	public void handleClickAction(String var) {
		if (finded_character.equals(var)) {
			if (playSound) {
				playSound("success");
			}

			int score = Integer.parseInt(originator.getState());
			score++;

			originator.setState(String.valueOf(score));
			careTaker.add(originator.saveStateToMemento());

			labelScore.setText("Score : " + score);

			if (score > bestScore) {
				bestScore = score;
			}
			
			handleCharacters();
		} else {
			progressBar.setValue(progressBar.getValue() - 20);
			if (playSound) {
				playSound("error");
			}
		}
	}
	
	public void handleCharacters() {
		String pack = null;
		if (iterator.hasNext()) {
			pack = (String)iterator.next();
		} else {
			showGameOver();
		}
		
		int finded_number = ThreadLocalRandom.current().nextInt(1, 5);
        
		String[] characters_array = {"1", "2", "3", "4"};
		Collections.shuffle(Arrays.asList(characters_array));
		
		findedCharacter.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\" + pack + "\\" + finded_number + ".png"));
		character_1.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\" + pack + "\\" + characters_array[0] + ".png"));
		character_2.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\" + pack + "\\" + characters_array[1] + ".png"));
		character_3.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\" + pack + "\\" + characters_array[2] + ".png"));
		character_4.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\pictures\\" + pack + "\\" + characters_array[3] + ".png"));

		finded_character = String.valueOf(finded_number);
		character1 = characters_array[0];
		character2 = characters_array[1];
		character3 = characters_array[2];
		character4 = characters_array[3];
	}

	private void showGameOver() {
		Container parent = Game.getContainer();
		parent.removeAll();

		GameOverText.setScore(originator.getState());
		parent.add(gameOverText);
		parent.repaint();

		new java.util.Timer().schedule(
			new java.util.TimerTask() {
				@Override
				public void run() {
					gameFinished();
				}
			}, 4000
		);
		timer.cancel();

	}

	private void gameFinished() {
		String score = originator.getState(),
				_bestScore = String.valueOf(bestScore);

		HandleSettingsFile.writeFileData("last_score", score);
		HandleSettingsFile.writeFileData("best_score", _bestScore);

		HomePanel.setLabelScore(score, _bestScore);

		Context context = Game.getContext();
		StopState stopState = new StopState();
		stopState.doAction(context, Game.getContainer());
		System.out.println(context.getState().toString());
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 720);
    }
}

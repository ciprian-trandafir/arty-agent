package classes;

import interfaces.State;
import panels.GamePanel;
import java.awt.Container;

public class StartState implements State {
    public String toString(){
        return "Start State";
    }

    @Override
    public void doAction(Context context, Container container) {
        context.setState(this);
        container.removeAll();
        GamePanel gamePanel = new GamePanel();
        gamePanel.setBounds(0, 0, 600, 720);
        container.add(gamePanel);
        container.repaint();
    }
}

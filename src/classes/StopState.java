package classes;

import interfaces.State;
import panels.HomePanel;
import java.awt.Container;

public class StopState implements State {
    public String toString(){
        return "Stop State";
    }

    @Override
    public void doAction(Context context, Container container) {
        context.setState(this);
        container.removeAll();
        HomePanel homePanel = HomePanel.getInstance();
        homePanel.setBounds(0, 0, 600, 720);
        container.add(homePanel);
        container.repaint();
    }
}

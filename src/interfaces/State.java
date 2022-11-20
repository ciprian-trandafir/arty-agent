package interfaces;

import classes.Context;
import java.awt.Container;

public interface State {
    public void doAction(Context context, Container container);
}

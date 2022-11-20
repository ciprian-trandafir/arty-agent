package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import panels.HomePanel;

public class HomePanelTest {
    @Test
    public void implementedCorrectly() {
        Assertions.assertEquals(HomePanel.getInstance(), HomePanel.getInstance());
    }
}
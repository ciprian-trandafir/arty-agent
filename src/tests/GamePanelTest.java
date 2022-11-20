package tests;

import classes.PicturesRepository;
import interfaces.Iterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamePanelTest {
    @Test
    public void implementedCorrectly() {
        Iterator iterator;
        PicturesRepository picturesRepository = new PicturesRepository();
        iterator = picturesRepository.getIterator();

        String pack = null;
        if (iterator.hasNext()) {
            pack = (String)iterator.next();
        }

        Assertions.assertEquals(pack, "pack1");
    }
}
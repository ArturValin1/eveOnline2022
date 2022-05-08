package place;

import org.sikuli.script.Region;

import java.awt.event.KeyEvent;


public class Inventory extends BaseClass {
    public Inventory(Region region, String pic) {
        super(region, pic);
    }

    public Inventory() {
        super(new Region(0, 36, 643, 365), "miningHold.png");
        //заполняем последовательно кнопки которые нужно нажать.
        fillKeys(KeyEvent.VK_ALT);
        fillKeys(KeyEvent.VK_C);
    }
}
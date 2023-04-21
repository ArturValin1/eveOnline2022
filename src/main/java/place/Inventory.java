package place;

import org.sikuli.script.Region;

import java.awt.event.KeyEvent;


public class Inventory extends BaseClass {
    public Inventory(Region region, String pic) {
        super(region, pic);
    }

    public Inventory() {
        super(new Region(59, 40, 765, 555), "OreHolderShip.png");
        //заполняем последовательно кнопки которые нужно нажать.
        fillKeys(KeyEvent.VK_ALT);
        fillKeys(KeyEvent.VK_C);
    }
}
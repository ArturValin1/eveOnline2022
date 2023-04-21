package place;

import org.sikuli.script.Region;

import java.awt.event.KeyEvent;

public class Athanor extends BaseClass {

    public Athanor(Region region, String pic) {
        super(region, pic);
    }

    public Athanor() {
        super(new Region(601, 344, 720, 481), "refeneryForCompress.png");
        //заполняем последовательно кнопки которые нужно нажать.
        fillKeys(KeyEvent.VK_ALT);
        fillKeys(KeyEvent.VK_E);
    }
}
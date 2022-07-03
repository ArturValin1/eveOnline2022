package place;

import org.sikuli.script.Region;

import java.awt.event.KeyEvent;

public class Belt2_1 extends BaseClass{
    public Belt2_1(Region region, String pic) {
        super(region, pic);
    }

    public Belt2_1() {
        super(new Region(305, 86, 705, 481), "belt2_p2.png");
        //заполняем последовательно кнопки которые нужно нажать.
        fillKeys(KeyEvent.VK_ALT);
        fillKeys(KeyEvent.VK_E);
    }
}

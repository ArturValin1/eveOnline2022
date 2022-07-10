package place;

import org.sikuli.script.Region;

import java.awt.event.KeyEvent;

public class Belt5_1 extends BaseClass{
    public Belt5_1(Region region, String pic) {
        super(region, pic);
    }

    public Belt5_1() {
        super(new Region(305, 65, 680, 571), "belt51.png");
        //заполняем последовательно кнопки которые нужно нажать.
        fillKeys(KeyEvent.VK_ALT);
        fillKeys(KeyEvent.VK_E);
    }
}

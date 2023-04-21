package place;

import org.sikuli.script.Region;

import java.awt.event.KeyEvent;

public class Belt extends BaseClass{
    public Belt(Region region, String pic) {
        super(region, pic);
    }

    public Belt() {
        super(new Region(305, 86, 805, 681), "ABPoint2.png");
        //заполняем последовательно кнопки которые нужно нажать.
        fillKeys(KeyEvent.VK_ALT);
        fillKeys(KeyEvent.VK_E);
    }
}

import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.awt.*;

public class TestRegion {
    public static void main(String[] args) throws AWTException {
        Region region = new Screen().selectRegion();
        Robot robot = new Robot();
        robot.delay(400);
        System.out.println(region.toString());
    }
}

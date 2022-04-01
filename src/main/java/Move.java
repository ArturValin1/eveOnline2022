import org.sikuli.script.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Move {
    public boolean warpToFromAltE(Robot robot, String warpToObject) {
        boolean result = false;
        Region region = new Screen();
        robot.delay(1_000);
        pressReleaseTwoKeys(robot, KeyEvent.VK_ALT, KeyEvent.VK_E);
        Match match = null;
        try {
            match = region.find(warpToObject);
            region.rightClick(match);
            robot.delay(900);
            match = region.find("warp_to_location.png");
            match.click();
            result = true;
        } catch (FindFailed e) {
            e.printStackTrace();
        }
        robot.delay(1_000);
        pressReleaseTwoKeys(robot, KeyEvent.VK_ALT, KeyEvent.VK_E);
        return result;
    }



    public void pressReleaseTwoKeys(Robot robot, int k1, int k2) {
        robot.keyPress(k1);
        robot.delay(200);
        robot.keyPress(k2);
        robot.delay(200);
        robot.keyRelease(k2);
        robot.keyRelease(k1);
    }
    public void pressReleaseOneKeys(Robot robot, int k1) {
        robot.keyPress(k1);
        robot.delay(200);
        robot.keyRelease(k1);
    }
}
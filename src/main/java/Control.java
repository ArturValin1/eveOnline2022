import org.sikuli.script.FindFailed;
import place.BaseClass;
import place.PlagioclaseLock;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Control {
    private Check check = new Check();
    private Robot robot;

    public Control(Robot robot) {
        this.robot = robot;
    }

    public void removeLasers() {
        int lasers = check.checkLaserOnAsteroid();
        if (lasers == 2) {
            pressReleaseKey(KeyEvent.VK_F1);
            pressReleaseKey(KeyEvent.VK_F2);
            return;
        }
        if (lasers == 1) {
            pressReleaseKey(KeyEvent.VK_F1);
            removeLasers();
        }
    }

    public void removeAsteroid(BaseClass pl) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(100);
        while (check.checkLockPlagioclase() > 0) {
            try {
                pl.getRegion().find(pl.getPic()).click();
                robot.delay(200);
            } catch (FindFailed e) {
                e.printStackTrace();
            }
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(100);
    }

    public void pressReleaseKey(int key) {
        robot.keyPress(key);
        robot.delay(210);
        robot.keyRelease(key);
        robot.delay(290);
    }
}

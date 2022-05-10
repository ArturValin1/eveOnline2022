import org.sikuli.script.FindFailed;
import place.BaseClass;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Control {
    private Check check = new Check();
    private Robot robot;

    public Control(Robot robot) {
        this.robot = robot;
    }

    //удаляем все лазеры с астероида
    public void removeLasers() {
        int lasers = check.checkLaserOnAsteroid();
        if (lasers == 2) {
            pressReleaseKey(KeyEvent.VK_F1);
            pressReleaseKey(KeyEvent.VK_F2);
            robot.delay(1_000);
            return;
        }
        if (lasers == 1) {
            pressReleaseKey(KeyEvent.VK_F1);
            robot.delay(1_500);
            removeLasers();
        }
    }

    //удаляем все астероиды из захвата
    public void removeAsteroid(BaseClass pl) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(100);
        while (check.checkLockPlagioclase() > 0) {
            try {
                pl.getRegion().find(pl.getPic()).click();
                robot.delay(700);
            } catch (FindFailed e) {
                e.printStackTrace();
            }
        }
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(100);
    }

    //активируем два лазера
    public void startTwoLaser() {
        removeLasers();
        pressReleaseKey(KeyEvent.VK_F1);
        robot.delay(200);
        pressReleaseKey(KeyEvent.VK_F2);
        robot.delay(300);
    }

    //находимся ли в варпе
    public boolean inWarp() {
        boolean result = (check.isMPS() > 0);
        return !result;
    }

    public void pressReleaseKey(int key) {
        robot.keyPress(key);
        robot.delay(210);
        robot.keyRelease(key);
        robot.delay(290);
    }
}
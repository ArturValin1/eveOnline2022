import org.sikuli.script.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Start {
    private boolean flag = true;
    private long timeMiningAsteroid = 0;
    private long delta = 0;
    private long startTime = 0;
    private long endTime = 0;

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Move move = new Move(robot);
        Control control = new Control(robot);
        robot.delay(1_000);
        Mining mining = new Mining(16, robot, new Screen().selectRegion());

        for (int i = 0; i < 3; i++) {
            oneCycle(move, mining, control, robot);
            System.out.println(String.format(" ------------   %s ------------", i));
            robot.delay(10_000 + 1000 * i * 2);
        }

        System.out.println("END");
    }


    public void isFullTimeToMining() {


    }



    public static void oneCycle(Move move, Mining mining, Control control, Robot robot) {
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1_000);
        control.removeLasers();
        move.moveToUnloadAndReturned();
        mining.startSurvey();
        mining.calcTime();
        control.startTwoLaser();
    }


}

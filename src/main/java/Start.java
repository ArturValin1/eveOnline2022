import org.sikuli.script.Screen;


import java.awt.*;
import java.awt.event.KeyEvent;

public class Start {

    public static void main(String[] args) throws AWTException {
        Start s = new Start();
        Robot robot = new Robot();
        Move move = new Move(robot);
        Control control = new Control(robot);
        robot.delay(1_000);
        Mining mining = new Mining(16, robot, new Screen().selectRegion());
        s.miningStart(robot, move, control, mining);
        System.out.println("END");
    }


    private void miningStart(Robot robot, Move move, Control control, Mining mining) {
        for (int i = 0; true; i++) {
            if (!oneCycle(move, mining, control, robot)) {
                break;
            }
            System.out.println(String.format(" ------------   %s ------------", i));
        }
    }

    public boolean oneCycle(Move move, Mining mining, Control control, Robot robot) {
        boolean result = true;
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1_000);
        control.removeLasers();
        move.moveToUnloadAndReturned();
        if (!mining.startMining(control)) {
            result = false;
        }
        return result;
    }
}

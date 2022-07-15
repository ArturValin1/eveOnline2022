import org.sikuli.script.Screen;
import place.BaseClass;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Start {

    public static void main(String[] args) throws AWTException {
        Belts belts = new Belts();
        belts.initPlace();
        List<BaseClass> listBelts = belts.getBelts();
        Start s = new Start();
        Robot robot = new Robot();
        Move move = new Move(robot);
        Control control = new Control(robot);
        robot.delay(1_000);
        Mining mining = new Mining(16, robot, new Screen().selectRegion());
        s.miningStart(robot, move, control, mining, listBelts);
        System.out.println("END");
    }


    private void miningStart(Robot robot, Move move, Control control, Mining mining, List<BaseClass> listBelts) {
        while (listBelts.size() > 0) {
            move.setBelt(listBelts.get(0));
            for (int i = 0; true; i++) {
                if (!oneCycle(move, mining, control, robot)) {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(String.format("++++++++++      Delete belt %s  +++++++++++++++", listBelts.get(0).getPic()));
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    listBelts.remove(0);
                    break;
                }
                System.out.println(String.format(" ------------   %s ------------", i));
            }
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

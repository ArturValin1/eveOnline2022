import org.sikuli.script.Screen;
import place.Belt;
import place.PlagioclaseLock;

import java.awt.*;

public class Start {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Move move = new Move(robot);
        robot.delay(1_000);
        Mining mining = new Mining(16, robot, new Screen().selectRegion());
        System.out.println(move.moveToUnloadAndReturned());
//        mining.startSurvey();
//        System.out.println(mining.lockAsteroid());
//        mining.startLaser();

//        PlagioclaseLock pl = new PlagioclaseLock();
//        Control control = new Control(robot);
//        control.removeAsteroid(pl);

    }
}

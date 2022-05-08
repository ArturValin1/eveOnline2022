import java.awt.*;

public class Start {
    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        Move move = new Move(robot);
        System.out.println(move.moveToUnloadAndReturned());
    }
}

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.awt.*;
import java.util.Iterator;

public class SsProbe {
    public static void main(String[] args) throws AWTException {
        CloseOpenWindow cow = new CloseOpenWindow();



//        try {
            Robot r = new Robot();

//            r.delay(1_000);
//
//        Region region = new Screen().selectRegion();
//        for (int i = 0; i < 10; i++) {
//            int j =0;
//            try {
//                Iterator<Match> all = region.findAll(String.format("%s", i));
//                while (all.hasNext()){
//                    Match next = all.next();
//                    r.delay(300);
//                    System.out.println(String.format("x = %s, y = %s",next.x,next.y));
//                    next.click();
//                    j++;
//                }
//                System.out.println(String.format("%s = %s",i,j));
//            } catch (FindFailed e) {
//                throw new RuntimeException(e);
//            }
//        }
//            System.out.println(region.text());
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }

    }
}

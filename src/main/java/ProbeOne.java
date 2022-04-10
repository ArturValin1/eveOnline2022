import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.awt.*;
import java.util.List;

public class ProbeOne {
    public static void main(String[] args) throws AWTException {
        Robot r = new Robot();
        ImagePath.add("D:\\java\\eveOnline2022\\src\\main\\resources\\images"); //задаем папку с изображениями.
        r.delay(1_000);
        Mining m = new Mining(16,r);
        List<Region> regions =  m.newFindM3(new Screen().selectRegion());
        regions.forEach(e->{
            System.out.println(e.text());
        });
    }
}

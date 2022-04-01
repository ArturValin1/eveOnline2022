import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ppp {
    public static void main(String[] args) throws AWTException, IOException {
        Robot r = new Robot();
        BufferedImage bi = r.createScreenCapture(new Rectangle(0,0,1920,1080));
        File outputfile = new File("image.png");
        ImageIO.write(bi, "png", outputfile);
        Region region = new Screen();
        System.out.println(String.format("%s %S %s %s", region.x, region.y,  region.w, region.h));
    }
}

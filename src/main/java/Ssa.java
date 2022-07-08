import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ssa {
    public static void main(String[] args) {
        BlackAndWhite bw = new BlackAndWhite();
        try {
            BufferedImage sub = ImageIO.read(new File("src/main/resources/images/R1980x1080/kmBW.png"));
            BufferedImage image = ImageIO.read(new File("src/main/resources/images/R1980x1080/test01BW.png"));
            BufferedImage sub1 = ImageIO.read(new File("src/main/resources/images/R1980x1080/m3BW.png"));
            Point p =bw.findSubImageInBigImage(sub, image);
            System.out.println(p.x + " " + p.y);
            p =bw.findSubImageInBigImage(sub1, image);
            System.out.println(p.x + " " + p.y);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

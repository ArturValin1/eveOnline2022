import dao.Asteroid;
import org.sikuli.script.Image;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransferImage {
    public Asteroid findAsteroid(Robot robot) throws IOException, AWTException {
        CloseOpenWindow cow = new CloseOpenWindow();
        BlackAndWhite bw = new BlackAndWhite();
        String m3 = "src/main/resources/images/R1900x600/m3black.png";
        BufferedImage m3Image = bw.cutPicture(ImageIO.read(new File(m3)));
        Region region = new Screen().selectRegion();
        Mining mining = new Mining(16, robot, region);
        List<Region> regionList = mining.findM3(region);
        regionList.forEach(e -> {
            BufferedImage colorImage = e.getImage().get();
            BufferedImage blackImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
            Graphics2D graphics2D = blackImage.createGraphics();
            graphics2D.drawImage(colorImage, 0, 0, null);

            BufferedImage[] split = bw.splitImageToVolumeAndRange(blackImage, m3Image);
            int[] res = bw.volumeAndRange(split);
            if (res[0] > 0 && res[1] < 17) {
                System.out.println(String.format("Region x = %s  y = %s accept. Volume is %s, range is %s", e.getX(), e.getY(), res[0], res[1]));
            } else
                System.out.println(String.format("Region x = %s  y = %s EPSENT. Volume is %s, range is %s", e.getX(), e.getY(), res[0], res[1]));
        });
        return  null;
    }
}
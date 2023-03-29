package helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecordImageToFile {
    public void record(List<BufferedImage> list, String folder) {
        int i = 0;
        for (BufferedImage t : list) {
            String path = folder+"\\" + (i++) + ".png";
            try {
                ImageIO.write(t, "png", new File(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

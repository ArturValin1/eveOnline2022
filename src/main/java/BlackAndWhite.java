import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

public class BlackAndWhite {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_YELLOW = "\u001B[31m";
        String fileName = "src/main/resources/images/R1980x1080/bw/bw9.png";
        File file = new File(fileName);
        BufferedImage bi = ImageIO.read(file);
        int width = bi.getWidth();
        int height = bi.getHeight();
        Color color = null;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <width; j++) {
                color = new Color(bi.getRGB(j, i));
                if (color.getBlue() > 0) {
                    System.out.print(ANSI_YELLOW
                            + "2"
                            + ANSI_RESET);
                } else System.out.print(0);
            }
            System.out.println();
        }
    }
}
//        String folder = "D:\\java\\digits\\1980\\";
//        List<String> files = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            files.add(String.format("%s%s.png", folder, i));
//        }
//        AtomicInteger i = new AtomicInteger();
//        files.forEach(e -> {
//            File file = new File(e);
//            BufferedImage orginalImage = null;
//            try {
//                orginalImage = ImageIO.read(file);
//                BufferedImage blackAndWhiteImg = new BufferedImage(
//                        orginalImage.getWidth(), orginalImage.getHeight(),
//                        BufferedImage.TYPE_BYTE_BINARY);
//                Graphics2D graphics = blackAndWhiteImg.createGraphics();
//                graphics.drawImage(orginalImage, 0, 0, null);
//                ImageIO.write(blackAndWhiteImg, "png", new File(String.format("%s%s%d.png", folder, "bw", i.getAndIncrement())));
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//    }
import helper.BlackAndWhite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Ssa {
    public static void main(String[] args) {
        Ssa s = new Ssa();
        BlackAndWhite bw = new BlackAndWhite();
        try {
            BufferedImage base = ImageIO.read(new File("src/main/resources/images/R1980x1080/2611BW.png"));
            BufferedImage divide = ImageIO.read(new File("src/main/resources/images/R1980x1080/m3BW.png"));
            Point p = s.findSubImageInBigImage(divide, base);
            if (p != null) {
                System.out.println(p.getX());
            }
            BufferedImage[] split = s.split(base, divide);
            if (split[0] != null && split[1] != null) {
                bw.printImage(bw.cutPicture(split[0]));
                bw.printImage(bw.cutPicture(split[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
    Делим изображение на два и возращаем.
    Первое до делителя.
    Второе после делителя. (Делитель исключеатся)
     */
    public BufferedImage[] split(BufferedImage image, BufferedImage divide) {
        boolean mayBe = (image.getHeight() >= divide.getHeight()) && (image.getWidth() >= image.getWidth());
        BufferedImage[] result = new BufferedImage[2];
        if (mayBe) {
            Point point = findSubImageInBigImage(divide, image);
            if (point != null) {
                BufferedImage image1 = image.getSubimage(0, 0, point.x, image.getHeight());
                BufferedImage image2 = image.getSubimage(point.x + divide.getWidth(), 0, image.getWidth() - (point.x + divide.getWidth() + 1), image.getHeight());
                result[0] = image1;
                result[1] = image2;
            }
        } else {
            System.out.println("Divide biggest than original. ");
        }
        return result;
    }
    /*
     * Take from
     * https://github.com/jfalkner/find_image/blob/master/src/falkner/jayson/findimage/FindImage.java
     * remake for my purposes.
     */

    public Point findSubImageInBigImage(BufferedImage subImage, BufferedImage image) {
        // brute force N^2 check all places in the image
        for (int i = 0; i <= image.getWidth() - subImage.getWidth(); i++) {
            check_subimage:
            for (int j = 0; j <= image.getHeight() - subImage.getHeight(); j++) {
                for (int ii = 0; ii < subImage.getWidth(); ii++) {
                    for (int jj = 0; jj < subImage.getHeight(); jj++) {
                        if (subImage.getRGB(ii, jj) != image.getRGB(i + ii, j + jj)) {
                            continue check_subimage;
                        }
                    }
                }
                // if here, all pixels matched
                return new Point(i, j);
            }
        }
        return null;
    }

}
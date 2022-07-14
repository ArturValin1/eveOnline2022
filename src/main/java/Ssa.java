import place.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Ssa {
    public static void main(String[] args) {
        BlackAndWhite bw = new BlackAndWhite();

        try {
//            BufferedImage sub = ImageIO.read(new File("src/main/resources/images/R1980x1080/kmBW.png"));
            BufferedImage sub = ImageIO.read(new File("src/main/resources/images/R1980x1080/m3BW.png"));
            BufferedImage image = ImageIO.read(new File("src/main/resources/images/R1980x1080/bwTestDigit.png"));
            BufferedImage zero = ImageIO.read(new File("src/main/resources/images/R1980x1080/bw/zero.png"));
            Point p = bw.findSubImageInBigImage(sub, image);
            if (p != null)
                System.out.println(p.x + " " + p.y);
            else {
                bw.printImage(sub);
                bw.printImage(image);
                System.out.println("Not found");
            }
            bw.printImage(image);
            BufferedImage im1 = bw.cutPicture(image.getSubimage(0, 0, p.x, image.getHeight()));

            BufferedImage[] images = bw.sliceToDigital(im1);


            int count = 0;
            for (BufferedImage i : images) {
                bw.printImage(i);

            }

            System.out.println(" zero = " + count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        List<String> list = new ArrayList<>();
        list.add("ss");
        list.add("dd");
        list.add("ff");
        while (list.size()>0){
            System.out.println(String.format("%s and size list is %s", list.get(0), list.size()));
            list.remove(0);
        }

    }

}

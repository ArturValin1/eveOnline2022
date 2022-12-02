import helper.BlackAndWhite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Ssa {
    public static void main(String[] args) {
        BlackAndWhite bw = new BlackAndWhite();
        try {
            String num = bw.parseImageString(ImageIO.read(new File("src/main/resources/images/R1980x1080/temp/01.png")));
            System.out.println(num);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
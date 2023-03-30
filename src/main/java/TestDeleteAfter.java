import helper.*;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TestDeleteAfter {
    public static void main(String[] args) {
        ImageUtils iu = new ImageUtils();
        SliceToLine stl = new SliceToLine();
        SliceToSymbol sliceToSymbol = new SliceToSymbol();
        try {
            BufferedImage bi = ImageIO.read(new File("src/main/resources/images/R1080/t02.png"));
            bi = iu.cutPicture(iu.convertToBlackAndWhite1(bi));
//            List<BufferedImage> strings = stl.getAllString(bi);
//            List<BufferedImage> symbols = new ArrayList<>();
//            for (BufferedImage t : strings) {
//                sliceToSymbol.getUniqueSymbolsFromImageString(t, symbols);
//            }

//            int i=0;
//            for (BufferedImage t: symbols){
//                String path = "D:\\java\\temp\\"+ (i++) + ".png";
//                ImageIO.write(t, "png", new File(path));
//            }

//            ParseString parseString = new ParseString();
//            String str1 = parseString.parseImageString(bi).replaceAll("\n\n", "\n");
//            System.out.println(str1);
//            System.out.println("--------");
//            str1 = str1.split("\n")[0];
//            System.out.println(str1);
//            String[] str2 = str1.split("m\\^3");
//            System.out.println(str2[0]);
//            System.out.println(str2[1]);

            CloseOpenWindow cow = new CloseOpenWindow();
            cow.setup();
            Robot r = new Robot();
            r.delay(1_000);
            Region region = new Screen().selectRegion();
            Mining mining = new Mining(16,r,region);
            Region testRegion =  mining.asteroidForMining();
            if (testRegion!=null){
                System.out.println("---------GOOOD------------");

            } else {
                System.out.println("==========NOT=============");
            }

        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }
}

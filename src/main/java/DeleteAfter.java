import helper.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DeleteAfter {
    public static void main(String[] args) {
        ImageUtils iu = new ImageUtils();
        SliceToLine stl = new SliceToLine();
        SliceToSymbol sliceToSymbol = new SliceToSymbol();
        try {
            BufferedImage bi = ImageIO.read(new File("src/main/resources/images/R1080/tt1.png"));
            bi = iu.cutPicture(iu.convertToBlackAndWhite1(bi));
            List<BufferedImage> strings = stl.getAllString(bi);
            List<BufferedImage> symbols = new ArrayList<>();
            for (BufferedImage t : strings) {
                sliceToSymbol.getUniqueSymbolsFromImageString(t, symbols);
            }

//            int i=0;
//            for (BufferedImage t: symbols){
//                String path = "D:\\java\\temp\\"+ (i++) + ".png";
//                ImageIO.write(t, "png", new File(path));
//            }


            ParseString parseString = new ParseString();
            String str1 = parseString.parseImageString(bi);
            System.out.println(str1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

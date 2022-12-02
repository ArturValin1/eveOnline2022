import helper.BlackAndWhite;
import helper.SymbolsFromString;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DeleteAfter {
    public static void main(String[] args) {
        BlackAndWhite bw = new BlackAndWhite();
        SymbolsFromString sfs = new SymbolsFromString();
        try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/images/R1980x1080/temp/02.png"));
            BufferedImage bi = bw.convertToBlackAndWhite1(image);
            List<BufferedImage> list = bw.getStringPicsFromImage(bi);
            List<BufferedImage> symbolsList = new ArrayList<>();
            list.forEach(w -> {
                sfs.getSetSymbolsFromImageString(w, symbolsList);
            });
            List<BufferedImage> cutSymbols = new ArrayList<>();
            symbolsList.forEach(w -> {
                sfs.getSetSymbolsFromImageString(w, cutSymbols);
            });
            System.out.println(cutSymbols.size());
            AtomicInteger i = new AtomicInteger();
            cutSymbols.forEach(w->{
                try {
                    ImageIO.write(w,"png", new File(String.format("D:\\temp\\%s.png", i.getAndIncrement())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package digitals;

import helper.BlackAndWhite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Te {
    public static void main(String[] args) {
//        char c = 94;
        BlackAndWhite bw = new BlackAndWhite();
        SampleNumbers sm = new SampleNumbers();
        try {
            BufferedImage test = bw.convertToBlackAndWhite1(ImageIO.read(new File("src/main/resources/images/R1980x1080/temp/01.png")));
            List<BufferedImage> list = bw.getStringPicsFromImage(test);
            List<BufferedImage> numbers = bw.getSybolsFromStringImage(list.get(0));
            StringBuilder sb = new StringBuilder();
            Set set = sm.getMap().keySet();
            Map<String,List<BufferedImage>> map = sm.getMap();
            numbers.forEach(w->{
                set.forEach(v->{
                    map.get(v).forEach(a->{
                        if (bw.findSubImageInBigImage(a,w)!=null){
                            sb.append(v);
                        }
                    });
                });
            });

            System.out.println(sb);




        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

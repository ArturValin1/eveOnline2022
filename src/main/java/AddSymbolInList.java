import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AddSymbolInList {
    public static void main(String[] args) {
        String path = "src/main/resources/images/R1980x1080/temp/symbols";
        File file = new File(path);
        List<File> fileList = Arrays.stream(file.listFiles()).toList();
        fileList.forEach(v->{
            System.out.println(String.format("%s is Directory %s", v, v.isDirectory()));
        });
    }


}

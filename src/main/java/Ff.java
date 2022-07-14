import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Ff {
    public static void main(String[] args) {
        File[] files = new File("src/main/resources/images/R1980x1080/bw").listFiles();
        List<File> fileList = Arrays.stream(files).toList();
        fileList.forEach(e->{
            if (!e.isDirectory()){
                System.out.println(e.getName() + " is file");
            } else {
                System.out.println(e.getName() + " is Directory");
            }
        });
    }
}

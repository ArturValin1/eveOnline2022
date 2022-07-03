import org.sikuli.script.*;
import place.BaseClass;

import java.awt.*;
import java.util.List;

public class CloseOpenWindow {

    public CloseOpenWindow() {
        setup();
    }

    public void setup() {
        String r900 = "/R1900x600";
        String r1080 = "/R1980x1080";
        String path = "src/main/resources/images";
        String result;
        if (new Screen().toString().contains("600")) {
            result = r900;
        } else {
            result = r1080;
        }
        ImagePath.add(path + result);
        ImagePath.add(path+result+"/digitals");

        OCR.globalOptions().language("ccc");
    }

    public boolean isOpenWindow(BaseClass base, Robot robot) {
        boolean result = checkMatch(base.getRegion(), base.getPic());
        if (!result) {
            pressOneKey(robot, base.getKeys());
            robot.delay(200);
            releaseOneKey(robot, base.getKeys());
            robot.delay(700);
            result = checkMatch(base.getRegion(), base.getPic());
        }
        return result;
    }

    public void closedWindow(BaseClass base, Robot robot) {
        boolean result = checkMatch(base.getRegion(), base.getPic());
        if (result) {
            pressOneKey(robot, base.getKeys());
            robot.delay(200);
            releaseOneKey(robot, base.getKeys());
        }
        //необходима двойная проверка, т.к. после первой, если окно было на заднем фоне, то оно становиться только  на передний.
        result = checkMatch(base.getRegion(), base.getPic());
        if (result) {
            pressOneKey(robot, base.getKeys());
            robot.delay(200);
            releaseOneKey(robot, base.getKeys());
        }
    }

    public boolean checkMatch(Region region, String pic) {
        boolean result = false;
        try {
            Match match = region.find(pic);
            if (match != null) {
                result = true;
                System.out.println(String.format("----> Изображение %s успешно найдено. <----", pic));
            }
        } catch (FindFailed e) {
            System.out.println(String.format("----> Изображение %s не найдено. <----", pic));
        }
        return result;
    }

    public void pressOneKey(Robot r, java.util.List<Integer> list) {
        list.forEach(e -> {
            r.keyPress(e);
            r.delay(100);
        });
    }

    public void releaseOneKey(Robot r, List<Integer> list) {
        list.forEach(e -> {
            r.keyRelease(e);
            r.delay(122);
        });
    }
}
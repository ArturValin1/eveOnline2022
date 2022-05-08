import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

public class ClickTo {
    public boolean leftClick(Region region, String point) {
        boolean result = false;
        try {
            Match match = region.find(point);
            match.click();
            result = true;
        } catch (FindFailed e) {
            System.out.println(String.format("Не могу найти %s. ClickTo->leftClick()", point));
        }
        return result;
    }

    public boolean rightClick(Region region, String point) {
        boolean result = false;
        try {
            Match match = region.find(point);
            match.rightClick();
            result = true;
        } catch (FindFailed e) {
            System.out.println(String.format("Не могу найти %s. ClickTo->rightClick()", point));
        }
        return result;
    }
}
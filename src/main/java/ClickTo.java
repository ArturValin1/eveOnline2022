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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return result;
    }
}

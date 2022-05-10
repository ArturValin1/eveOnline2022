package place;

import org.sikuli.script.Region;

public class MinPerSecond extends BaseClass{
    public MinPerSecond(Region region, String pic) {
        super(region, pic);
    }

    public MinPerSecond() {
        super(new Region(755,782,98,52), "minPerSecond.png");
    }
}

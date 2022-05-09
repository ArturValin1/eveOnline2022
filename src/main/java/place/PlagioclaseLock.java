package place;

import org.sikuli.script.Region;

public class PlagioclaseLock extends BaseClass{
    public PlagioclaseLock(Region region, String pic) {
        super(region, pic);
    }

    public PlagioclaseLock() {
        super(new Region(722,32,396,193), "plagioclaseLock.png");
    }
}

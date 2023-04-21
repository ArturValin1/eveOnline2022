package place;

import org.sikuli.script.Region;

public class PlagioclaseLock extends BaseClass{
    public PlagioclaseLock(Region region, String pic) {
        super(region, pic);
    }

    public PlagioclaseLock() {
        super(new Region(1205,28,250,169), "plagio01.png");
    }
}

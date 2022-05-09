package place;

import org.sikuli.script.Region;

public class LaserOnAsteroid extends BaseClass{
    public LaserOnAsteroid(Region region, String pic) {
        super(region, pic);
    }

    public LaserOnAsteroid() {
        super(new Region(722,32,396,193), "laserAsteroid.png");
    }
}

package place;

import org.sikuli.script.Region;

public class LaserOnAsteroid extends BaseClass{
    public LaserOnAsteroid(Region region, String pic) {
        super(region, pic);
    }

    public LaserOnAsteroid() {
        super(new Region(1161,164,229,139), "laserOnAsteroid.png");
    }
}

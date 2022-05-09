import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import place.BaseClass;
import place.LaserOnAsteroid;
import place.PlagioclaseLock;

import java.util.Iterator;

public class Check {
    private BaseClass laser = new LaserOnAsteroid();
    private BaseClass asteroid = new PlagioclaseLock();

    //определяем количество лазеров в регионе (на астероиде)
    public int checkLaserOnAsteroid() {
        int result = inRegion(laser);
        return result;
    }

    //определяем количество захваченных астероидов
    public int checkLockPlagioclase() {
        int result = inRegion(asteroid);
        return result;
    }

    //ищем число картинок в регионе
    public int inRegion(BaseClass base) {
        int result = 0;
        try {
            Iterator<Match> matches = base.getRegion().findAll(base.getPic());
            while (matches.hasNext()) {
                result++;
                matches.next();
            }
        } catch (FindFailed e) {
            System.out.println(String.format("Изображение не обнаружено %s. Check.java -> inRegion()", base.getPic()));
        }
        return result;
    }
}
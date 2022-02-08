import org.sikuli.script.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Mining {
    private int range;
    private Robot robot;

    public Mining(int range, Robot robot) {
        this.range = range;
        this.robot = robot;
    }

    public int run() {
        int returnResult = 0;
        Match[] ee = findVolumeDistance();
        Match volume = ee[0];
        Match distance = ee[1];
        ArrayList<Region> list = new ArrayList<>(); //список с регионами в которых обнаружено м3
        if (volume != null && distance != null) {
            list = findM3(volume, distance);
        } else {
            System.out.println("Not found volume and distance.");
        }
        System.out.println(String.format("Found %s asteroids.", list.size()));
        for (Region findReg : list) {
            String str = findReg.text();
            String[] split = str.split("m3");
            try {
                int volumeAsteroid = Integer.parseInt(split[0].replace(" ", ""));
                String distanceAsteroid = split[1].replace(" ", "");
                String letter = "";
                if (distanceAsteroid.contains("km")) {
                    distanceAsteroid = distanceAsteroid.split("km")[0];
                    letter = "km";
                } else {
                    distanceAsteroid = distanceAsteroid.split("m")[0];
                    letter = "m";
                }
                //условие для захвата астероида. Первая скобка для киллометров, вторая если в метрах.
                if ((letter.length() > 1) && (Integer.parseInt(distanceAsteroid) < range) || letter.length() == 1) {
                    System.out.println(String.format("%s ---> Yes, and volume = %s", str, volumeAsteroid));
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    findReg.click();
                    robot.delay(500);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.delay(1000);
                    returnResult=volumeAsteroid;
                    break;
                } else {
                    System.out.println(String.format("%s ---> No", str));
                }
            } catch (NumberFormatException nfe) {
                System.out.println(String.format("Не могу распознать строку %s", str));
            }
        }
        return returnResult;
    }


    public Match[] findVolumeDistance() {
        Region region = new Screen().selectRegion("Выбери volume & distance");
        Match volume = null;
        Match distance = null;
        Match[] matches = new Match[2];
        try {
            volume = region.find("volume.png");
            distance = region.find("distance.png");
            System.out.println(String.format("volume x = %s, y = %s", volume.x, volume.y));
            System.out.println(String.format("distance x = %s, y = %s", distance.x, distance.y));
            matches[0] = volume;
            matches[1] = distance;
        } catch (FindFailed e) {
            System.out.println("File Mining.java Function findVolumeDistance(). Not match volume and distance.");
        }
        return matches;
    }

    public ArrayList<Region> findM3(Match volume, Match distance) {
        Region region = new Region(volume.x, volume.y, (distance.x + 80 - volume.x), 700);
        Region reg;
        ArrayList<Region> matches = new ArrayList<>();
        try {
            Iterator<Match> matchIterator = region.findAll("m3.png");
            while (matchIterator.hasNext()) {
                Match next = matchIterator.next();
                reg = new Region(volume.x, next.y, distance.x + 80 - volume.x, 20);
                matches.add(reg);
            }
        } catch (FindFailed e) {
            e.printStackTrace();
        }
        return matches;
    }
}


import dao.Asteroid;

import helper.ParseString;
import org.sikuli.script.*;
import place.BaseClass;
import place.PlagioclaseLock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//все начинается с calcTime().
public class Mining {
    private JFrame jFrame;
    int timeFillHangar = 0;
    int timeAsteroidEnd = 999;
    private int rangeLaser;
    private Robot robot;
    private Region regionForSearchM3;
    private int _volumeAsteroid = 0;
    private BaseClass plag = new PlagioclaseLock();
    private Asteroid asteroid = null;

    //Класс с методами для определения астероида для добычи.
    public Mining(int rangeLaser, Robot robot, Region regionForSearchM3, JFrame jFrame) {
        this.rangeLaser = rangeLaser;
        this.robot = robot;
        this.regionForSearchM3 = regionForSearchM3;
        this.jFrame = jFrame;
    }

    //задолбался указывать регион для поиска :)
    public Mining(Robot robot) {
        this.robot = robot;
        this.rangeLaser = 16;
        this.regionForSearchM3 = new Region(349, 528, 165, 293);
    }

    //возвращаем регион с астероидом для добычи.
    public Region asteroidForMining() {
        Region returnRegion = null;
        Asteroid asteroid1 = findAsteroidFromList(findM3(regionForSearchM3));
        if (asteroid1 != null) {
            returnRegion = asteroid1.getRegion();
            asteroid = asteroid1;
        } else {
            asteroid = new Asteroid(null, 0, 111);
        }

        return returnRegion;
    }

    //    ищем все регионы с м3, и преобразуем в регион для поиска с объемом руды и расстояния до астероида
    public List<Region> findM3(Region regionForSearch) {
        List<Region> list = new ArrayList<>();
        try {
            Iterator<Match> matchIterator = regionForSearch.findAll("m3.png");
            while (matchIterator.hasNext()) {
                Match match = matchIterator.next();
                list.add(new Region(regionForSearch.x, match.y, regionForSearch.w, match.h));
            }
        } catch (FindFailed e) {
            e.printStackTrace();
        }
        return list;
    }

    //запускаем оценку залежей астероидов.
    public boolean startSurvey() {
        boolean result = false;
        try {
            robot.keyPress(KeyEvent.VK_ALT);
            robot.delay(400);
            robot.keyPress(KeyEvent.VK_F1);
            robot.delay(400);
            robot.keyRelease(KeyEvent.VK_F1);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_ALT);
            robot.delay(5_000); //ждем пока отработаем модуль.
            result = true;
        } catch (Exception e) {
            System.out.println("Какая то шляпа. Mining.java -> startSurvey()");
        }
        return result;
    }

    //захватываем подходящий астероид для добычи
    public int lockAsteroid() {
        Region region = asteroidForMining();
        if (region != null) {
            try {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.delay(200);
                region.click();
                robot.delay(300);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                volumeAsteroid();
                robot.delay(3_500); //ждем захвата астероида
            } catch (Exception e) {
                System.out.println("Какая то шляпа. Mining.java -> lockAsteroid()");
                return -2;
//            System.exit(1);
            }
        } else
            _volumeAsteroid = 0;
        return _volumeAsteroid;
    }

    //вычисляем объем руды в астероиде
    public int volumeAsteroid() {
        _volumeAsteroid = asteroid.getVolume();
        return _volumeAsteroid;
    }

    //время добычи астероида
    public int calcTime() {
        int seconds = 0;
        int volumeAsteroid = lockAsteroid();
        if (volumeAsteroid > 0) {
            seconds = (int) (volumeAsteroid / (2.5 * 2)) + 1;
            System.out.println(String.format("Время добычи равно %s секунд.", seconds));
        }
        return seconds;
    }

    public boolean startMining(Control control) {
        boolean result = true;
        int timeFillShip = 980; //время заполнения трюма коробля в секундах
        int timeMiningAsteroid = 0;
        Region regionHundred = new Region(10,100,40,40);
        Region regionDec = new Region(10,200,40,40);
        regionHundred.highlightOn();
        regionDec.highlightOn();
        while (timeFillShip > 0) {
            if (timeMiningAsteroid <= 0) {
                control.removeLasers();
                control.removeAsteroid(plag);
                startSurvey();
                timeMiningAsteroid = calcTime();
                if (timeMiningAsteroid <= 0) {
                    result = false;
                    break;
                }
                control.startTwoLaser();
            }
            robot.delay(2_000);
            timeFillShip -= 2;
            timeMiningAsteroid -= 2;
            System.out.println(String.format("Время окончания загрузки %s, время выработки астероида %s ", timeFillShip, timeMiningAsteroid));
            jFrame.setTitle(String.format("H = %s, A = %s ", timeFillShip, timeMiningAsteroid));
            if (timeFillShip<100){
                regionHundred.highlightOff();
            }
            if (timeFillShip<10){
                regionDec.highlightOff();
            }
//            timeFillHangar = timeFillShip;
//            timeAsteroidEnd = timeMiningAsteroid;
        }
        return result;
    }

    //Ищем подходящий астероид из списка (Регионов).
    public Asteroid findAsteroidFromList(List<Region> regionList) {
        List<Asteroid> asteroidList = new ArrayList<>();
        ParseString parseString = new ParseString();
        for (Region r : regionList) {
            BufferedImage bi = r.getImage().get();
            String[] data = parseString.parseImageString(bi).split("m\\^3");
            int volume = 0;
            try {
                volume = Integer.parseInt(data[0]);
            } catch (NumberFormatException e) {
                System.out.println("Не могу распознать строку." + data[0]);
            }
            String rangeStr = null;
            try {
                rangeStr = data[1];
                boolean kmIs = rangeStr.contains("km");
                String separator = kmIs ? "km" : "m";
                int range = Integer.parseInt(rangeStr.split(separator)[0]);
                if (range < 16 && volume > 0) {
                    System.out.println(String.format("Region x = %s  y = %s accept. Volume is %s, range is %s", r.getX(), r.getY(), volume, range));
                    asteroidList.add(new Asteroid(r, volume, range));
                } else {
                    if (separator.equals("m")) {
                        System.out.println(String.format("Region x = %s  y = %s accept. Volume is %s, range is %s", r.getX(), r.getY(), volume, range));
                        asteroidList.add(new Asteroid(r, volume, range));
                    } else {
                        System.out.println(String.format("Region x = %s  y = %s Not Good for Lock. Volume is %s, range is %s", r.getX(), r.getY(), volume, range));
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Не корректно распознана строка");
            }
        }
        return asteroidList.size() > 0 ? asteroidList.get(0) : null;
    }


}
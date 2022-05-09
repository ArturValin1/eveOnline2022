import org.sikuli.script.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mining {
    private int range;
    private Robot robot;
    private Region regionForSearchM3;
    private int _volumeAsteroid = 0;

    //Класс с методами для определения астероида для добычи.
    public Mining(int range, Robot robot, Region regionForSearchM3) {
        this.range = range;
        this.robot = robot;
        this.regionForSearchM3 = regionForSearchM3;
    }

    //возвращаем регион с астероидом для добычи.
    public Region asteroidForMining() {
        Region returnRegion = null;
        try {
            List<Region> list = findM3(regionForSearchM3); //список с регионами в которых обнаружено м3
            for (Region findReg : list) {
                String str = findReg.text().replace(" ", "");
                String[] split = str.split("m3");
                String distance = split[1];
                if (distance.endsWith("km")) {
                    int range = Integer.parseInt(distance.split("km")[0]);
                    if (range < 16) { //16 км по умолчанию, нужно поменять если больше дальность добычи.
                        returnRegion = findReg;
                        break;
                    }
                } else if (distance.endsWith("m")) {           //может и не нужно это условие
                    returnRegion = findReg;
                    break;
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Не могу корректно распознать число. Ошибка в классе Mining.java функция asteroidForMining. ");
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
        try {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(200);
            region.click();
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            volumeAsteroid(region);
            robot.delay(3_500); //ждем захвата астероида
        } catch (Exception e) {
            System.out.println("Какая то шляпа. Mining.java -> lockAsteroid()");
        }
        return _volumeAsteroid;
    }

    //вычисляем объем руды в астероиде
    public int volumeAsteroid(Region region) {
        _volumeAsteroid = 0;
        String str = region.text().split("m3")[0].replace(" ", "");
        try {
            _volumeAsteroid = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            System.out.println("Не могу распознать число. " + str);
        } catch (Exception e) {
            System.out.println("Какая то шляпа. Mining.java -> volumeAsteroid()");
        }
        return _volumeAsteroid;
    }
    //запускаем лазеры для добычи
    public void startLaser(){
        robot.delay(200);
        robot.keyPress(KeyEvent.VK_F1);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_F2);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_F1);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_F2);
        robot.delay(200);
    }
}
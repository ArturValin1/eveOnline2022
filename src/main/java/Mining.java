import dao.Asteroid;
import org.sikuli.script.*;
import place.BaseClass;
import place.PlagioclaseLock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//все начинается с calcTime().
public class Mining {
    private int rangeLaser;
    private Robot robot;
    private Region regionForSearchM3;
    private int _volumeAsteroid = 0;
    private BaseClass plag = new PlagioclaseLock();
    private Asteroid asteroid = null;

    //Класс с методами для определения астероида для добычи.
    public Mining(int rangeLaser, Robot robot, Region regionForSearchM3) {
        this.rangeLaser = rangeLaser;
        this.robot = robot;
        this.regionForSearchM3 = regionForSearchM3;
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

//        try {
//            List<Region> list = findM3(regionForSearchM3); //список с регионами в которых обнаружено м3
//
//            for (Region findReg : list) {
//                String str = findReg.text().replace(" ", "");
//                String[] split = str.split("m3");
//                String distance = split[1];
//                if (distance.endsWith("km")) {
//                    int range = Integer.parseInt(distance.split("km")[0]);
//                    if (range < rangeLaser) { //16 км по умолчанию, нужно поменять если больше дальность добычи.
//                        returnRegion = findReg;
//                        break;
//                    }
//                } else if (distance.endsWith("m")) {           //может и не нужно это условие
//                    returnRegion = findReg;
//                    break;
//                }
//            }
//        } catch (NumberFormatException nfe) {
//            System.out.println("Не могу корректно распознать число. Ошибка в классе Mining.java функция asteroidForMining. ");
//        }
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
            volumeAsteroid();
            robot.delay(3_500); //ждем захвата астероида
        } catch (Exception e) {
            System.out.println("Какая то шляпа. Mining.java -> lockAsteroid()");
        }
        return _volumeAsteroid;
    }

    //вычисляем объем руды в астероиде
    public int volumeAsteroid() {
        return _volumeAsteroid = asteroid.getVolume();
    }

    public int volumeAsteroid(Region region) {
        _volumeAsteroid = 0;
        String str = region.text().split("m3")[0].replace(" ", "");
        try {
            _volumeAsteroid = Integer.parseInt(str);
            System.out.println(String.format("Объем захваченного астероида равен %s m3", _volumeAsteroid));
        } catch (NumberFormatException nfe) {
            System.out.println("Не могу распознать число. " + str);
        } catch (Exception e) {
            System.out.println("Какая то шляпа. Mining.java -> volumeAsteroid()");
        }
        return _volumeAsteroid;
    }

    //время добычи астероида
    public int calcTime() {
        int seconds = 0;
        seconds = (int) (lockAsteroid() / (2.4 * 2)) + 1;
        System.out.println(String.format("Время добычи равно %s секунд.", seconds));
        return seconds;
    }

    public boolean startMining(Control control) {
        boolean result = true;
        int timeFillShip = 1_000; //время заполнения трюма коробля в секундах
        int timeMiningAsteroid = 0;
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
        }
        return result;
    }

    public Asteroid findAsteroidFromList(List<Region> regionList) {
        BlackAndWhite bw = new BlackAndWhite();
        String m3 = "src/main/resources/images/R1900x600/m3black.png";
        BufferedImage m3Image = null;
        List<Asteroid> asteroidList = new ArrayList<>();
        try {
            m3Image = bw.cutPicture(ImageIO.read(new File(m3)));
            BufferedImage finalM3Image = m3Image;
            regionList.forEach(e -> {
                BufferedImage colorImage = e.getImage().get();
                BufferedImage blackImage = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
                Graphics2D graphics2D = blackImage.createGraphics();
                graphics2D.drawImage(colorImage, 0, 0, null);
                BufferedImage[] split = bw.splitImageToVolumeAndRange(blackImage, finalM3Image);
                int[] res = bw.volumeAndRange(split);
                if (res[0] > 0 && res[1] < 17) {
                    System.out.println(String.format("Region x = %s  y = %s accept. Volume is %s, range is %s", e.getX(), e.getY(), res[0], res[1]));
                    asteroidList.add(new Asteroid(e, res[0], res[1]));
                } else
                    System.out.println(String.format("Region x = %s  y = %s EPSENT. Volume is %s, range is %s", e.getX(), e.getY(), res[0], res[1]));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return asteroidList.size() > 0 ? asteroidList.get(0) : null;
    }


}
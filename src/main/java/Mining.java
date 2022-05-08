import org.sikuli.script.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mining {
    private int range;
    private Robot robot;
    private Region regionForSearchM3;

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
}
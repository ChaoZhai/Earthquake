package uk.ac.ncl.chen.monitor;

import uk.ac.ncl.chen.entity.Earthquake;
import uk.ac.ncl.chen.entity.Observatory;

import java.util.*;

/**
 * @ClassName Monitoring
 * @Description: TODO
 * @Author BENY
 * @Date 2019/11/8
 * @Version V1.0
 **/
public class Monitoring {

    /**
     * The observatory with the largest average earthquake magnitude
     *
     * @param observatoryList all Observatory information
     * @return
     */
    public void avgMAXObs(List<Observatory> observatoryList) {
        List<Double> integerList = new ArrayList<>();
        for (Observatory observatory : observatoryList) {
            integerList.add(observatory.getAvEarthquake());
        }
        Double max = Collections.max(integerList);
        for (Observatory observatory : observatoryList) {
            if (Double.doubleToLongBits(observatory.getAvEarthquake()) == Double.doubleToLongBits(max)) {
                System.out.println("The observatory with the largest average earthquake magnitude is :" + observatory.toString());
            }
        }

    }


    /**
     * The largest magnitude earthquake ever recorded
     *
     * @param observatoryList all Observatory information
     * @return
     */
    public Earthquake maxEarthquake(List<Observatory> observatoryList) {
        List<Earthquake> earthquakeList = new ArrayList<>();
        for (Observatory observatory : observatoryList) {
            if(observatory.getMaxEq()!= null){
                earthquakeList.add(observatory.getMaxEq());
            }

        }
        Optional<Earthquake> userOp = earthquakeList.stream().filter(Objects::nonNull).max(Comparator.comparingDouble(Earthquake::getMagnitude));
        Earthquake max = userOp.orElse(new Earthquake());
        return max;
    }

    /**
     * @param observatoryList all observatory information
     * @param magnitude       a given number
     * @return A list of all observatory earthquakes than a given number
     */
    public List<Earthquake> getRecThanGiven(List<Observatory> observatoryList, Integer magnitude) {
        List<Earthquake> earthquakeList = new ArrayList<>();
        for (Observatory observatory : observatoryList) {
            earthquakeList.addAll(observatory.getRecThanGiven(magnitude));
        }
        return earthquakeList;

    }


}

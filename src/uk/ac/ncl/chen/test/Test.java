package uk.ac.ncl.chen.test;

import uk.ac.ncl.chen.entity.Earthquake;
import uk.ac.ncl.chen.entity.Observatory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author BENY
 * @Date 2019/11/11
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {


        Earthquake e1=new Earthquake();
        e1.setMagnitude(3.4);
        e1.setLatitude("23");
        e1.setLongitude("2332");
        e1.setYear("1992");
        Earthquake e2=new Earthquake();
        e2.setMagnitude(5.4);
        e2.setLatitude("23");
        e2.setLongitude("2332");
        e2.setYear("1992");
        Earthquake e3=new Earthquake();
        e3.setMagnitude(7.8);
        e3.setLatitude("23");
        e3.setLongitude("2332");
        e3.setYear("1992");

        List<Earthquake> hasRecord=new ArrayList<>();
        hasRecord.add(e1);
        hasRecord.add(e2);
        hasRecord.add(e3);

        Double avEarthquake = getAvEarthquake(hasRecord);
        System.out.println(avEarthquake);
    }

    public static Double getAvEarthquake(List<Earthquake> hasRecord){
        DecimalFormat decimalFormat=new DecimalFormat("#.#");
        if(hasRecord.size()>0){
            Double sum=0.0;
            for (Earthquake eq:hasRecord) {
                sum+=eq.getMagnitude();
            }
            return Double.valueOf(decimalFormat.format(sum/hasRecord.size()));
        }else {
            //   System.out.println("no has record earthquake!");
            return 0.0;
        }
    }
}

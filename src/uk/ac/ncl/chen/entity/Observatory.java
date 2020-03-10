package uk.ac.ncl.chen.entity;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @ClassName Observatory
 * @Description: retrieve the name of the observatory, the name of the country in which it is located, the year in which earthquake observations started, the area covered by the observatory (in square kilometres) and a list of Earthquake events that it has recorded
 * @Author BENY
 * @Date 2019/11/8
 * @Version V1.0
 **/
public class Observatory {

    private String observatoryName;
    private String country;
    private String startYear;
    private String areaCovered;
    private List<Earthquake> hasRecord;


    public Observatory(String observatoryName, String country, String startYear, String areaCovered) {
        this.observatoryName = observatoryName;
        this.country = country;
        this.startYear = startYear;
        this.areaCovered = areaCovered;
        this.hasRecord = new ArrayList<>();
    }

    public String getObservatoryName() {
        return observatoryName;
    }

    public void setObservatoryName(String observatoryName) {
        this.observatoryName = observatoryName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public List<Earthquake> getHasRecord() {
        return hasRecord;
    }

    public void setHasRecord(List<Earthquake> hasRecord) {
        this.hasRecord = hasRecord;
    }


    @Override
    public String toString() {
        return "Observatory{" +
                "observatoryName='" + observatoryName + '\'' +
                ", country='" + country + '\'' +
                ", startYear='" + startYear + '\'' +
                ", areaCovered='" + areaCovered + '\'' +
                ", hasRecord=" + hasRecord +
                '}';
    }


    /**
     * The largest magnitude earthquake recorded by the observatory
     *
     * @return The largest magnitude earthquake
     */
    public Earthquake getMaxEq() {
        if(hasRecord.size()>0){
            Optional<Earthquake > userOp= hasRecord.stream().filter(Objects::nonNull).max(Comparator.comparingDouble(Earthquake ::getMagnitude));
            Earthquake maxEmp = userOp.orElse(new Earthquake());
            return maxEmp;
        }else {
            return null;
        }

    }

    /**
     *The average earthquake magnitude recorded at the observatory
     * @return average earthquake magnitude
     */
    public Double getAvEarthquake(){
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


    /**
     *A list of all earthquakes recorded at the observatory with a magnitude greater than a given number.
     * @param magnitude  a given number
     * @return A list of all earthquakes than a given number
     */
    public List<Earthquake> getRecThanGiven(Integer magnitude){
        List<Earthquake> earthquakes=new ArrayList<>();
        for (Earthquake eq:hasRecord) {
            if(eq.getMagnitude()>magnitude){
                earthquakes.add(eq);
            }

        }
        return earthquakes;
    }


}

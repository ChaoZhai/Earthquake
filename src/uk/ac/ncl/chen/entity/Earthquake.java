package uk.ac.ncl.chen.entity;

/**
 * @ClassName Earthquake
 * @Description: retrieve information about the magnitude, position (latitude and longitude) and year of the event.
 * @Author BENY
 * @Date 2019/11/8
 * @Version V1.0
 **/
public class Earthquake  implements Comparable{

    //0~12
    private Double magnitude;
    private String latitude;
    private String longitude;
    private String year;

    public Earthquake(Double magnitude, String latitude, String longitude, String year) {
        this.magnitude = magnitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.year = year;
    }

    public Earthquake() {

    }


    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "magnitude='" + magnitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Earthquake eq=(Earthquake) o;
        return magnitude.compareTo(eq.getMagnitude());
    }
}

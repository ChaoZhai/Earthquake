package uk.ac.ncl.chen.main;


import uk.ac.ncl.chen.entity.Earthquake;
import uk.ac.ncl.chen.entity.Observatory;
import uk.ac.ncl.chen.monitor.Monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @ClassName MonitoringIO
 * @Description: menu and input and output
 * @Author BENY
 * @Date 2019/11/8
 * @Version V1.0
 **/
public class MonitoringIO {

    //initialization
    public static List<Observatory> observatoryList = new ArrayList<>();
    public static String pattern = "\\d{4}";
    public static Monitoring monitoring = new Monitoring();

    public static void main(String[] args) {
        earthquakeMenu();
    }

    public static void earthquakeMenu() {
        System.out.println("\n");
        System.out.println("Welcome to EarthquakeMenu");
        System.out.println("1.Enter observatory data");
        System.out.println("2.Enter earthquake data ");
        System.out.println("3.Show largest average earthquake");
        System.out.println("4.Show largest earthquake ever");
        System.out.println("5.Show all earthquakes with magnitude greater than a given number");
        System.out.println("6.exit");
        System.out.println("Please enter your choice number:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (isNum(str)) {
            switch (Integer.valueOf(str)) {
                case 1:
                    inputObservatory(scanner);
                    break;
                case 2:
                    inputEarthquake(scanner);
                    break;
                case 3:
                    ShowLarAvEQ();
                    break;
                case 4:
                    showMaxEq();
                    break;
                case 5:
                    ShowThanGiven(scanner);
                    break;
                case 6:
                    System.exit(0);
                default:
                    inputWrongInformation();
            }
        } else {
            inputWrongInformation();
        }
        earthquakeMenu();
    }


    /**
     * Determine if str is a number
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }


    /**
     * input wrong selection prompt
     */
    public static void inputWrongInformation() {
        System.out.println("The selection you entered does not exist, please re-select!");
        earthquakeMenu();
    }

    /**
     * case 1: Enter observatory data
     */
    public static void inputObservatory(Scanner scanner) {
        System.out.println("please enter observatory name:");
        String name = scanner.nextLine();
        System.out.println("please enter observatory country:");
        String country = scanner.nextLine();
        System.out.println("please enter observatory startYear(YYYY):");
        String startYear = scanner.nextLine();
        boolean isMatch = Pattern.matches(pattern, startYear);

        if (!isMatch) {
            System.out.println("startYear must be four digits number , like: 1990" +
                    "\n" + "Please enter information again!");
            return;
        }

        System.out.println("please enter observatory areaCovered:");
        String areaCovered = scanner.nextLine();
        Observatory observatory = new Observatory(name, country, startYear, areaCovered);
        observatoryList.add(observatory);
        System.out.println(observatory.toString() + "\n" + "information saved successful!");

    }

    /**
     * case 2: Enter earthquake data
     */
    public static void inputEarthquake(Scanner scanner) {
        if (observatoryList.size() > 0) {
            System.out.println("please select the observatory:");
            Integer i = 1;
            for (Observatory observatory : observatoryList) {
                System.out.println(i + "." + observatory.toString());
                i++;
            }
            String obs = scanner.nextLine();
            if (isNum(obs) && !(Integer.valueOf(obs) > observatoryList.size())) {
                List<Earthquake> earthquakeList = new ArrayList<>();
                System.out.println("please enter earthquake magnitude(0~12):");
                String magnitude = scanner.nextLine().trim();
                if (!(isNum(magnitude) && Double.valueOf(magnitude) > 0 && Double.valueOf(magnitude) < 13)) {
                    System.out.println("magnitude mange between 0 and 12 , " +
                            "\n" + "Please enter information again!");
                    return;
                }
                System.out.println("please enter earthquake latitude:");
                String latitude = scanner.nextLine();
                System.out.println("please enter earthquake longitude:");
                String longitude = scanner.nextLine();
                System.out.println("please enter earthquake year(YYYY):");
                String year = scanner.nextLine();

                boolean isMatch = Pattern.matches(pattern, year);
                if (!isMatch) {
                    System.out.println("year must be four digits number , like: 1990" +
                            "\n" + "Please enter information again!");
                    return;
                }
                Earthquake earthquake = new Earthquake(Double.valueOf(magnitude), latitude, longitude, year);
                earthquakeList.add(earthquake);
                Observatory observatory = observatoryList.get(Integer.valueOf(obs) - 1);
                if (observatory.getHasRecord().size() > 0) {
                    earthquakeList.addAll(observatory.getHasRecord());
                }
                observatory.setHasRecord(earthquakeList);
                System.out.println(observatory.toString() + "\n" + "information saved successful!");
            } else {
                inputWrongInformation();
            }
        } else {
            System.out.println("please enter observatory information first!");
        }


    }

    /**
     * case 3: Show largest average earthquake
     */
    public static void ShowLarAvEQ() {
        if (observatoryList.size() > 0) {
            monitoring.avgMAXObs(observatoryList);
        } else {
            System.out.println("No monitoring record! please enter observatory information first!");
        }

    }

    /**
     * case 4: largest earthquake ever
     */
    public static void showMaxEq() {
        if (observatoryList.size() > 0) {
            Earthquake earthquake = monitoring.maxEarthquake(observatoryList);
            System.out.println("Largest earthquake ever :" + earthquake.toString());
        } else {
            System.out.println("No monitoring record! please enter observatory information first!");
        }

    }

    /**
     * case 5: Show all earthquakes with magnitude greater than a given number
     */
    public static void ShowThanGiven(Scanner scanner) {
        if (observatoryList.size() > 0) {
            System.out.println("Please enter number:");
            String givenNo = scanner.nextLine();
            if (!(isNum(givenNo) && Double.valueOf(givenNo) > 0 && Double.valueOf(givenNo) < 13)) {
                System.out.println("magnitude mange between 0 and 12 , " +
                        "\n" + "Please enter information again!");
                return;
            }
            List<Earthquake> recThanGiven = monitoring.getRecThanGiven(observatoryList, Integer.valueOf(givenNo));
            System.out.println("Show all earthquakes with magnitude greater than " + givenNo + " :");
            for (Earthquake earthquake : recThanGiven) {
                System.out.println(earthquake.toString());
            }

        } else {
            System.out.println("No monitoring record! please enter observatory information first!");
        }
    }
}

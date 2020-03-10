package uk.ac.ncl.chen.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName CloudComputing
 * @Description: TODO
 * @Author BENY
 * @Date 2019/12/9
 * @Version V1.0
 **/
public class CloudComputing {


    public static void main(String[] args) {
        //  String url ="https://localhost/primecheck";
        /*String url ="https://www.baidu.com";
        String NomalOrPoisson = "POISSON";

        double miu=2.0;
        double sigma2=12.0;
        double lamda=1.0;*/
        Boolean isNormal = true;
        String url = args[0];
        String NomalOrPoisson = args[1];
        double miu = Double.parseDouble(args[2]);
        double sigma2 = Double.parseDouble(args[3]);
        double lamda = Double.parseDouble(args[4]);
        Integer times = Integer.valueOf(args[5]);


        //(Normal, Poisson)
        if ("POISSON".equals(NomalOrPoisson.toUpperCase())) {
            isNormal = false;
        }


        Process process = new Process();
        Thread thread = new Thread(process);
        thread.setName("线程Process");
        thread.start();

        try {
            for (int i = 0; i < 10; i++) {
                double random = 0;
                System.out.println("Try "+ i + 1 + " Times");
                System.out.println(execCurl(url));
                //阻塞main线程，休眠random
                if (isNormal) {
                    random = NormRand(miu, sigma2);
                } else {
                    random = Prand(lamda);
                }
                System.out.println("wait "+random+"....");
                Thread.sleep((long) random);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 正太分布
     *
     * @param miu
     * @param sigma2
     * @return
     */
    public static double NormRand(double miu, double sigma2) {
        double N = 12;
        double x = 0, temp = N;
        do {
            x = 0;
            for (int i = 0; i < N; i++)
                x = x + (Math.random());
            x = (x - temp / 2) / (Math.sqrt(temp / 12));
            x = miu + x * Math.sqrt(sigma2);
        } while (x <= 0);         //在此我把小于0的数排除掉了
        return x;
    }


    /**
     * 泊松分布
     *
     * @param lamda
     * @return
     */
    public static double Prand(double lamda) {     // 泊松分布
        double x = 0, b = 1, c = Math.exp(-lamda), u;
        do {
            u = Math.random();
            b *= u;
            if (b >= c)
                x++;
        } while (b >= c);
        return x;
    }


    /**
     * curl service
     *
     * @param url
     * @return
     */
    public static String execCurl(String url) {
        // = "https://www.baidu.com";
        String[] cmds = {"curl", url};
        ProcessBuilder process = new ProcessBuilder(cmds);
        java.lang.Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;


    }
}

/**
 * 线程类
 */
class Process implements Runnable {

    @Override
    public void run() {


    }
}

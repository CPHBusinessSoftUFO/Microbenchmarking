/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbenchmarking.framework;

/**
 *
 * @author kasper
 */
public class CalibratingTimer {

    public static double Mark5( Runnable code ) {
        int n = 10, count = 1, totalCount = 0;
        double dummy = 0.0, runningTime = 0.0;
        do {
            count *= 2;
            double st = 0.0, sst = 0.0;
            for ( int j = 0; j < n; j++ ) {
                Timer t = new Timer();
                for ( int i = 0; i < count; i++ )
                    code.run();
                runningTime = t.check();
                double time = runningTime * 1e9 / count;
                st += time;
                sst += time * time;
                totalCount += count;
            }
            double mean = st / n, sdev = Math.sqrt( ( sst - mean * mean * n ) / ( n - 1 ) );
            System.out.printf( "%6.1f ns +/- %8.2f %10d%n", mean, sdev, count );
            
        } while ( runningTime < 0.25 && count < Integer.MAX_VALUE / 2 );

        return dummy / totalCount;
    }

    class TimerParameters {
        int N;
        Double mean;
        Double std;
    }
}

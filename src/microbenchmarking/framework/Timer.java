/*
 * Code by Peter Sestoft from https://www.itu.dk/people/sestoft/papers/benchmarking.pdf
 * The code is based on the code referenced in section 12 of the above
 */
package microbenchmarking.framework;

/**
 * This class is used to time code. Internally it is measured using Javas
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#nanoTime">nanoTime()</a>
 * method.
 *
 * The idiomatic way to use it is
 * ````java
 * Timer clock = new Timer(); // Timer starts right away
 * //... code block to be timed goes here
 * Double consumedTime = clock.check();
 * ````
 * 
 * 
 * @author Peter Sestoft, comments by Kasper Østerbye
 */
public class Timer {
    private long start, spent = 0;

    /**
     * Creates a new instance of timer which starts to count right away
     */
    public Timer() {
        play();
    }
    /**
     * Returns how long time the timer has been running.
     * 
     * | name | how many to a second ||
     * |----|---|
     * | second | 1 ||
     * | milli second | 1000 (1e3) ||
     * | micro second | 1_000_000 (1e6) ||
     * | nano second  | 1_000_000_000 (1e9) ||
     * @return time in seconds
     */
    public double check() {
        return ( System.nanoTime() - start + spent ) / 1e9;
    }
    /**
     * Stops the timer until play is pressed (or timer is read using 
     * {@link #check() check}). It can be used as:
     * ````java
     *  Timer clock2 = new Timer(); // Timer starts right away
     *  //... block 1 to be timed
     *  clock2.pause(); // Stop timing
     *  // code that should not be timed
     *  clock2.play();
     *  //... block 2 to be timed
     *  Double consumedTime2 = clock.check();
     *  // Consumed time from block 1 and block 2
     * ````
     */
    public void pause() {
        spent += System.nanoTime() - start;
    }

    /**
     * Starts (or restarts) the timer. The timer is automatically started in
     * constructor. See example in {@link #pause() pause}.
     */
    public void play() {
        start = System.nanoTime();
    }

    private void sampleCodeForDocAbove() {
        // Just to make sure it is syntactically correct
        Timer clock = new Timer(); // Timer starts right away
        //... code block to be timed goes here
        Double consumedTime = clock.check();

        // And
        Timer clock2 = new Timer(); // Timer starts right away
        //... block 1 to be timed
        clock2.pause(); // Stop timing
        // code that should not be timed
        clock2.play();
        //... block 2 to be timed
        Double consumedTime2 = clock.check();
        // Consumed time from block 1 and block 2
    }
}

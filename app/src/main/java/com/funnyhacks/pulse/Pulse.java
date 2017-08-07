package com.funnyhacks.pulse;

/**
 * Created by ksandom on 07/08/17.
 */

public class Pulse {

    private long lastBeat = 0;

    protected int getBPM() {
        long now = System.currentTimeMillis();

        // On the first occurence, we don't have anything to calculate, so just store what we need and return 0.
        if (lastBeat < 0) {
            lastBeat = now;

            // TODO Make a comment.
            return -1;
        }

        // Make a calculation.
        long difference = (now-lastBeat);

        lastBeat = now;
        int realTimeBPM = Math.round(60000/difference);

        // Return the result.
        return realTimeBPM;
    }

}

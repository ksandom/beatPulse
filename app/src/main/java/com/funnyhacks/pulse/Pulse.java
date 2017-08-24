package com.funnyhacks.pulse;

/**
 * Created by ksandom on 07/08/17.
 */

public class Pulse {

    // Raw data.
    private long now = 0;
    private long lastBeat = 0;
    private long initialBeat = 0;
    private int beatCount = 0;

    // Calculated data.
    private int realtimeBMP = 0;
    private int averageBMP = 0;

    // Other.
    private String message = "";

    public void beat() {
        now = System.currentTimeMillis();
        beatCount++;

        resetMessage();

        if (lastBeat < 1) {
            // On the first occurence, we don't have anything to calculate, so just reset.
            firstBeat();
            failed("Good work, keep going!");
        }
        else {
            // Do calculations.
            calculateRealtimeBMP();
            calculateAverageBMP();
        }
    }

    public void firstBeat() {
        reset();
        lastBeat = now;
        initialBeat = now;
    }

    public void reset() {
        // Feedback.
        if (lastBeat > 0) {
            failed("Reset, ready to start again.");
        }
        else {
            failed("Already reset, just hit the beat button to continue.");
        }

        // Raw data.
        lastBeat = 0;
        initialBeat = 0;
        beatCount = 0;

        // Calculated data.
        realtimeBMP = -1;
        averageBMP = -1;
    }

    private void calculateRealtimeBMP() {
        // Make a calculation.
        long difference = (now - lastBeat);

        lastBeat = now;
        try {
            realtimeBMP = Math.round(60000 / difference);
        }
        catch (Throwable e) { // For divide by 0.
            realtimeBMP=-2;
            failed("Too many fingers?");
        }
    }

    private void calculateAverageBMP() {
        long difference = (now - initialBeat);
        try {
            averageBMP = Math.round(beatCount * 60000 / difference);
        }
        catch (Throwable e) {
            averageBMP = -2;
            failed("Too soon?");
        }
    }

    public int getAverageBMP() {
        return averageBMP;
    }

    public int getRealtimeBPM() {
        return realtimeBMP;
    }

    public int getCount() {
        return beatCount;
    }

    public int getDuration() {
        if (initialBeat > 0) {
            return Math.round((now-initialBeat)/1000);
        }
        else {
            return 0;
        }
    }

    public String getMessage() {
        return message;
    }

    private void failed(String newMessage) {
        message = newMessage;
    }

    private void resetMessage() {
        message = "";
    }
}

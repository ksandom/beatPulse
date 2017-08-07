package com.funnyhacks.pulse;

/**
 * Created by ksandom on 07/08/17.
 */

public class Pulse {

    private long now = 0;
    private long lastBeat = 0;
    private int realtimeBMP = 0;

    private String message = "";

    public void beat() {
        now = System.currentTimeMillis();

        calculateBMP();
    }

    private void calculateBMP() {
        resetMessage();

        // On the first occurence, we don't have anything to calculate, so just store what we need and return 0.
        if (lastBeat < 0) {
            lastBeat = now;

            failed("Good work, keep going!");
            realtimeBMP = -1;
        }

        // Make a calculation.
        long difference = (now-lastBeat);

        lastBeat = now;
        realtimeBMP = Math.round(60000/difference);
    }

    public int getBPM() {
        return realtimeBMP;
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

package com.funnyhacks.pulse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private long lastBeat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.beat);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // getBPM and put it to the user.
                int bpm = getBPM();
                String bmpOutput = Integer.toString(bpm) + " BPM";

                final TextView beats = (TextView) findViewById(R.id.beats);
                beats.setText(bmpOutput);

                // TODO Make a comment based on the value.
                //final TextView comment = (TextView) findViewById(R.id.comment);
                //comment.setText(Long.toString(now)+"-"+Long.toString(lastBeat)+" "+Long.toString(difference));

            }
        });

    }

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

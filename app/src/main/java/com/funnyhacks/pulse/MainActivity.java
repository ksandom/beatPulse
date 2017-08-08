package com.funnyhacks.pulse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Pulse pulse = new Pulse();
    // Tab2
    private TextView beatsSimple;
    private TextView comment;

    // Tab3
    private TextView beatsAverage;
    private TextView beatsRealtime2;
    private TextView beatsCount;
    private TextView beatsDuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        While it won't take long to start everything up, even on an old phone, we may as well set
        the welcome message first so the user can see that while stuff gets loaded.
        */
        // Set welcome message.
        String welcomeMessage = "Hi!";
        welcomeMessage += "\n\nThis is a really basic heart beat monitor to give you results really fast." +
                "\nThere are two are two distinct pulses that come close together. If you feel" +
                " both just press the button once. (Otherwise the estimate will flail around" +
                " like a shirt caught in a car door.)" +
                "\n\nMeasuring yourself and taping the screen is quite hard (it can be done). I" +
                " suggest getting a friend to help you. - You can hold the device, and they can" +
                " feel your pulse and tap the button with the other hand." +
                "\n\nMost measurements are variations of BMP (Beats Per Minute)." +
                "\n\nIMPORTANT" +
                "\nNothing in this app should be taken as medical advice. If you" +
                " need medical advice or have any medical questions, please see your doctor.";

        final TextView welcome = (TextView) findViewById(R.id.welcomeMessage);
        welcome.setText(welcomeMessage);


        // Set up the interface - Tab1
        beatsSimple = (TextView) findViewById(R.id.beatsSimple);
        comment = (TextView) findViewById(R.id.comment);

        // Set up the interface - Tab2
        beatsAverage = (TextView) findViewById(R.id.beatAverage);
        beatsRealtime2 = (TextView) findViewById(R.id.beatsRealtime2);
        beatsCount = (TextView) findViewById(R.id.beatsCount);
        beatsDuration = (TextView) findViewById(R.id.beatsDuration);


        // Set up the buttons.
        final Button button1 = (Button) findViewById(R.id.beat1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Trigger a beat, and update the interface accordingly.
                pulse.beat();
                updateInterface();
            }
        });
        final Button button2 = (Button) findViewById(R.id.beat2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Trigger a beat, and update the interface accordingly.
                pulse.beat();
                updateInterface();
            }
        });



        // Set up tabs.
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec;

        //Tab 1
        spec = host.newTabSpec("Welcome");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Welcome");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Do it!");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Do it!");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Do it with details!");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Do it with details!");
        host.addTab(spec);
    }

    private void updateInterface() {
        // Tab 1
        beatsSimple.setText(Integer.toString(pulse.getAverageBMP()));
        comment.setText(pulse.getMessage());


        //Tab 2
        beatsAverage.setText(Integer.toString(pulse.getAverageBMP()));
        beatsRealtime2.setText(Integer.toString(pulse.getRealtimeBPM()));
        beatsCount.setText(Integer.toString(pulse.getCount()));
        beatsDuration.setText(Integer.toString(pulse.getDuration()));
    }
}

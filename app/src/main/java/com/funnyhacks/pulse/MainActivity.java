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
    private TextView beats1;
    private TextView beats2;
    private TextView comment;

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
                " both just press the button once. (Otherwise the estimate will flail around)" +
                " like a shirt caught in a car door." +
                "\n\nIMPORTANT" +
                "\nNothing in this app should be taken as medical advice. If you" +
                " need medical advice or have any medical questions, please see your doctor.";

        final TextView welcome = (TextView) findViewById(R.id.welcomeMessage);
        welcome.setText(welcomeMessage);


        // Set up the interface
        beats1 = (TextView) findViewById(R.id.beats1);
        beats2 = (TextView) findViewById(R.id.beats2);
        comment = (TextView) findViewById(R.id.comment);


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
        // Output the realtime BMP.
        String bmpOutput = Integer.toString(pulse.getRealtimeBPM()) + " BPM";
        beats1.setText(bmpOutput);
        beats2.setText(bmpOutput);

        // Make a comment based what's happening.
        comment.setText(pulse.getMessage());
    }
}

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


        // Set up the button.
        final Button button = (Button) findViewById(R.id.beat);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // getBPM and put it to the user.
                pulse.beat();
                int bpm = pulse.getBPM();
                String bmpOutput = Integer.toString(bpm) + " BPM";

                final TextView beats = (TextView) findViewById(R.id.beats);
                beats.setText(bmpOutput);

                // Make a comment based what's happening.
                final TextView comment = (TextView) findViewById(R.id.comment);
                comment.setText(pulse.getMessage());

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

    }

}

package com.funnyhacks.pulse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Stuff for rotating sanely.
    private Pulse pulse = Pulse.getInstance();
    private InterfaceHelper interfaceHelper = InterfaceHelper.getInstance();

    // Tab management.
    TabHost host;

    // Tab2
    private TextView beatsSimple;
    private TextView comment;

    // Tab3
    private TextView beatsAverage;
    private TextView beatsRealtime2;
    private TextView beatsCount;
    private TextView beatsDuration;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        interfaceHelper.setTab(host.getCurrentTab());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the interface - Tab1
        beatsSimple = (TextView) findViewById(R.id.beatsSimple);
        comment = (TextView) findViewById(R.id.comment);

        // Set up the interface - Tab2
        beatsAverage = (TextView) findViewById(R.id.beatAverage);
        beatsRealtime2 = (TextView) findViewById(R.id.beatsRealtime2);
        beatsCount = (TextView) findViewById(R.id.beatsCount);
        beatsDuration = (TextView) findViewById(R.id.beatsDuration);


        // Set up the buttons.
        final Button beat1 = (Button) findViewById(R.id.beat1);
        beat1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Trigger a beat, and update the interface accordingly.
                pulse.beat();
                updateInterface();
            }
        });
        final Button beat2 = (Button) findViewById(R.id.beat2);
        beat2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Trigger a beat, and update the interface accordingly.
                pulse.beat();
                updateInterface();
            }
        });

        final Button reset1 = (Button) findViewById(R.id.reset1);
        reset1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Trigger a beat, and update the interface accordingly.
                pulse.reset();
                updateInterface();
            }
        });
        final Button reset2 = (Button) findViewById(R.id.reset2);
        reset2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Trigger a beat, and update the interface accordingly.
                pulse.reset();
                updateInterface();
            }
        });



        // Set up tabs.
        host = (TabHost)findViewById(R.id.tabHost);
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

        // Update the interface to cope with screen rotations.
        updateInterface();

        // If we've only rotated, make sure we are on the right tab.
        int tab = interfaceHelper.getTab();
        if (tab > -1) {
            host.setCurrentTab(tab);
        }
        else {
            interfaceHelper.setTab(host.getCurrentTab());
        }
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

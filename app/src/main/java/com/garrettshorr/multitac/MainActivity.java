package com.garrettshorr.multitac;

import android.content.Context;
import android.content.IntentFilter;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button topLeftButton, topCenterButton, topRightButton;
    private Button centerLeftButton, centerCenterButton, centerRightButton;
    private Button bottomLeftButton, bottomCenterButton, bottomRightButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setUpOnClickButtons();

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.topLeftButton :
                Toast.makeText(MainActivity.this,"button1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.topCenterButton :
                Toast.makeText(MainActivity.this,"button2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.topRightButton :
                Toast.makeText(MainActivity.this,"button3", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void wireWidgets(){
        topLeftButton = (Button) findViewById(R.id.topLeftButton);
        topCenterButton = (Button) findViewById(R.id.topCenterButton);
        topRightButton = (Button) findViewById(R.id.topRightButton);
        centerLeftButton = (Button) findViewById(R.id.centerLeftButton);
        centerCenterButton = (Button) findViewById(R.id.centerCenterButton);
        centerRightButton = (Button) findViewById(R.id.centerRightButton);
        bottomLeftButton = (Button) findViewById(R.id.bottomLeftButton);
        bottomCenterButton = (Button) findViewById(R.id.bottomCenterButton);
        bottomRightButton = (Button) findViewById(R.id.bottomRightButton);
    }

    public void setUpOnClickButtons(){
        topLeftButton.setOnClickListener(this);
        topCenterButton.setOnClickListener(this);
        topRightButton.setOnClickListener(this);
        centerLeftButton.setOnClickListener(this);
        centerCenterButton.setOnClickListener(this);
        centerRightButton.setOnClickListener(this);
        bottomLeftButton.setOnClickListener(this);
        bottomCenterButton.setOnClickListener(this);
        bottomRightButton.setOnClickListener(this);
    }


}
package com.garrettshorr.multitac;

import android.content.Context;
import android.content.IntentFilter;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.peak.salut.Callbacks.SalutCallback;
import com.peak.salut.Callbacks.SalutDataCallback;
import com.peak.salut.Callbacks.SalutDeviceCallback;
import com.peak.salut.Salut;
import com.peak.salut.SalutDataReceiver;
import com.peak.salut.SalutDevice;
import com.peak.salut.SalutServiceData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SalutDataCallback {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    private Button topLeftButton, topCenterButton, topRightButton;
    private Button centerLeftButton, centerCenterButton, centerRightButton;
    private Button bottomLeftButton, bottomCenterButton, bottomRightButton;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SalutDataReceiver dataReceiver = new SalutDataReceiver(MainActivity.this, MainActivity.this);
        SalutServiceData serviceData = new SalutServiceData("TicTacToe", 50489, "MultiTac");

        Salut network = new Salut(dataReceiver, serviceData, new SalutCallback() {
            @Override
            public void call() {
                Log.e(TAG, "Sorry, but this device does not support WiFi Direct.");
            }
        });

        network.startNetworkService(new SalutDeviceCallback() {
            @Override
            public void call(SalutDevice device) {
                Log.d(TAG, device.readableName + " has connected!");
            }
        });

        network.discoverNetworkServices(new SalutDeviceCallback() {
            @Override
            public void call(SalutDevice device) {
                Log.d(TAG, "A device has connected with the name " + device.deviceName);
            }
        }, false);

        //OR

        network.discoverNetworkServices(new SalutCallback() {
            @Override
            public void call() {
                Log.d(TAG, "All I know is that a device has connected.");
            }
        }, true);
        /*
        network.discoverNetworkServicesWithTimeout(new SalutCallback() {
            @Override
            public void call() {
                Log.d(TAG, "Look at all these devices! " + network.foundDevices.toString());
            }
        }, new SalutCallback() {
            @Override
            public void call() {
                Log.d(TAG, "Bummer, we didn't find anyone. ");
            }
        }, 5000);

        network.registerWithHost(possibleHost, new SalutCallback() {
            @Override
            public void call() {
                Log.d(TAG, "We're now registered.");
            }
        }, new SalutCallback() {
            @Override
            public void call() {
                Log.d(TAG, "We failed to register.");
            }
        });
        */
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
    @Override
    public void onDataReceived(Object o) {

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
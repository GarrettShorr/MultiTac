package com.garrettshorr.multitac;

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

public class MainActivity extends AppCompatActivity implements SalutDataCallback, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    private Button hostButton, joinButton;
    private Salut network;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        hostButton = (Button) findViewById(R.id.button_host);
        joinButton = (Button) findViewById(R.id.button_join);


        SalutDataReceiver dataReceiver = new SalutDataReceiver(MainActivity.this, MainActivity.this);
        SalutServiceData serviceData = new SalutServiceData("TicTacToe", 50489, "MultiTac");

        network = new Salut(dataReceiver, serviceData, new SalutCallback() {
            @Override
            public void call() {
                Log.e(TAG, "Sorry, but this device does not support WiFi Direct.");
            }
        });

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
    }
    @Override
    public void onDataReceived(Object o) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_host:
                startNetworkService();
                break;
            case R.id.button_join:
                joinNetwork();
                break;
        }
    }

    private void joinNetwork() {
        network.discoverNetworkServices(new SalutDeviceCallback() {
            @Override
            public void call(SalutDevice device) {
                Log.d(TAG, "A device has connected with the name " + device.deviceName);
            }
        }, false);

        network.discoverWithTimeout(new SalutCallback() {
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
    }

    private void startNetworkService() {
        network.startNetworkService(new SalutDeviceCallback() {
            @Override
            public void call(SalutDevice device) {
                Log.d(TAG, device.readableName + " has connected!");
            }
        });
    }
}
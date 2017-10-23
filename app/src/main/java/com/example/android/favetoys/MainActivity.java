package com.example.android.favetoys;

import java.util.Set;
import java.util.UUID;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class MainActivity extends AppCompatActivity {

    private int bluetoothInput = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Device does not support Bluetooth
        if (mBluetoothAdapter == null) {

        }

        // If bluetooth is off, turn bluetooth on
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            BluetoothDevice mDevice;
            for (BluetoothDevice device : pairedDevices) {
                mDevice = device;
            }
        }

    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) { }
            mmSocket = tmp;
        }
        public void run() {
            mBluetoothAdapter.cancelDiscovery();
            try {
                mmSocket.connect();
            } catch (IOException connectException) {
                try {
                    mSocket.close();
                } catch (IOException closeException) { }
                return;
            }
        }
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }


    protected void
}


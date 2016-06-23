package com.julioleitao.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    BluetoothAdapter BA;

    public void turnBluetoothOff(View view)
    {
        BA.disable();
        if(BA.isEnabled())
        {
            Toast.makeText(getApplicationContext(),"Bluetooth could not be disabled",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Bluetooth turned off ",Toast.LENGTH_LONG).show();
        }
    }

    public void findDiscoverableDevices(View view)
    {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewPairedDevices(View view)
    {
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
        ListView pairedDeviceslistView = (ListView) findViewById(R.id.pairedDeviceslistView);

        ArrayList pairedDevicesArrayList = new ArrayList();

        for(BluetoothDevice bluetoothDevice:pairedDevices)
        {
            pairedDevicesArrayList.add(bluetoothDevice.getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairedDevicesArrayList);
        pairedDeviceslistView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        BA = BluetoothAdapter.getDefaultAdapter();

        if(BA.isEnabled())
        {
            Toast.makeText(getApplicationContext(),"Bluetooth is on",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            if(BA.isEnabled())
            {
                Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

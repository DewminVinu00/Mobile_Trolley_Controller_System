package com.example.BTCarController;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private final String DEVICE_ADDRESS = "98:DA:60:01:8A:15" ;
    private final UUID PORT_UUID = UUID.fromString ( "00001101-0000-1000-8000-00805f9b34fb" );

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    Button forward_btn, forward_left_btn, forward_right_btn, reverse_btn, revers_right_btn, revers_left_btn, bluetooth_connect_btn, up_btn, bottom_btn;

    String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        forward_btn = (Button) findViewById(R.id.forward_btn);
        forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        forward_right_btn = (Button) findViewById (R.id.forward_right_btn);
        reverse_btn = (Button) findViewById ( R.id.reverse_btn );
        bluetooth_connect_btn = (Button) findViewById ( R.id.bluetooth_connect_btn );
        up_btn = (Button) findViewById ( R.id.up_btn ) ;
        bottom_btn = (Button) findViewById ( R.id.bottom_btn );


        forward_btn.setOnTouchListener ( new View.OnTouchListener ( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction ( ) == MotionEvent.ACTION_DOWN)
                {
                    command = "1";

                    try
                    {
                        outputStream.write(command.getBytes ());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                else if(motionEvent.getAction ( )== MotionEvent.ACTION_UP)
                {
                    command = "10";
                    try
                    {
                        outputStream.write(command.getBytes ());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace ();
                    }

                }
                return false;
            }
        } );

        reverse_btn.setOnTouchListener ( new View.OnTouchListener ( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction () == MotionEvent.ACTION_DOWN)
                {
                    command = "2";
                    try
                    {
                        outputStream.write(command.getBytes ());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                else if(motionEvent.getAction () == MotionEvent.ACTION_UP)
                {
                    command = "10";

                    try
                    {
                        outputStream.write(command.getBytes ());
                    }
                    catch (IOException e)
                    {

                    }
                }
                return false;
            }
        } );

        forward_left_btn.setOnTouchListener ( new View.OnTouchListener ( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction () == MotionEvent.ACTION_DOWN)
                {
                    command = "3";

                    try
                    {
                        outputStream.write(command.getBytes ());
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                else if (motionEvent.getAction () == MotionEvent.ACTION_UP)
                {
                    command = "10";

                    try
                    {
                        outputStream.write ( command.getBytes () );
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                return false;
            }
        } );

        forward_right_btn.setOnTouchListener ( new View.OnTouchListener ( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction () == motionEvent.ACTION_DOWN)
                {
                    command="4";

                    try {
                        outputStream.write ( command.getBytes () );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                else if(motionEvent.getAction () == MotionEvent.ACTION_UP)
                {
                    command = "10";

                    try {
                        outputStream.write ( command.getBytes () );
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                return false;
            }
        } );

        up_btn.setOnTouchListener ( new View.OnTouchListener ( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction () == motionEvent.ACTION_DOWN)
                {
                    command="5";

                    try {
                        outputStream.write ( command.getBytes () );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                else if(motionEvent.getAction () == MotionEvent.ACTION_UP)
                {
                    command = "10";

                    try {
                        outputStream.write ( command.getBytes () );
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace ();
                    }
                }

                return false;
            }
        } );

        bottom_btn.setOnTouchListener ( new View.OnTouchListener ( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction () == motionEvent.ACTION_DOWN)
                {
                    command="6";

                    try {
                        outputStream.write ( command.getBytes () );
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                else if(motionEvent.getAction () == MotionEvent.ACTION_UP)
                {
                    command = "10";

                    try {
                        outputStream.write ( command.getBytes () );
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace ();
                    }
                }
                return false;
            }
        } );

        bluetooth_connect_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                if (!BTinit ( )) {
                    return;
                }
                BTconnect();
            }
        } );
    }

    public boolean BTinit()
    {
        boolean found = false;

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter ();

        if(bluetoothAdapter == null)
        {
            Toast.makeText ( getApplicationContext (), "Device doesn't support bluetooth", Toast.LENGTH_SHORT ).show ( );
            if(! bluetoothAdapter.isEnabled ())
            {
                Intent enableAdapter = new Intent ( BluetoothAdapter.ACTION_REQUEST_ENABLE );
                startActivityForResult ( enableAdapter,0 );

                try {
                    Thread.sleep (1000 );
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace ();
                }
            }
        } else {
            if(!bluetoothAdapter.isEnabled ())
            {
                Intent enableAdapter = new Intent ( BluetoothAdapter.ACTION_REQUEST_ENABLE );
                startActivityForResult ( enableAdapter,0 );

                try {
                    Thread.sleep (1000 );
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace ();
                }
            }
        }

        Set<BluetoothDevice> bondedDevices;
        bondedDevices = bluetoothAdapter.getBondedDevices ();

        if(bondedDevices.isEmpty ())
        {
            Toast.makeText ( getApplicationContext (),"please pair the device first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean BTconnect()
    {
        boolean connected = true;

        try{
            socket = device.createRfcommSocketToServiceRecord ( PORT_UUID );
            socket.connect();

            Toast.makeText ( getApplicationContext (), "Connection to bluetooth device successful", Toast.LENGTH_SHORT ).show ( );
        }
        catch(IOException e)
        {
            e.printStackTrace ();
            connected = false;
        }

        if(connected)
        {
            try{
                outputStream = socket.getOutputStream ();
            }
            catch(IOException e)
            {
                e.printStackTrace ();
            }
        }
        return connected;
    }

    @Override
    protected void onStart() {
        super.onStart ( );
    }
}
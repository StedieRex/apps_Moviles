package com.example.pruebaesp_controlluces;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_BLUETOOTH_CONNECT_PERMISSION = 3;
    private static final int REQUEST_FINE_LOCATION_PERMISSION = 2;
    private BluetoothAdapter mBtAdapter;
    private BluetoothSocket btSocket;
    private BluetoothDevice DispositivoSeleccionado;
    private ConnectedThread MyConexionBT;
    private ArrayList<String> mNameDevices = new ArrayList<>();
    private ArrayAdapter<String> deviceAdapter;

    Button IdbtnBuscar,IdBtnConectar,IdBtnLuz1on,IdBtnLuz1off,IdBtnLuz2on,IdBtnLuz2off,IdBtnDesconectar;
    Spinner IdDisEncontrados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        requestBluetoothConnectPermission();
        requestLocationPermission();

        IdbtnBuscar = findViewById(R.id.IdBtnBuscar);
        IdBtnConectar = findViewById(R.id.IdBtnConectar);
        IdBtnLuz1on = findViewById(R.id.IdBtnLuz1on);
        IdBtnLuz1off = findViewById(R.id.IdBtnLuz1off);
        IdBtnLuz2on = findViewById(R.id.IdBtnLuz2on);
        IdBtnLuz2off = findViewById(R.id.IdBtnLuz2off);
        IdBtnDesconectar = findViewById(R.id.IdBtnDesconectar);
        IdDisEncontrados = findViewById(R.id.IdDisEncontrados);

        deviceAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, mNameDevices);

        IdbtnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DispositivosVinculados();
            }
        });
        IdBtnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConectarDispBT();
            }
        });

        IdBtnDesconectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btSocket!=null){
                    try{btSocket.close();}
                    catch (IOException e){Toast.makeText(getBaseContext(),"Error",Toast.LENGTH_SHORT).show();}
                }
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode()==MainActivity.REQUEST_ENABLE_BT){
                        Log.d(TAG,"ACTIVIDAD REGISTRADA");
                    }

                }
            });

    public void DispositivosVinculados(){
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter==null){
            showToast("Bluetooth no disponible en este dispositivo");
            finish();
            return;
        }
        if(!mBtAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_CONNECT)!=PackageManager.PERMISSION_GRANTED)
                return;
            someActivityResultLauncher.launch(enableBtIntent);
        }

        IdDisEncontrados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                DispositivoSeleccionado = getBluetoothDeviceByName(mNameDevices.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                DispositivoSeleccionado = null;
            }
        });

        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if(pairedDevices.size()>0){
            for(BluetoothDevice device : pairedDevices){
                mNameDevices.add(device.getName());
            }
            deviceAdapter.notifyDataSetChanged();
        }else {
            showToast("No hay dispositivos Bluetooth emparejados");
        }


    }

    private void requestLocationPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_FINE_LOCATION_PERMISSION);
    }

    private void requestBluetoothConnectPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.BLUETOOTH_CONNECT},REQUEST_BLUETOOTH_CONNECT_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if(requestCode==REQUEST_BLUETOOTH_CONNECT_PERMISSION){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.d(TAG,"Permiso concedido, ahora puedes utilizar funciones de Bluetooth que requieren BLUETOOTH_");
            }else{
                Log.d(TAG,"permisos denegado, debes manejar este caso segun tus necesidades");
            }
        }
    }

    private BluetoothDevice getBluetoothDeviceByName(String name){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_CONNECT)!=PackageManager.PERMISSION_GRANTED)
            Log.d(TAG,"------->>>>>> ActivityCompat.checkSelfPermission");
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        for(BluetoothDevice device : pairedDevices){
            if(device.getName().equals(name)){
                return device;
            }
        }
        return null;
    }

    private void ConectarDispBT(){
        if(DispositivoSeleccionado == null){
            showToast("Selecciona un dispositivo Bluetooth.");
            return;
        }
        try{
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) !=PackageManager.PERMISSION_GRANTED)
                return;
            btSocket = DispositivoSeleccionado.createInsecureRfcommSocketToServiceRecord(BT_MODULE_UUID);
            btSocket.connect();
            MyConexionBT = new ConnectedThread(btSocket);
            MyConexionBT.start();
            showToast("Conexcion exitosa");
        }catch (IOException e){
            showToast("Error al conectar con el dispositivo.");
        }
    }

    private class ConnectedThread extends Thread{
        private final OutputStream mmOutStream;
        ConnectedThread(BluetoothSocket socket){
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            }catch (IOException e){

                showToast("error al crear el flujo de daots.");
            }
            mmOutStream=tmpOut;
        }
        public void write(char input){
            try{
                mmOutStream.write((byte)input);

            }catch (IOException e){
                Toast.makeText(getBaseContext(), "La conexion fallo",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void showToast(final String message){
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
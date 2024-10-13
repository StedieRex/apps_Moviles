package com.example.a1_primeraapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn1;

    EditText valor1;
    EditText valor2;

    TextView txt;

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

        //aqui empezamos a escribir codigo, el codigo anterior es la ejecucion de la aplicacion


        valor1=(EditText) findViewById(R.id.editTextNumberDecimal);
        valor2=(EditText) findViewById(R.id.editTextNumberDecimal2);

        btn1 = (Button) findViewById(R.id.button);

        txt = (TextView) findViewById(R.id.textView3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double v1=Double.parseDouble(valor1.getText().toString());
                Double v2=Double.parseDouble(valor2.getText().toString());

                Double total = v1 + v2;

                txt.setText(""+total);

                //metodo que lanza un mensaje de prueba
                //Toast.makeText(MainActivity.this, "Hola mundo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
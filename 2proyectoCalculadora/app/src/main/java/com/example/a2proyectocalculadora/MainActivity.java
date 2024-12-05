package com.example.a2proyectocalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView num1;
    TextView num2;
    TextView simbolo_operacion;
    TextView mostrar_resultado;

    Button btnsuma;
    Button btnresta;
    Button btnmultiplicacion;
    Button btndivision;

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

        btnsuma = (Button) findViewById(R.id.button2);
        btnresta = (Button) findViewById(R.id.button4);
        btnmultiplicacion = (Button) findViewById(R.id.button);
        btndivision = (Button) findViewById(R.id.button3);

        num1 = (TextView) findViewById(R.id.editTextNumberDecimal);
        num2 = (TextView) findViewById(R.id.editTextNumberDecimal2);
        simbolo_operacion = (TextView) findViewById(R.id.textView2);
        mostrar_resultado = (TextView) findViewById(R.id.textView3);

        btndivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float n1 = Float.parseFloat(num1.getText().toString());
                float n2 = Float.parseFloat(num2.getText().toString());

                float resultado = n1/n2;

                mostrar_resultado.setText("Resultado = "+resultado);
            }
        });

        btnresta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float n1 = Float.parseFloat(num1.getText().toString());
                float n2 = Float.parseFloat(num2.getText().toString());

                float resultado = n1-n2;

                mostrar_resultado.setText("Resultado = "+resultado);
            }
        });

        btnsuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float n1 = Float.parseFloat(num1.getText().toString());
                float n2 = Float.parseFloat(num2.getText().toString());

                float resultado = n1+n2;

                mostrar_resultado.setText("Resultado = "+resultado);
            }
        });

        btnmultiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float n1 = Float.parseFloat(num1.getText().toString());
                float n2 = Float.parseFloat(num2.getText().toString());

                float resultado = n1*n2;

                mostrar_resultado.setText("Resultado = "+resultado);
            }
        });
    }
}
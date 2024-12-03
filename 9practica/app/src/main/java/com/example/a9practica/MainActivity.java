package com.example.a9practica;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView text;
    CountDownTimer timer;
    int i;

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

        text = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime();
            }
        });
        i=0;
    }

    public void startTime(){
        timer =new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //text.setText(""+millisUntilFinished);
                i++;
                text.setText(""+i);
            }

            @Override
            public void onFinish() {
                text.setText("");
                i=0;us
            }
        }.start();
    }
}
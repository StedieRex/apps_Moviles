package com.example.practica6;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView text;
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

        text= (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        String[] item = new String[10];

        item[0] = "juan";
        item[1] = "alma";
        item[2] = "maria";
        item[3] = "marisol";
        item[4] = "julio";
        item[5] = "elena";
        item[6] = "alejandra";
        item[7] = "mariano";
        item[8] = "monse";
        item[9] = "muriel";

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        text.setAdapter(arrayAdapter);


    }
}
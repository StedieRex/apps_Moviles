package com.example.myapplication;

import static androidx.core.content.res.TypedArrayUtils.getText;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtUser;
    EditText txtPass;

    Button btn;
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

        txtUser = (EditText) findViewById(R.id.editText);
        txtPass = (EditText) findViewById(R.id.editTextTextPassword);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v,EditText) {
                btn_onclick(v);

            }
        });

    }

    public void btn_onclick(View v){
        String user = txtUser.getText().toString();
        String pass = txtUser.getText().toString();

        if(user.equals("root") && pass.equals("123")){

        }else{
            Toast.makeText(this,"Error de usuario",Toast.LENGTH_SHORT).show();
            txtPass.setText("");
            txtUser.setText("");
        }
    }
}


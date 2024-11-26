package com.example.a14proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText txtUser;
    EditText txtPass;

    ConexionSQLiteHelper conn;
    SQLiteDatabase db;
    @SuppressLint("MissingInflatedId")
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

        txtUser = (EditText) findViewById(R.id.editTextText);
        txtPass = (EditText) findViewById(R.id.editTextTextPassword);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnclick(v);
            }
        });
    }

    public void btnOnclick(View v){
        String user = txtUser.getText().toString();
        String pass = txtPass.getText().toString();
        if(valida( user, pass).equals("ok")){
            Intent i = new Intent(this, Activity_menu.class);
            startActivity(i);
        }else{
            txtUser.setText("");
            txtPass.setText("");
        }
    }

    public String valida(String user, String pass){
        try{
            conn = new ConexionSQLiteHelper(this,"articulos",null,3);
            db = conn.getReadableDatabase();
            String [] params = {user};
            String sql = "select id, nom, usuario, pass from usuario where usuario = ?";

            System.out.println(sql);

            Cursor cursor = db.rawQuery(sql,params);

            int id;
            String nom= "";
            String usuario = "";
            String pass2 = "";
            while(cursor.moveToNext()){
                id = cursor.getInt(0);
                nom = cursor.getString(1);
                usuario = cursor.getString(2);
                pass2 = cursor.getString(3);

                System.out.println("--------------------");
                System.out.println("id: " +id+" nom: "+nom+" usuario: "+usuario+" pass: "+pass2);
            }
            cursor.close();
            db.close();

            if(pass.equals(pass2) && !pass.equals("") && !user.equals("")){
                return "ok";
            }else{
                return "error";
            }
        }catch (Exception ex){
            System.out.println("----------------------------");
            System.out.println(ex.getMessage().toString());
            System.out.println("----------------------------");
        }
        return "";
    }
}
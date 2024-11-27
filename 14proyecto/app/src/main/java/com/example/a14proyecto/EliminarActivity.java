package com.example.a14proyecto;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EliminarActivity extends AppCompatActivity {
    Button btnCancelar;
    Button btnAceptar;
    Button btnSearch;

    EditText txtNom;
    EditText txtCosto;
    EditText txtFecha;
    EditText txtID;

    ConexionSQLiteHelper conn;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCancelar = (Button) findViewById(R.id.button11);
        btnAceptar = (Button) findViewById(R.id.button12);
        btnSearch = (Button) findViewById(R.id.button13);

        txtNom = (EditText) findViewById(R.id.editTextText2);
        txtCosto = (EditText) findViewById(R.id.editTextNumberDecimal3);
        txtFecha = (EditText) findViewById(R.id.editTextDate3);

        txtID = (EditText) findViewById(R.id.editTextNumber3);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearchOnclick(txtID.getText().toString());
            }
        });
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptarOnclick(v);
            }
        });
    }

    public void btnAceptarOnclick(View v){
        deleteDB(txtID.getText().toString());
    }

    public void deleteDB(String id) {
        try {
            conn = new ConexionSQLiteHelper(this,  "articulos", null, 3);
            db = conn.getReadableDatabase();

            String sql = "delete from articulo where id ='" + id + "'";
            System.out.println(sql);

            db.execSQL(sql);
            System.out.println("ok");
            alerta("se elimino el Articulo", "Informacion");
            txtNom.setText("");
            txtCosto.setText("");
            txtFecha.setText("");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void alerta(String line, String titulo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(line)
                .setCancelable(true)
                .setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        }).show();
    }


    public void btnSearchOnclick(String clave){
        try{
            conn = new ConexionSQLiteHelper(this, "articulos", null, 3);
            db = conn.getReadableDatabase();

            String sql = "select id, nom, costo, fecha from articulo where id = '"+clave+"'";

            System.out.println(sql);

            Cursor cursor = db.rawQuery(sql,null);

            String id = "";
            String nom = "";
            String costo = "";
            String fecha = "";
            txtNom.setText("");
            txtCosto.setText("");
            txtFecha.setText("");

            while(cursor.moveToNext()){
                id = ""+cursor.getInt(0);
                nom = cursor.getString(1);
                costo = "" + cursor.getDouble(2);
                fecha = cursor.getString(3);
                System.out.println("------------------------");
                System.out.println("------------------------");
                System.out.println("------------------------");

                System.out.println("id: "+id+ " nom: "+nom+" costo: "+costo);

            }
            txtNom.setText(nom);
            txtCosto.setText(costo);
            txtFecha.setText(fecha);
            cursor.close();
            db.close();
        }catch (Exception ex){
            System.out.println("------------------------");
            System.out.println(ex.getMessage().toString());
            System.out.println("------------------------");
        }
    }
}
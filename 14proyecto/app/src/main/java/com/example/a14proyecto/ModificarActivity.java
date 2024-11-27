package com.example.a14proyecto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class ModificarActivity extends AppCompatActivity {
    Button btnCancelar;
    Button btnAceptar;
    Button btnSearch;

    EditText txtNom;
    EditText txtCosto;
    EditText txtFecha;
    EditText txtID;

    ConexionSQLiteHelper conn;

    SQLiteDatabase db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCancelar = (Button) findViewById(R.id.button9);
        btnAceptar = (Button) findViewById(R.id.button10);
        btnSearch = (Button) findViewById(R.id.button8);

        txtNom = (EditText) findViewById(R.id.editTextText7);
        txtCosto = (EditText) findViewById(R.id.editTextNumberDecimal2);
        txtFecha = (EditText) findViewById(R.id.editTextDate2);

        txtID = (EditText) findViewById(R.id.editTextNumber2);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearchOnclick(v);
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
        String nom = txtNom.getText().toString();
        String costo = txtCosto.getText().toString();
        String fecha = txtFecha.getText().toString();
        String id = txtID.getText().toString();

        String error = "";

        if(nom.equals("")){
            error+= "Falta el nombre\n";
        }
        if(costo.equals("")){
            error += "Falta el costo\n";
        }
        if(fecha.equals("")){
            error += "Falta la fecha\n";
        }
        if(error.equals("")){
            updateDB(id,nom,costo,fecha);
        }else{
            alerta(error,"Error al actualizar la base de datos");
        }
    }

    public void updateDB(String id, String nom, String costo, String fecha)
    {
        try
        {
            conn = new ConexionSQLiteHelper( this,  "articulos",  null,  3);
            db = conn.getReadableDatabase();

            String sql = "update articulo set nom='" + nom + "', costo='" + costo + "', fecha='" + fecha + "' where id='" + id + "'";
            System.out.println(sql);

            db.execSQL(sql);
            System.out.println("ok");
            alerta( "Se modifico el Articulo.", "Informacion");
            txtNom.setText("");
            txtCosto.setText("");
            txtFecha.setText("");
        }
        catch (Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
    }


    public void btnSearchOnclick(View v){
        mostrar(txtID.getText().toString());
    }

    public void alerta(String line, String titulo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(line)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    public void mostrar(String clave){
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
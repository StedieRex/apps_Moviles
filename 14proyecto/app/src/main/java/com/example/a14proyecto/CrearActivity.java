package com.example.a14proyecto;

import android.content.DialogInterface;
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

public class CrearActivity extends AppCompatActivity {
    Button btnCancelar;
    Button btnAceptar;

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
        setContentView(R.layout.activity_crear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCancelar = (Button) findViewById(R.id.button6);
        btnAceptar = (Button) findViewById(R.id.button7);

        txtNom = (EditText) findViewById(R.id.editTextText3);
        txtCosto = (EditText) findViewById(R.id.editTextNumberDecimal);
        txtFecha = (EditText) findViewById(R.id.editTextDate);

        txtID = (EditText) findViewById(R.id.editTextNumber);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptarOnclick(v);
            }
        });
    }

    public void alerta(String line, String titulo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(line)
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }
    public void btnAceptarOnclick(View v){
        String nom = txtNom.getText().toString();
        String costo = txtCosto.getText().toString();
        String fecha = txtFecha.getText().toString();
        String id = txtID.getText().toString();

        String error = "";

        if(nom.equals("")){
            error += "Falta el nombre\n";
        }
        if(costo.equals("")){
            error +="Falta el Costo\n";
        }
        if(fecha.equals("")){
            error +="Falta la Fecha\n";
        }

        if(error.equals("")){
            insertDB(id,nom, costo, fecha);
        }else{
            alerta(error, "Error");
        }
    }

    public void insertDB(String id, String nom, String costo, String fecha){
        try{
            conn = new ConexionSQLiteHelper(this, "articulos", null, 3);
            db = conn.getReadableDatabase();

            String sql = "insert into articulo values('" +id+ "','" + nom+"','" + costo + "', '"+fecha+"')";
            System.out.println(sql);

            db.execSQL(sql);
            System.out.println("ok");
            alerta("Se creo el Articulo", "Informacion");
            txtNom.setText("");
            txtID.setText("");
            txtCosto.setText("");
            txtFecha.setText("");
        }catch (Exception ex){
            System.out.println("Error:"+ex.getMessage());
        }
    }
}
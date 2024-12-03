package com.example.a15proyecto;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class CrearActivity extends AppCompatActivity {
    Button btnCancelar;
    Button btnAceptar;

    EditText txtNom;
    EditText txtCosto;
    EditText txtID;
    Spinner txtFoto;

    String spinner;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    @SuppressLint("MissingInflatedId")
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
        txtID = (EditText) findViewById(R.id.editTextNumber);
        txtFoto = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> objetivo = new ArrayList<String>();
        objetivo.add("img1.jpg");
        objetivo.add("img2.jpg");
        objetivo.add("img3.jpg");
        objetivo.add("img4.jpg");
        objetivo.add("img5.jpg");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,objetivo);
        txtFoto.setAdapter(adapter);

        txtFoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptarClick(v);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void btnAceptarClick(View v) {
        String id = txtID.getText().toString();
        String nom = txtNom.getText().toString();
        String foto = "";
        String costo = txtCosto.getText().toString();
        String fecha = "";

        String url = "https://serviciosdigitalesplus.com/distribuida2026/procesos.php?tipo=1&id=" + id
                + "&nom=" + nom + "&costo=" + costo + "&foto=" + spinner + "&fecha=2024/10/23&r=" + Math.random();

        requestQueue = Volley.newRequestQueue(this);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("web ok");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("web error");
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}
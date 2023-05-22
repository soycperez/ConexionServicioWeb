package com.example.conexionservicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.*;
import com.android.volley.toolbox.*;


import org.json.JSONException;
import org.json.JSONObject;

public class ModificarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Button btnBuscar;
    Button btnModificar;
    EditText txtID;
    EditText txtNom;
    EditText txtCosto;

    int estado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        request = Volley.newRequestQueue(getApplicationContext());
        btnBuscar = (Button) findViewById(R.id.button7);
        btnModificar = (Button) findViewById(R.id.button8);
        txtID = (EditText) findViewById(R.id.editTextTextPersonName2);
        txtNom = (EditText) findViewById(R.id.editTextTextPersonName5);
        txtCosto = (EditText) findViewById(R.id.editTextNumberDecimal);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSearch(view);
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclikModificar(view);
            }
        });
    }

    public void onclickSearch(View view){
        estado = 0;
        String line = "https://serviciosdigitalesplus.com/catalogo.php?tipo=1&id="+txtID.getText().toString()+"&r="+ Math.random();;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, line, null, this,this);
        request.add(jsonObjectRequest);
    }

    public void onclikModificar(View view){
        estado = 1;
        String line = "https://serviciosdigitalesplus.com/catalogo.php?tipo=5&id="+txtID.getText().toString()+"&nom="+txtNom.getText().toString()+"&costo="+txtCosto.getText().toString()+"&r="+ Math.random();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, line, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.print("------------------------------------");
        System.out.print("------------------------------------");
        System.out.print(error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        if(estado ==0){
            JSONObject obj = response.optJSONObject("dato");
            try{
                txtNom.setText(obj.optString("nom"));
                txtCosto.setText(obj.optString("costo"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
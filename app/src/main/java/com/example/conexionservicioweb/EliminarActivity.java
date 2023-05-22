package com.example.conexionservicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class EliminarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Button btnBuscar;
    Button btnEliminar;
    EditText txtID;
    EditText txtNom;
    EditText txtCosto;
    int estado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        request = Volley.newRequestQueue(getApplicationContext());

        setContentView(R.layout.activity_eliminar);
        btnBuscar = (Button) findViewById(R.id.button6);
        txtID = (EditText) findViewById(R.id.editTextTextPersonName6);
        txtNom = (EditText) findViewById(R.id.editTextTextPersonName7);
        txtCosto = (EditText) findViewById(R.id.editTextNumberDecimal2);
        btnEliminar= (Button) findViewById(R.id.button9);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSearch(view);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclikEliminar(view);
            }
        });
    }

    public void onclickSearch(View view){
        estado = 0;
        String line = "https://serviciosdigitalesplus.com/catalogo.php?tipo=1&id="+txtID.getText().toString()+"&r="+ Math.random();;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, line, null, this,this);
        request.add(jsonObjectRequest);
    }

    public void onclikEliminar(View view){
        estado = 1;
        String line = "https://serviciosdigitalesplus.com/catalogo.php?tipo=4&id="+txtID.getText().toString()+"&r="+ Math.random();
        txtID.setText("");
        txtNom.setText("");
        txtCosto.setText("");
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
package com.example.conexionservicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONObject;

public class CrearActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {


    EditText txtId;
    EditText txtNom;
    EditText txtCosto;
    Button btn;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        txtId = (EditText) findViewById(R.id.editTextTextPersonName3);
        txtNom = (EditText) findViewById(R.id.editTextTextPersonName4);
        txtCosto = (EditText) findViewById(R.id.editTextNumber);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    clickBtn1(view);
            }
        });
    }

    public void clickBtn1(View v){
        String nom = txtNom.getText().toString();
        String id = txtId.getText().toString();
        String costo = txtCosto.getText().toString();
        String error = "";
        if(nom.equals("")){
            error+= "Falta el nombre \n";
        }
        if(id.equals("")){
            error+= "Falta el ID\n";
        }
        if(costo.equals("")) {
            error+= "Falta el Costo \n";
        }
        if(error.equals("")){
            request = Volley.newRequestQueue(getApplicationContext());
            String line = "https://serviciosdigitalesplus.com/catalogo.php?tipo=3&id="+id+"&nom="+nom+"&costo="+costo+"&r="+ Math.random();
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, line, null, this, this);
            request.add(jsonObjectRequest);
        }else {
            System.out.println(error);
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.print("------------------------------------");
        System.out.print("------------------------------------");
        System.out.print(error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        //JSONObject obj = response.optJSONObject("dato");
        try{
            System.out.print("------------------------------------");
            System.out.print("------------------------------------");
            System.out.print("Ok");
            txtNom.setText("");
            txtId.setText("");
            txtCosto.setText("");
        }catch (Exception e){
            System.out.print("------------------------------------");
            System.out.print("------------------------------------");
            e.printStackTrace();
        }
    }
}
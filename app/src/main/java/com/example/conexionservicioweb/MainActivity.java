package com.example.conexionservicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    //WebView wv;

    EditText txt;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    Button btnCrear;
    Button btnModifcar;
    Button btnEliminar;
    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //wv = (WebView) findViewById(R.id.webView1);
        //wv.loadUrl("https://serviciosdigitalesplus.com/cartas/");

        txt = (EditText) findViewById(R.id.editTextTextPersonName);
        request = Volley.newRequestQueue(getApplicationContext());
        String line = "https://serviciosdigitalesplus.com/catalogo.php?tipo=1&id=1001"+"&r="+ Math.random();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, line, null, this, this);
        request.add(jsonObjectRequest);

        btnCrear = (Button) findViewById(R.id.button2);
        btnModifcar = (Button) findViewById(R.id.button3);
        btnEliminar = (Button) findViewById(R.id.button4);
        btnMostrar = (Button) findViewById(R.id.button5);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CrearActivity.class);
                startActivity(i);
            }
        });

        btnModifcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ModificarActivity.class);
                startActivity(i);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EliminarActivity.class);
                startActivity(i);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MostrarActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.print("------------------------------------");
        System.out.print("------------------------------------");
        System.out.print(error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        JSONObject obj = response.optJSONObject("dato");
        try{
            txt.setText(obj.optString("nom"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
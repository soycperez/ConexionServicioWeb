package com.example.conexionservicioweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public class MostrarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    EditText textArea;
    Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        textArea = (EditText) findViewById(R.id.editTextTextMultiLine);
        btnListar = (Button) findViewById(R.id.button10);
        request = Volley.newRequestQueue(getApplicationContext());

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListar(view);
            }
        });
    }

    public void onclickListar(View view){
        String line = "https://serviciosdigitalesplus.com/catalogo.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, line, null, this,this);
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
        JSONArray array = response.optJSONArray("datos");
        try{
            String aux = "";
            for (int i = 0; i<array.length(); i++){
                JSONObject obj = array.getJSONObject(i);
                aux += "ID: " + obj.get("id") + "\n";
                aux += "NOMBRE: " + obj.get("nom") + "\n";
                aux += "COSTO: " + obj.get("costo") + "\n";
                aux += "\n\n";
            }
            if(!aux.equals(""))
                textArea.setText(aux);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
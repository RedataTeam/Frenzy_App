package com.example.frenzy10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class new_user extends AppCompatActivity {
    EditText tnombre;
    EditText tapellidoPaterno;
    EditText tapellidoMaterno;
    EditText tfechaNacimiento;
    EditText tnumeroTel;
    EditText tcorreo;
    EditText tciudad;
    EditText testado;
    EditText tusuario;
    EditText tcontrasenia;
    Button b1;

    JSONObject usuario = new JSONObject();
    JSONObject persona = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        tnombre = findViewById(R.id.txtNombre);
        tapellidoPaterno = findViewById(R.id.txtApellidoPaterno);
        tapellidoMaterno = findViewById(R.id.txtApellidoMaterno);
        tfechaNacimiento = findViewById(R.id.txtFechaNac);
        tnumeroTel = findViewById(R.id.txtNumTel);
        tcorreo = findViewById(R.id.txtCorreo1);
        tciudad = findViewById(R.id.txtCiudad);
        testado = findViewById(R.id.txtEstado);
        tusuario = findViewById(R.id.txtUsuario);
        tcontrasenia = findViewById(R.id.txtContrasenia1);

        b1 = findViewById(R.id.btnRegistrar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    persona.put("nombre", tnombre.getText().toString());
                    persona.put("primerApellido", tapellidoPaterno.getText().toString());
                    persona.put("segundoApellido", tapellidoMaterno.getText().toString());
                    persona.put("fechaNacimiento", tfechaNacimiento.getText().toString());
                    persona.put("identificacion","");
                    persona.put("telMovil", tnumeroTel.getText().toString());
                    persona.put("correo", tcorreo.getText().toString());
                    persona.put("ciudad", tciudad.getText().toString());
                    persona.put("estado",testado.getText().toString());
                    persona.put("fotografia", "");

                    usuario.put("nombre", tusuario.getText().toString());
                    usuario.put("contrasenia",tcontrasenia.getText().toString());
                    usuario.put("persona", persona);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(usuario.toString());
                realizarRegistro(usuario.toString());
            }
        });
    }

    public void realizarRegistro(final String t1){
        String url = "http://10.0.0.13:8080/Frenzy/api/usuario/save";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(new_user.this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                irInicio();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError);
                Toast.makeText(new_user.this, "Error", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getParams(){
                HashMap<String, String> params = new HashMap<String ,String>();
                System.out.println(t1);
                params.put("datosUsuario", t1);
                return params;
            }

            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }


    public void limpiarCampos(){
        tnombre.setText("");
        tapellidoPaterno.setText("");
        tapellidoMaterno.setText("");
        tfechaNacimiento.setText("");
        tciudad.setText("");
        testado.setText("");
        tnumeroTel.setText("");
        tcorreo.setText("");
        tusuario.setText("");
        tcontrasenia.setText("");
    }

    public void irInicio() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
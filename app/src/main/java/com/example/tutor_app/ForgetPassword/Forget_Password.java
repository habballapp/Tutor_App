package com.example.tutor_app.ForgetPassword;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.R;
import com.example.tutor_app.Signin.SignIn;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Forget_Password extends AppCompatActivity {

    String Url = "http://pci.edusol.co/Login/forgot_password_api.php";
    private RelativeLayout signin, btn_signin_user;
    private EditText edt_email_pass, edt_contact;
    String email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);

        signin = findViewById(R.id.signin);
        edt_email_pass = findViewById(R.id.edt_email_pass);
        edt_contact = findViewById(R.id.edt_contact);
        btn_signin_user = findViewById(R.id.btn_signin_user);


        btn_signin_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edt_email_pass.getText().toString().trim();
                phone = edt_contact.getText().toString().trim();
                try {
                    ForgetPassword();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Forget_Password.this, SignIn.class);
                startActivity(intent);
            }
        });


    }

    private void ForgetPassword() throws JSONException {

        JSONObject map = new JSONObject();

        map.put("Email", email);
        map.put("PhoneNumber", phone);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                try {
                    Toast.makeText(Forget_Password.this,response.getString("message"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }

//                @Override
//                public String getBodyContentType() {
//                    return "json";
//                }
//
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> map = new HashMap<>();
//                    map.put("'name'", "'"+name+"'");
//                    map.put("fathername", fathername);
//                    map.put("email", email);
//                    map.put("class", classes);
//                    map.put("subjects", subjects);
//                    map.put("contactno1", contactno1);
//                    map.put("contactno2", contactno2);
//                    map.put("contactno3", contactno3);
//                    map.put("schoolcollege", schoolcollege);
//                    map.put("housenum",edt_house_number.getText().toString());
//                    map.put("buildingname",edt_bno.getText().toString());
//                    map.put("streetnum",edt_street.getText().toString());
//                    map.put("blocknum",edt_block.getText().toString());
//                    map.put("area",edt_area.getText().toString());
//                    map.put("city",edt_city.getText().toString());
//                    map.put("country",edt_country.getText().toString());
//                    map.put("gender",spinner_gender);
//                    map.put("desiredtiming",spinner_timings);
//                    map.put("userid",userid);
//
//                    Log.i("AddressClassDebug", String.valueOf(map));
//
//                    return map;
//                }
        };
        Volley.newRequestQueue(getApplication()).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        requestQueue.add(sr);


    }
}

package com.example.tutor_app.ForgetPassword;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.R;
import com.example.tutor_app.Signin.SignIn;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Confirm_Password extends AppCompatActivity {

    private RelativeLayout signin,btn_signin_user;
    private EditText newpassword,confirmpassword;
    String Url = "http://pci.edusol.co/Login/forgot_password_confirm_api.php";
    private String key;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__password);

        signin = findViewById(R.id.signin);
        newpassword = findViewById(R.id.new_password);
        confirmpassword = findViewById(R.id.confirm_password);
        btn_signin_user = findViewById(R.id.btn_signin_user);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Confirm_Password.this, SignIn.class);
                startActivity(intent);
            }
        });

        btn_signin_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newpassword.getText().toString().equals("")
                        && confirmpassword.getText().toString().equals("")
                ) {
                    Toast.makeText(Confirm_Password.this, "Please Enter All Required Fields", Toast.LENGTH_SHORT).show();
                    newpassword.setError("Please Enter Required Fields");
                    confirmpassword.setError("Please Enter Required Fields");

                } else if (confirmpassword.getText().toString().equals(newpassword.getText().toString())) {
                    try {
                        changePassword();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(Confirm_Password.this, "Password does not match", Toast.LENGTH_SHORT).show();

            }
        });
        Uri uri = getIntent().getData();
        if (uri != null) {
            key = String.valueOf(uri).split("key=")[1].split("&")[0];
            email = String.valueOf(uri).split("email=")[1].split("&")[0];
        }

    }

    private void changePassword() throws Exception {

        JSONObject map = new JSONObject();
        map.put("email", email);
        map.put("key", key);
        map.put("new_password", newpassword.getText());
        map.put("confirm_password", confirmpassword.getText());
        Log.i("confirm_password", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Response", String.valueOf(response));
                try {
                    Toast.makeText(Confirm_Password.this, response.getString("message"), Toast.LENGTH_LONG).show();
                    Intent changePassword = new Intent(Confirm_Password.this, SignIn.class);
                    startActivity(changePassword);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Confirm_Password.this, "Check connection", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "application/json");
                return map;
            }
        };
        Volley.newRequestQueue(getApplication()).add(sr);
        // RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        // requestQueue.add(sr);


    }

    @Override
    public void onBackPressed() {
        Intent changePassword = new Intent(Confirm_Password.this, SignIn.class);
        startActivity(changePassword);
    }

}

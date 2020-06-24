package com.example.tutor_app.Signin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.Dashboard_Drawer;
import com.example.tutor_app.Dashboard.ui.Dashboard_Drawer_Student;
import com.example.tutor_app.ForgetPassword.Forget_Password;
import com.example.tutor_app.R;
import com.example.tutor_app.UserType.User_Type;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    private RelativeLayout btn_signin;
    private TextView txt_password, txt_create;
    private EditText edt_email, edt_password;
    private String URL_LOGIN = "https://pci.matz.group/login_data.php ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_signin = findViewById(R.id.btn_signin_user);
        txt_password = findViewById(R.id.txt_password);
        txt_create = findViewById(R.id.txt_create);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mEmail = edt_email.getText().toString().trim();
                String mPassword = edt_password.getText().toString().trim();
                if (!mEmail.isEmpty() || !mPassword.isEmpty()) {
                    try {
                        Login();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    edt_email.setError("Please insert email");
                    edt_password.setError("Please insert password");
                }


            }

        });

        txt_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignIn.this, Forget_Password.class);
                startActivity(intent);
            }
        });


        txt_create.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){

        Intent intent = new Intent(SignIn.this, User_Type.class);
        startActivity(intent);
    }
    });

    }






    private void Login() throws JSONException {

        StringRequest sr = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String result) {
                Log.i("loginData", String.valueOf(result));
                try {
                    JSONObject obj = new JSONObject(result);
                    if (!obj.getString("userid").equals("null")) {

                        if (obj.getString("userrole").equals("Student")){

                            Toast.makeText(SignIn.this, "Student", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignIn.this, Dashboard_Drawer_Student.class);
                            startActivity(intent);
                        }
                        else if(obj.getString("userrole").equals("Teacher")){

                            Toast.makeText(SignIn.this, "Teacher", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignIn.this, Dashboard_Drawer.class);
                            startActivity(intent);

                        }

                    } else {
                        Toast.makeText(SignIn.this, "Username or password is incorrect.", Toast.LENGTH_LONG).show();
                    }
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
                map.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                return map;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", edt_email.getText().toString());
                map.put("password", edt_password.getText().toString());
                return map;
            }
        };
        Volley.newRequestQueue(this).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sr);
    }
}


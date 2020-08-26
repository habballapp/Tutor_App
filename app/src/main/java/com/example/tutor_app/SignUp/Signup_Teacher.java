package com.example.tutor_app.SignUp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.R;
import com.example.tutor_app.Signin.SignIn;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signup_Teacher extends AppCompatActivity {

    private EditText edt_email,edt_password,edt_fullname,edt_contact;
    private String Url_Teacher = "http://pci.edusol.co/Login/signup_teacher_data.php";
    private RelativeLayout btn_signup_user;
    private TextView back_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        btn_signup_user = findViewById(R.id.btn_signup_user);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_contact = findViewById(R.id.edt_contact);
        back_signIn = findViewById(R.id.back_signIn);
        back_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Signup_Teacher.this , SignIn.class);
                startActivity(back);
            }
        });
        final List<EditText> allFields =new ArrayList<EditText>();

        allFields.add(edt_email);
        allFields.add(edt_password);
        allFields.add(edt_fullname);
        allFields.add(edt_contact);

        btn_signup_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<EditText> ErrorFields =new ArrayList<EditText>();//empty Edit text arraylist
                for(int j = 0; j < allFields.size(); j++) {
                    if (TextUtils.isEmpty(allFields.get(j).getText())) {
                        // EditText was empty
                        //   Fields.add(allFields.get(j).getText().toString());
                        ErrorFields.add(allFields.get(j));//add empty Edittext only in this ArayList
                        for (int i = 0; i < ErrorFields.size(); i++) {
                            //Fields.add(ErrorFields.get(i).getText().toString());
                            EditText currentField = ErrorFields.get(i);
                            currentField.setError("this field required");
                            ErrorFields.set(i, currentField);
                            currentField.requestFocus();
                        }

                    }
                }
                Log.i("Size", String.valueOf(ErrorFields.size()));

                if (ErrorFields.isEmpty()) {
                    SignupUser();
                }
                else
                {
                    edt_email.setError("Please insert email");
                    edt_password.setError("Please insert password");
                    edt_fullname.setError("Please insert email");
                    edt_contact.setError("Please insert password");
                }
            }
        });



    }

    private void SignupUser() {

        StringRequest sr = new StringRequest(Request.Method.POST, Url_Teacher, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String result) {
                Log.i("SignupData", String.valueOf(result));
                try {
                    JSONObject obj = new JSONObject(result);
                    if (!obj.getString("teacherid").equals("null")) {

                        Toast.makeText(Signup_Teacher.this, "User Account Created.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Signup_Teacher.this, SignIn.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(Signup_Teacher.this, "Username or password is incorrect.", Toast.LENGTH_LONG).show();
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
                map.put("phonenumber", edt_contact.getText().toString());
                map.put("fullname", edt_fullname.getText().toString());
                return map;
            }
        };
        Volley.newRequestQueue(this).add(sr);
        // RequestQueue requestQueue = Volley.newRequestQueue(this);
        // requestQueue.add(sr);
    }


    }


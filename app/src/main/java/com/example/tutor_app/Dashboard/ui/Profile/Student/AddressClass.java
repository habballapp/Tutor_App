package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.MyAdapter;
import com.example.tutor_app.Dashboard.ui.Dashboard_Drawer_Student;
import com.example.tutor_app.R;
import com.example.tutor_app.SignUp.Signup_Student;
import com.example.tutor_app.Signin.SignIn;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressClass extends Fragment {


    private Spinner spinner1, spinner2,spinner3;
    private RelativeLayout btn_profile_next;
    private List<String> gender, timings,area;
    private EditText edt_house_number, edt_bno, edt_street, edt_block, edt_area, edt_city, edt_country;
    private String Filter_selected = "";
    String Url_Sprofile = "https://pci.edusol.co/StudentPortal/studenttutorformsubmit.php";
    String spinner_gender,spinner_area, spinner_timings, name, fathername, email, contactno1, contactno2, contactno3, classes, subjects, schoolcollege, spinnerTimings;
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_address_class, container, false);

        spinner1 = (Spinner) root.findViewById(R.id.spinner_gender);
        spinner2 = (Spinner) root.findViewById(R.id.spinner_timings);
        spinner3 = (Spinner) root.findViewById(R.id.spinner_area);
        edt_house_number = root.findViewById(R.id.edt_house_number);
        edt_bno = root.findViewById(R.id.edt_bno);
        edt_street = root.findViewById(R.id.edt_street);
        edt_block = root.findViewById(R.id.edt_block);
 //       edt_area = root.findViewById(R.id.edt_area);
        edt_city = root.findViewById(R.id.edt_city);
        edt_country = root.findViewById(R.id.edt_country);
        btn_profile_next = root.findViewById(R.id.btn_profile_next);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);

        name = sharedPreferences.getString("name", "");
        fathername = sharedPreferences.getString("fathername", "");
        email = sharedPreferences.getString("email", "");
        contactno1 = sharedPreferences.getString("contactno1", "");
        contactno2 = sharedPreferences.getString("contactno2", "");
        contactno3 = sharedPreferences.getString("contactno3", "");
        classes = sharedPreferences.getString("class", "");
        subjects = sharedPreferences.getString("subjects", "");
        schoolcollege = sharedPreferences.getString("schoolcollege", "");
        spinner_timings = sharedPreferences.getString("desiredtiming", "");


        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);

        userid = sharedPreferences1.getString("userid", "");


        gender = new ArrayList<>();
        gender.add("Select Preffered Gender");
        gender.add("Male");
        gender.add("Female");
        gender.add("Any");

        timings = new ArrayList<>();
        timings.add("Select Preffered Timings");

        for (int i = 8; i <=11 ; i++) {
            timings.add(i+"am");

        }
        timings.add( "12pm");
        for (int i = 1; i <=11 ; i++) {
            timings.add(i +"pm");

        }
        timings.add( "12am");


        final ArrayAdapter<String> spinner1_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gender) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);

                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);
                return view;
            }
        };
        spinner1.setAdapter(spinner1_adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinner_gender = gender.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < timings.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(timings.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }


        MyAdapter myAdapter = new MyAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, listVOs);
        spinner2.setAdapter(myAdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        area = new ArrayList<>();
        area = new ArrayList<>();
        area.add("Select Area");
        area.add("Baldia Town");
        area.add(" Bin Qasim Town");
        area.add("Gadap Town");
        area.add("Gulberg Town");
        area.add("Gulshan Town");
        area.add("Baldia Town");
        area.add("Kiamari Town");
        area.add("Korangi Town");
        area.add("Landhi Town");
        area.add("Liaquatabad Town");
        area.add("New Karachi Town");
        area.add("North Nazimabad Town");
        area.add("Orangi Town");
        area.add(" Shah Faisal Town");
        area.add(" SITE Town");
        area.add("Lyari Town");
        area.add(" Malir Town");

        final ArrayAdapter<String> spinner_area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, area) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);

                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);
                return view;
            }
        };
        spinner3.setAdapter(spinner_area_adapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinner_area = area.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_profile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String houseno = edt_house_number.getText().toString().trim();
                String buildingno = edt_bno.getText().toString().trim();
                String streetno = edt_street.getText().toString().trim();
                String blockno = edt_block.getText().toString().trim();
                String city = edt_city.getText().toString().trim();
             //   String area = edt_area.getText().toString().trim();
                String country = edt_country.getText().toString().trim();
//              String spinner_timings = spinner2.getText().toString().trim();

                try {
                    Address();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return root;
    }


    private void Address() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("name", name);
        map.put("fathername", fathername);
        map.put("email", email);
        map.put("class", classes);

        /* ** Convert the string to json from adapter While putting in shared preference as well ** */
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();

        List<String> selectedSubjects = gson.fromJson(subjects, type);

        JSONArray jsonArray = new JSONArray(selectedSubjects);

        map.put("subjects", jsonArray);

        map.put("contactno1", contactno1);
        map.put("contactno2", contactno2);
        map.put("contactno3", contactno3);
        map.put("schoolcollege", schoolcollege);
        map.put("housenum", edt_house_number.getText().toString());
        map.put("buildingname", edt_bno.getText().toString());
        map.put("streetnum", edt_street.getText().toString());
        map.put("blocknum", edt_block.getText().toString());
        map.put("area", spinner_area);
        map.put("city", edt_city.getText().toString());
        map.put("country", edt_country.getText().toString());
        map.put("gender", spinner_gender);

        /* ** Convert the string to json from adapter While putting in shared preference as well ** */
        List<String> selectedtimings = gson.fromJson(spinner_timings, type);

        JSONArray jsonArray_timings = new JSONArray(selectedtimings);

        map.put("desiredtiming", jsonArray_timings);

        map.put("userid", userid);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url_Sprofile, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (!response.getString("studenttutorformId").equals("null"))
                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                Log.i("AddProfile", String.valueOf(response));
//                    if(response != null && !response.isEmpty()){

//                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

//                    }
//                    else
//                    Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();

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
        Volley.newRequestQueue(getContext()).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(sr);
    }


}


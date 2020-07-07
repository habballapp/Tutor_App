package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.MyAdapter;
import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.R;
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
public class InstituteAddressFragment extends Fragment {

    private Spinner spinner1,spinner2,spinner_edt_area;
    private  ArrayAdapter<String> spinner_area_adapter;
    private RelativeLayout btn_profile_next;
    private List<String> gender,timings,area;
    private EditText edt_address, edt_street, edt_block, edt_city, edt_country, et_amount1, et_amount2;
    String URL_INSTITUTE = "http://pci.edusol.co/InstitutePortal/instituteregistrationsubmit.php";
    String institutename, phone1, phone2, phone3, email, cperson, typeofInstitute, ctype, stype, classes, subjects,
            spinner_gender, spinner_timings,otherinstitute,spinner_area;
    String userid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_institute_address, container, false);


        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);

        userid = sharedPreferences1.getString("userid", "");

        Log.i("userid", userid);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);


        spinner1 = (Spinner) root.findViewById(R.id.spinner_gender);
        spinner2 = (Spinner) root.findViewById(R.id.spinner_timings);
        edt_address = root.findViewById(R.id.edt_address);
        edt_street = root.findViewById(R.id.edt_street);
        edt_block = root.findViewById(R.id.edt_block);
        spinner_edt_area = root.findViewById(R.id.spinner_edt_area);
        edt_city = root.findViewById(R.id.edt_city);
        edt_country = root.findViewById(R.id. edt_country);
        et_amount1 = root.findViewById(R.id.et_amount1);
        et_amount2 = root.findViewById(R.id.et_amount2);
        btn_profile_next = root.findViewById(R.id.btn_profile_next);

        institutename = sharedPreferences.getString("nameofInstitute", "");
        phone1 = sharedPreferences.getString("contactno1", "");
        phone2 = sharedPreferences.getString("contactno2", "");
        phone3 = sharedPreferences.getString("contactno3", "");
        email = sharedPreferences.getString("email", "");
        cperson = sharedPreferences.getString("contactperson", "");
//        ctype = sharedPreferences.getString("otherclass", "");
//        stype = sharedPreferences.getString("othersubjects", "");
        classes = sharedPreferences.getString("class", "");
        subjects = sharedPreferences.getString("subjects", "");
        typeofInstitute = sharedPreferences.getString("typeofInstitute", "");
        otherinstitute = sharedPreferences.getString("IfInstituteOther","");





        gender = new ArrayList<>();
        gender.add("Select Preffered Gender");
        gender.add("Male");
        gender.add("Female");
        gender.add("Any");


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

        timings = new ArrayList<>();
        timings.add("Select Preffered Timings");

        for (int i = 8; i <=11 ; i++) {
            timings.add(i + ":00 am");
            timings.add(i + ":30 am");
        }
        timings.add( "12:00 pm");
        timings.add( "12:30 pm");
        for (int i = 1; i <=11 ; i++) {

            timings.add(i + ":00 pm");
            timings.add(i + ":30 pm");
        }
        timings.add( "12:00 am");


        final ArrayAdapter<String> spinner2_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, timings) {
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
        spinner2.setAdapter(spinner2_adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinner_timings = timings.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        area = new ArrayList<>();
        area.add("Select Area");
        area.add("Baldia Town");
        area.add("Bin Qasim Town");
        area.add("Gadap Town");
        area.add("Gulberg Town");
        area.add("Gulshan Town");
        area.add("Jamshed Town");
        area.add("Kiamari Town");
        area.add("Korangi Town");
        area.add("Landhi Town");
        area.add("Liaquatabad Town");
        area.add("New Karachi Town");
        area.add("North Nazimabad Town");
        area.add("Nazimabad Town");
        area.add("Orangi Town");
        area.add("Shah Faisal Town");
        area.add("SITE Town");
        area.add("Saddar Town");
        area.add("Lyari Town");
        area.add("Malir Town");

        spinner_area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, area) {
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
        spinner_edt_area.setAdapter(spinner_area_adapter);
        spinner_edt_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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




                String streetno = edt_street.getText().toString().trim();
                String blockno = edt_block.getText().toString().trim();
                String city = edt_city.getText().toString().trim();
             //   String area = edt_area.getText().toString().trim();
                String country = edt_country.getText().toString().trim();
                String amount1 = et_amount1.getText().toString().trim();
                String amount2 = et_amount2.getText().toString().trim();

                try {
                    InstituteAddress();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        if (!userid.equals("")) {
            try {
                getProfileData();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return root;
    }

    private void getProfileData() throws JSONException {

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("AddProfilePreviousData",
                Context.MODE_PRIVATE);

        int selectionPosition= spinner_area_adapter.getPosition(sharedPreferences2.getString("Area",""));
        spinner_edt_area.setSelection(selectionPosition);

        edt_address.setText(sharedPreferences2.getString("Address",""));
        edt_street.setText(sharedPreferences2.getString("StreetNum",""));
        edt_block.setText(sharedPreferences2.getString("BlockNum",""));
        edt_city.setText(sharedPreferences2.getString("City",""));
        edt_country.setText(sharedPreferences2.getString("Country",""));
        et_amount1.setText(sharedPreferences2.getString("SalaryFrom",""));
        et_amount2.setText(sharedPreferences2.getString("SalaryTo",""));






    }

    private void InstituteAddress() throws JSONException {

        JSONObject map = new JSONObject();

        /* ** Convert the string to json from adapter While putting in shared preference as well ** */
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();

        List<String> selectedSubjects = gson.fromJson(subjects, type);

        JSONArray jsonArray = new JSONArray(selectedSubjects);

        map.put("nameofInstitute",institutename);
        map.put("typeofInstitute",typeofInstitute);
        map.put("contactperson", cperson);
        map.put("email", email);


//        List<String> selectedClasses = gson.fromJson(classes, type);
//        JSONArray jsonArray1 = new JSONArray(selectedClasses);
        map.put("class", classes);
        map.put("subjects", jsonArray);
        map.put("contactno1", phone1);
        map.put("contactno2", phone2);
        map.put("contactno3", phone3);
        map.put("salaryfrom", et_amount1.getText().toString());
        map.put("salaryto", et_amount2.getText().toString());
//        map.put("otherclass",ctype );
//        map.put("othersubjects",ctype );
        map.put("streetnum", edt_street.getText().toString());
        map.put("blocknum", edt_block.getText().toString());
        map.put("area", spinner_area);
        map.put("city", edt_city.getText().toString());
        map.put("country", edt_country.getText().toString());
        map.put("gender", spinner_gender);
        map.put("timing", spinner_timings);
        map.put("address", String.valueOf(edt_address.getText()));
        map.put("IfInstituteOther",otherinstitute);

        /* ** Convert the string to json from adapter While putting in shared preference as well ** */
//        List<String> selectedtimings = gson.fromJson(spinner_timings, type);
//
//        JSONArray jsonArray_timings = new JSONArray(selectedtimings);
//
//        map.put("timing", jsonArray_timings);

        map.put("userid", userid);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, URL_INSTITUTE, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("AddProfile", String.valueOf(response));
                try {
                    if (!response.getString("instituteformId").equals("null"))
                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


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


package com.example.tutor_app.Dashboard.ui.Student.ProfileEdit;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
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
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.MultiSelectApdater_timing;
import com.example.tutor_app.Dashboard.ui.Student.Profile.StateVO;
import com.example.tutor_app.Loader.Loader;
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

public class EditAddress extends Fragment {


    private Spinner spinner1, spinner2,spinner3;
    private RelativeLayout btn_profile_next;
    private ArrayAdapter<String> spinner1_adapter,spinner_area_adapter;
    private List<String> gender, timings,area;
    private EditText edt_house_num, edt_bno, edt_street, edt_block, edt_area, edt_city, edt_country;
    private String Filter_selected = "";
    String Url_Sprofile = "http://pci.edusol.co/StudentPortal/EditProfilesubmit.php";
    String spinner_gender,spinner_area, spinner_timings, name, fathername, email, contactno1, contactno2, contactno3, classes, subjects, schoolcollege, spinnerTimings;
    String userid, Id;
    TextView spinner_area_textview,spinner_timings_textview,spinner_gender_textview;
    JSONObject response = new JSONObject();
    private Loader loader;
    String timingSelected ="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_address_class, container, false);

        spinner1 = (Spinner) root.findViewById(R.id.spinner_gender);
        spinner2 = (Spinner) root.findViewById(R.id.spinner_timings);
        spinner3 = (Spinner) root.findViewById(R.id.spinner_area);
        edt_house_num = root.findViewById(R.id.edt_house_num);
        edt_bno = root.findViewById(R.id.edt_bno);
        edt_street = root.findViewById(R.id.edt_street);
        edt_block = root.findViewById(R.id.edt_block);
        //       edt_area = root.findViewById(R.id.edt_area);
        edt_city = root.findViewById(R.id.edt_city);
        edt_country = root.findViewById(R.id.edt_country);
        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        spinner_area_textview = root.findViewById(R.id.spinner_area_textview);
        spinner_gender_textview = root.findViewById(R.id.spinner_gender_textview);
        spinner_timings_textview = root.findViewById(R.id.spinner_timings_textview);
        loader = new Loader(getContext());
        final List<EditText> allFields =new ArrayList<EditText>();



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

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        Id = sharedPreferences2.getString("UserId", "");




        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        String str_response = personal_profile1.getString("ViewProfileData", "");
        if(!str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            try {
                viewProfile();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


            gender = new ArrayList<>();
            gender.add("Select Preffered Gender");
            gender.add("Male");
            gender.add("Female");
            gender.add("Any");

            timings = new ArrayList<>();
            timings.add(" ");

            for (int i = 8; i <= 11; i++) {
                timings.add(i + "am");

            }
            timings.add("12pm");
            for (int i = 1; i <= 11; i++) {
                timings.add(i + "pm");

            }
            timings.add("12am");


            spinner1_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gender) {
                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    // TODO Auto-generated method stub
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(getResources().getColor(R.color.text_color_selection));
                    text.setTextSize((float) 13.6);
                    text.setPadding(50, 0, 50, 0);

                    return view;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // TODO Auto-generated method stub
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(getResources().getColor(R.color.text_color_selection));
                    text.setTextSize((float) 13.6);
                    text.setPadding(50, 0, 50, 0);
                    return view;
                }
            };
            spinner1.setAdapter(spinner1_adapter);
            int selectionPosition = 0;
            try {
            selectionPosition = spinner1_adapter.getPosition(response.getString("Gender"));
            } catch (JSONException e) {
            e.printStackTrace();
            }
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    spinner_gender = gender.get(position);
                      if (position==0){
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }
                    else
                    {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }
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


            MultiSelectApdater_timing multiSelectApdatertiming = new MultiSelectApdater_timing(getContext(), android.R.layout.simple_spinner_dropdown_item, listVOs);
            spinner2.setAdapter(multiSelectApdatertiming);

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
                    text.setPadding(50, 0, 50, 0);

                    return view;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // TODO Auto-generated method stub
                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text1);
                    text.setTextColor(getResources().getColor(R.color.text_color_selection));
                    text.setTextSize((float) 13.6);
                    text.setPadding(50, 0, 50, 0);
                    return view;
                }
            };
            spinner3.setAdapter(spinner_area_adapter);

            int selectionPosition1 = 0;
            try {
                selectionPosition1 = spinner_area_adapter.getPosition(response.getString("Area"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    spinner_area = area.get(position);
                      if (position==0){
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }
                    else
                    {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            if (!userid.equals("")) {
                try {
                    getProfileData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            allFields.add(edt_house_num);
            allFields.add(edt_bno);
            allFields.add(edt_street);
            allFields.add(edt_block);
            allFields.add(edt_city);
            allFields.add(edt_country);


            btn_profile_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences check_field = getContext().getSharedPreferences("CheckField",
                            Context.MODE_PRIVATE);
                    final SharedPreferences.Editor profileStudent1 = check_field.edit();
                    profileStudent1.putString("selectedsubjects", "");
                    profileStudent1.apply();

                    SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("CheckField",
                            Context.MODE_PRIVATE);

                    timingSelected = sharedPreferences1.getString("timingSelected", "");
                    Log.i("TimingSelected",timingSelected);

//                    String houseno = edt_house_num.getText().toString().trim();
//                    String buildingno = edt_bno.getText().toString().trim();
//                    String streetno = edt_street.getText().toString().trim();
//                    String blockno = edt_block.getText().toString().trim();
//                    String city = edt_city.getText().toString().trim();
//                    String area = edt_area.getText().toString().trim();
//                    String country = edt_country.getText().toString().trim();
//                    String spinner_timings = spinner2.getText().toString().trim();TimingSelectedTimingSelected
                    List<EditText> ErrorFields =new ArrayList<EditText>();//empty Edit text arraylist
                    for(int j = 0; j < allFields.size(); j++){
                        if(TextUtils.isEmpty(allFields.get(j).getText())){
                            // EditText was empty
                            //   Fields.add(allFields.get(j).getText().toString());
                            ErrorFields.add(allFields.get(j));//add empty Edittext only in this ArayList
                            for(int i = 0; i < ErrorFields.size(); i++)
                            {
                                //Fields.add(ErrorFields.get(i).getText().toString());
                                EditText currentField = ErrorFields.get(i);
                                currentField.setError("this field required");
                                ErrorFields.set(i,currentField);
                                currentField.requestFocus();
                            }

                        }
                    }

                    if(ErrorFields.isEmpty() && spinner1.getSelectedItemPosition() != 0 && spinner3.getSelectedItemPosition()!=0 && !timingSelected.equals(""))
                    {

                        try {
                            Address();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    else
                    {
                        Toast.makeText(getContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                    }


                }
            });

            SharedPreferences check_field1 = getContext().getSharedPreferences("CheckField",
                    Context.MODE_PRIVATE);
            final SharedPreferences.Editor profileStudent1 = check_field1.edit();
            profileStudent1.putString("timingSelected", "");
            profileStudent1.apply();

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
        spinner3.setSelection(selectionPosition);

        edt_house_num.setText(sharedPreferences2.getString("HouseNumber",""));
        edt_bno.setText(sharedPreferences2.getString("BuildingName",""));
        edt_street.setText(sharedPreferences2.getString("StreetNumber",""));
        edt_block.setText(sharedPreferences2.getString("BlockNumber",""));
        edt_city.setText(sharedPreferences2.getString("City",""));
        edt_country.setText(sharedPreferences2.getString("Country",""));

        int selectionPosition1 = spinner1_adapter.getPosition(sharedPreferences2.getString("Gender",""));
        spinner1.setSelection(selectionPosition1);


    }

    private void Address() throws JSONException {


        loader.showLoader();
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
        map.put("housenum", edt_house_num.getText().toString());
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
        map.put("Id", Id);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url_Sprofile, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("edite_profile" , String.valueOf(response));
                try {
                    loader.hideLoader();
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
//
//                        Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            Toast.makeText(getContext(), "User Profile Created.", Toast.LENGTH_LONG).show();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
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
//                    map.put("housenum",edt_house_num.getText().toString());
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
        // RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // requestQueue.add(sr);
    }


    private void viewProfile() throws JSONException {
//        btn_profile_next.setVisibility(View.GONE);

//        edt_house_num.setEnabled(false);
//        edt_bno.setEnabled(false);
//        edt_street.setEnabled(false);
//        edt_block.setEnabled(false);
//        //       edt_area = root.findViewById(R.id.edt_area);
//        edt_city.setEnabled(false);
//        edt_country.setEnabled(false);

//        spinner1 = (Spinner) root.findViewById(R.id.spinner_gender);
//        spinner2 = (Spinner) root.findViewById(R.id.spinner_timings);
//        spinner3 = (Spinner) root.findViewById(R.id.spinner_area);
        edt_house_num.setText(response.getString("HouseNumber"));
        edt_house_num.setTextColor(getResources().getColor(R.color.textcolor));

        edt_bno.setText(response.getString("BuildingName"));
        edt_bno.setTextColor(getResources().getColor(R.color.textcolor));

        edt_street.setText(response.getString("StreetNumber"));
        edt_street.setTextColor(getResources().getColor(R.color.textcolor));

        edt_block.setText(response.getString("BlockNumber"));
        edt_block.setTextColor(getResources().getColor(R.color.textcolor));

        edt_city.setText(response.getString("City"));
        edt_city.setTextColor(getResources().getColor(R.color.textcolor));

        edt_country.setText(response.getString("Country"));
        edt_country.setTextColor(getResources().getColor(R.color.textcolor));

//        spinner1.setClickable(false);
//        spinner1.setEnabled(false);
//        spinner1.setVisibility(View.GONE);
//        spinner_gender_textview.setVisibility(View.VISIBLE);
//        spinner_gender_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
//        spinner_gender_textview.setText(response.getString("Gender"));

//        spinner2.setClickable(false);
//        spinner2.setEnabled(false);
//        spinner2.setVisibility(View.GONE);
        spinner_timings_textview.setVisibility(View.VISIBLE);
        spinner_timings_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
        spinner_timings_textview.setText(response.getString("DesiredTiming"));

//        spinner3.setClickable(false);
//        spinner3.setEnabled(false);
//        spinner3.setVisibility(View.GONE);
//        spinner_area_textview.setVisibility(View.VISIBLE);
//        spinner_area_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
//        spinner_area_textview.setText(response.getString("Area"));
    }
    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.container, new EditClass()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }


                return false;
            }
        });

    }
}


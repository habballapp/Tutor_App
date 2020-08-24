package com.example.tutor_app.Dashboard.ui.Institude.Profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.Institude.Dashboard_Drawer_Institute;
import com.example.tutor_app.Dashboard.ui.Institude.Profile.InstituteClassFragment;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.R;
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
public class InstituteAddressFragment extends Fragment {

    private Spinner spinner1, spinner2, spinner_edt_area;
    private ArrayAdapter<String> spinner_area_adapter;
    private RelativeLayout btn_profile_next ,back;
    private List<String> gender, timings, area;
    private EditText edt_address, edt_street, edt_block, edt_city, edt_country, et_amount1, et_amount2;
    String URL_INSTITUTE = "http://pci.edusol.co/InstitutePortal/instituteregistrationsubmit.php";
    String institutename, phone1, phone2, phone3, email, cperson, typeofInstitute, ctype, stype, classes, subjects,
            spinner_gender, spinner_timings, otherinstitute, spinner_area ,otherSubject;
    String userid;
    TextView spinner_area_textview, spinner_timings_textview, spinner_gender_textview ,back_txt;
    JSONObject response = new JSONObject();
    private Loader loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_institute_address, container, false);


//        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
//                Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        loader = new Loader(getContext());

        Log.i("userid_new", userid);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);


        spinner1 = (Spinner) root.findViewById(R.id.spinner_gender);
        spinner2 = (Spinner) root.findViewById(R.id.spinner_timings);
        edt_address = root.findViewById(R.id.edt_address);
        edt_street = root.findViewById(R.id.edt_street);
        edt_block = root.findViewById(R.id.edt_block);
        spinner_edt_area = root.findViewById(R.id.spinner_edt_area);
        edt_city = root.findViewById(R.id.edt_city);
        edt_country = root.findViewById(R.id.edt_country);
        et_amount1 = root.findViewById(R.id.et_amount1);
        et_amount2 = root.findViewById(R.id.et_amount2);
        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        spinner_area_textview = root.findViewById(R.id.spinner_area_textview);
        spinner_gender_textview = root.findViewById(R.id.spinner_gender_textview);
        spinner_timings_textview = root.findViewById(R.id.spinner_timings_textview);
        back = root.findViewById(R.id.back);
        back_txt = root.findViewById(R.id.back_txt);


        institutename = sharedPreferences.getString("nameofInstitute", "");
        phone1 = sharedPreferences.getString("contactno1", "");
        phone2 = sharedPreferences.getString("contactno2", "");
        phone3 = sharedPreferences.getString("contactno3", "");
        email = sharedPreferences.getString("email", "");
        cperson = sharedPreferences.getString("contactperson", "");
       classes = sharedPreferences.getString("class", "");
        subjects = sharedPreferences.getString("Select_subject", "");
        otherSubject = sharedPreferences.getString("otherSubject", "");
        typeofInstitute = sharedPreferences.getString("typeofInstitute", "");
        otherinstitute = sharedPreferences.getString("IfInstituteOther", "");
        Log.i("subject_aaa" ,subjects);
        Log.i("Other_subject" ,otherSubject);


        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        String str_response = personal_profile1.getString("ViewProfileData", "");
        if (!str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            try {
                viewProfile();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        if (str_response.equals("")) {
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

            timings = new ArrayList<>();
            timings.add("Select Preffered Timings");

            for (int i = 8; i <= 11; i++) {
                timings.add(i + ":00 am");
                timings.add(i + ":50 am");
            }
            timings.add("12:00 pm");
            timings.add("12:50 pm");
            for (int i = 1; i <= 11; i++) {

                timings.add(i + ":00 pm");
                timings.add(i + ":50 pm");
            }
            timings.add("12:00 am");


            final ArrayAdapter<String> spinner2_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, timings) {
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
            spinner2.setAdapter(spinner2_adapter);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    spinner_timings = timings.get(position);
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
            spinner_edt_area.setAdapter(spinner_area_adapter);
            spinner_edt_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            final List<EditText> allFields =new ArrayList<EditText>();

            allFields.add(edt_address);
            allFields.add(edt_street);
            allFields.add(edt_block);
            allFields.add(edt_city);
            allFields.add(edt_country);
            allFields.add(et_amount1);
            allFields.add(et_amount2);


            btn_profile_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


//                    String streetno = edt_street.getText().toString().trim();
//                    String blockno = edt_block.getText().toString().trim();
//                    String city = edt_city.getText().toString().trim();
//                    //   String area = edt_area.getText().toString().trim();
//                    String country = edt_country.getText().toString().trim();
//                    String amount1 = et_amount1.getText().toString().trim();
//                    String amount2 = et_amount2.getText().toString().trim()
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

                    Log.i("allFields", String.valueOf(allFields));
                        if(ErrorFields.isEmpty() && spinner_edt_area.getSelectedItemPosition()!= 0 && spinner2.getSelectedItemPosition()!= 0 && spinner1.getSelectedItemPosition()!=0 ){

                            try {
                                InstituteAddress();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        else{
                            Toast.makeText(getContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
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
        }

        return root;
    }

    private void getProfileData() throws JSONException {
    back.setVisibility(View.GONE);
//        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
//                Context.MODE_PRIVATE);
//        userid = sharedPreferences1.getString("UserId", "");
//        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("AddProfilePreviousData",
                Context.MODE_PRIVATE);

        int selectionPosition = spinner_area_adapter.getPosition(sharedPreferences2.getString("Area", ""));
        spinner_edt_area.setSelection(selectionPosition);

        edt_address.setText(sharedPreferences2.getString("Address", ""));
        edt_street.setText(sharedPreferences2.getString("StreetNum", ""));
        edt_block.setText(sharedPreferences2.getString("BlockNum", ""));
        edt_city.setText(sharedPreferences2.getString("City", ""));
        edt_country.setText(sharedPreferences2.getString("Country", ""));
        et_amount1.setText(sharedPreferences2.getString("SalaryFrom", ""));
        et_amount2.setText(sharedPreferences2.getString("SalaryTo", ""));

    }

    private void InstituteAddress() throws JSONException {

        JSONObject map = new JSONObject();
        loader.showLoader();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();

        List<String> selectedSubjects = gson.fromJson(subjects, type);

        JSONArray jsonArray = new JSONArray(selectedSubjects);

        map.put("nameofInstitute", institutename);
        map.put("typeofInstitute", typeofInstitute);
        map.put("contactperson", cperson);
        map.put("email", email);
        map.put("class", classes);
        map.put("subjects", jsonArray);
        map.put("contactno1", phone1);
        map.put("contactno2", phone2);
        map.put("contactno3", phone3);
        map.put("salaryfrom", et_amount1.getText().toString());
        map.put("salaryto", et_amount2.getText().toString());
        map.put("streetnum", edt_street.getText().toString());
        map.put("blocknum", edt_block.getText().toString());
        map.put("area", spinner_area);
        map.put("city", edt_city.getText().toString());
        map.put("country", edt_country.getText().toString());
        map.put("gender", spinner_gender);
        map.put("timing", spinner_timings);
        map.put("address", String.valueOf(edt_address.getText()));
        map.put("IfInstituteOther", otherinstitute);

        map.put("userid", userid);

        Log.i("mapAddress", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, URL_INSTITUTE, map, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONObject response) {
                Log.i("AddProfile", String.valueOf(response));
                loader.hideLoader();
                try {
                    if (!response.getString("instituteformId").equals("null"))
                        DiscardPopup("Successfully" ,"Your profile created successfully. ");

                    else
                        Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Error .", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loader.hideLoader();
                DiscardPopup("Error"  , "Network Connection");
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();

                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }

        };
        Volley.newRequestQueue(getContext()).add(sr);
    }


    private void viewProfile() throws JSONException {
        btn_profile_next.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
        back_txt.setText("Back");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.container, new HomeFragment()).addToBackStack("null");
                fragmentTransaction.commit();
            }
        });

        edt_address.setEnabled(false);
        edt_street.setEnabled(false);
        edt_block.setEnabled(false);
        //       edt_area = root.findViewById(R.id.edt_area);
        edt_city.setEnabled(false);
        edt_country.setEnabled(false);

        et_amount1.setEnabled(false);
        et_amount2.setEnabled(false);

        edt_address.setText(response.getString("Address"));
        edt_address.setTextColor(getResources().getColor(R.color.text_color_selection));

        edt_street.setText(response.getString("StreetNum"));
        edt_street.setTextColor(getResources().getColor(R.color.text_color_selection));

        edt_block.setText(response.getString("BlockNum"));
        edt_block.setTextColor(getResources().getColor(R.color.text_color_selection));

        edt_city.setText(response.getString("City"));
        edt_city.setTextColor(getResources().getColor(R.color.text_color_selection));

        edt_country.setText(response.getString("Country"));
        edt_country.setTextColor(getResources().getColor(R.color.text_color_selection));

        et_amount1.setText(response.getString("SalaryFrom"));
        et_amount1.setTextColor(getResources().getColor(R.color.text_color_selection));

        et_amount2.setText(response.getString("SalaryTo"));
        et_amount2.setTextColor(getResources().getColor(R.color.text_color_selection));

        spinner1.setClickable(false);
        spinner1.setEnabled(false);
//        spinner1.setVisibility(View.GONE);
        spinner_gender_textview.setVisibility(View.VISIBLE);
        spinner_gender_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
        spinner_gender_textview.setText(response.getString("Gender"));

        spinner2.setClickable(false);
        spinner2.setEnabled(false);
//        spinner2.setVisibility(View.GONE);
        spinner_timings_textview.setVisibility(View.VISIBLE);
        spinner_timings_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
        spinner_timings_textview.setText(response.getString("Timings"));

        spinner_edt_area.setClickable(false);
        spinner_edt_area.setEnabled(false);
//        spinner3.setVisibility(View.GONE);
        spinner_area_textview.setVisibility(View.VISIBLE);
        spinner_area_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
        spinner_area_textview.setText(response.getString("Area"));
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
                    fragmentTransaction.add(R.id.container, new InstituteClassFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });

    }
    private void DiscardPopup(String heading , String message) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view_popup = inflater.inflate(R.layout.successful_popup, null);
        TextView tv_discard = view_popup.findViewById(R.id.tv_discard);
        tv_discard.setText(heading);
        TextView tv_discard_txt = view_popup.findViewById(R.id.tv_discard_txt);
        tv_discard_txt.setText(message);
        alertDialog.setView(view_popup);
        alertDialog.getWindow().setGravity(Gravity.TOP | Gravity.START | Gravity.END);
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        layoutParams.y = 200;
        layoutParams.x = -70;// top margin
        alertDialog.getWindow().setAttributes(layoutParams);
        ImageButton img_email = (ImageButton) view_popup.findViewById(R.id.btn_close);
        img_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.container, new HomeFragment()).addToBackStack("null");
                fragmentTransaction.commit();
            }
        });

        alertDialog.show();
    }
}


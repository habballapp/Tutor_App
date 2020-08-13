package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Dashboard.ui.Profile.Student.SelectClass;
import com.example.tutor_app.Dashboard.ui.Qualification.Qualification;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstituteFragment extends Fragment {

    private Spinner spinner_type;
    private RelativeLayout btn_class_next;
    private List<String> itype;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_institutename, edt_phone1, edt_phone2, edt_phone3, edt_email, contact_person, edt_other;
    private String Filter_selected = "";
    String userid, viewProfile_userid;
    ArrayAdapter<String> adapter1;
    private TextView spinner_type_textview;
    String Url = "http://pci.edusol.co/InstitutePortal/view_profile_api.php";
    private Loader loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_institute, container, false);

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        viewProfile_userid = sharedPreferences2.getString("UserId", "");
        Log.i("UserId", userid);
        loader = new Loader(getContext());


        itype = new ArrayList<>();
        itype.add("Type of Institute");
        itype.add("School");
        itype.add("College");
        itype.add("University");
        itype.add("CoachingCenter");
        itype.add("Other");

        btn_class_next = root.findViewById(R.id.btn_class_next);


        edt_institutename = root.findViewById(R.id.edt_institutename);
        edt_phone1 = root.findViewById(R.id.edt_phone1);
        edt_phone2 = root.findViewById(R.id.edt_phone2);
        edt_phone3 = root.findViewById(R.id.edt_phone3);
        edt_email = root.findViewById(R.id.edt_email);
        contact_person = root.findViewById(R.id.contact_person);
        edt_other = root.findViewById(R.id.edt_other);
        spinner_type_textview = root.findViewById(R.id.spinner_type_textview);


        Log.i("UserId", userid);
        Log.i("ProfileId", viewProfile_userid);

        SharedPreferences institute_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileInstitute = institute_profile.edit();

        spinner_type = root.findViewById(R.id.spinner_type);
        adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, itype) {

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

        spinner_type.setAdapter(adapter1);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Filter_selected = itype.get(position);

                if (Filter_selected.equals("Other")) {

                    edt_other.setVisibility(View.VISIBLE);
                } else {

                    edt_other.setVisibility(View.GONE);
                }

                profileInstitute.putString("typeofInstitute", String.valueOf(itype.get(position)));
                profileInstitute.apply();
                Log.i("Value:", String.valueOf(String.valueOf(itype.get(position))));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final List<EditText> allFields =new ArrayList<EditText>();
        allFields.add( edt_institutename);
        allFields.add(edt_phone1);
//        allFields.add(edt_phone2);
//        allFields.add( edt_phone3);
        allFields.add(edt_phone3);
        allFields.add(edt_email);
        allFields.add(contact_person);

        if (!viewProfile_userid.equals("")) {
            viewProfile();
        } else if (!userid.equals("")) {
            getProfileData();
        }



        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(String.valueOf(edt_other.getText()).equals(""))) {
                    profileInstitute.putString("IfInstituteOther", String.valueOf(edt_other.getText()));
                } else {
                    profileInstitute.putString("IfInstituteOther", " ");
                }

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
                if (viewProfile_userid.equals("")) {
                    if(ErrorFields.isEmpty() && spinner_type.getSelectedItemPosition() != 0){

                        profileInstitute.putString("nameofInstitute", String.valueOf(edt_institutename.getText()));
                        profileInstitute.putString("contactperson", String.valueOf(contact_person.getText()));
                        profileInstitute.putString("contactno1", String.valueOf(edt_phone1.getText()));
                        profileInstitute.putString("contactno2", String.valueOf(edt_phone2.getText()));
                        profileInstitute.putString("contactno3", String.valueOf(edt_phone3.getText()));
                        profileInstitute.putString("email", String.valueOf(edt_email.getText()));
                        profileInstitute.apply();


                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteClassFragment()).addToBackStack("tag");
                        fragmentTransaction.commit();
                        Toast.makeText(getContext()," All Fields",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                        ((TextView)spinner_type.getSelectedView()).setError("Error message");
                        spinner_type.setPadding(0, 10, 20, 0);
                    }

                }
                else
                    {
                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteClassFragment()).addToBackStack("tag");
                        fragmentTransaction.commit();
                        Toast.makeText(getContext()," All Fields",Toast.LENGTH_SHORT).show();
                }


            }
        });

        return root;
    }

    private void viewProfile() {

        loader.showLoader();
        JSONObject map = new JSONObject();
        try {
            map.put("InstituteId", viewProfile_userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("institutedebug", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile", String.valueOf(response));
                loader.hideLoader();
                Gson gson = new Gson();
//                Type type = new TypeToken<JSONObject>(){}.getType();

                SharedPreferences personal_profile = getContext().getSharedPreferences("ViewProfile",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor profileStudent = personal_profile.edit();
                profileStudent.putString("ViewProfileData", gson.toJson(response));
                profileStudent.apply();


                edt_institutename.setEnabled(false);
                edt_institutename.setTextColor(getResources().getColor(R.color.text_color_selection));
                spinner_type.setEnabled(false);
                spinner_type.setClickable(false);
                spinner_type_textview.setVisibility(View.VISIBLE);
                spinner_type_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_phone1.setEnabled(false);
                edt_phone1.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_phone2.setEnabled(false);
                edt_phone2.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_phone3.setEnabled(false);
                edt_phone3.setTextColor(getResources().getColor(R.color.text_color_selection));
                edt_email.setEnabled(false);
                edt_email.setTextColor(getResources().getColor(R.color.text_color_selection));
                contact_person.setEnabled(false);
                contact_person.setTextColor(getResources().getColor(R.color.text_color_selection));

                adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, itype) {

                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        // TODO Auto-generated method stub
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(getResources().getColor(R.color.transparent_color));
                        text.setTextSize((float) 13.6);
                        text.setPadding(30, 0, 30, 0);

                        return view;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // TODO Auto-generated method stub
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(getResources().getColor(R.color.transparent_color));
                        text.setTextSize((float) 13.6);
                        text.setPadding(30, 0, 30, 0);
                        return view;
                    }
                };

                spinner_type.setAdapter(adapter1);


                try {
                    edt_institutename.setText(response.getString("InstituteName"));
                    spinner_type_textview.setText(response.getString("TypeOfInstitute"));
                    edt_phone1.setText(response.getString("ContactNo1"));
                    edt_phone2.setText(response.getString("ContactNo2"));
                    edt_phone3.setText(response.getString("ContactNo3"));
                    edt_email.setText(response.getString("Email"));
                    contact_person.setText(response.getString("ContactPerson"));
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
        };
        Volley.newRequestQueue(getContext()).add(sr);

    }

    private void getProfileData() {

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("AddProfilePreviousData",
                Context.MODE_PRIVATE);

//        int selectionPosition = adapter1.getPosition(sharedPreferences2.getString("TypeOfInstitute", ""));
//        spinner_type.setSelection(selectionPosition);
        edt_institutename.setText(sharedPreferences2.getString("InstituteName", ""));
        edt_phone1.setText(sharedPreferences2.getString("ContactNo1", ""));
        edt_phone2.setText(sharedPreferences2.getString("ContactNo2", ""));
        edt_phone3.setText(sharedPreferences2.getString("ContactNo3", ""));
        edt_email.setText(sharedPreferences2.getString("Email", ""));
        contact_person.setText(sharedPreferences2.getString("ContactPerson", ""));

        Log.i("Email", String.valueOf(edt_email.getText()));
        Log.i("ContactPerson", String.valueOf(contact_person.getText()));
        Log.i("Phone1", String.valueOf(edt_phone1.getText()));
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
                    fragmentTransaction.add(R.id.container, new HomeFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }


                return false;
            }
        });

    }
}

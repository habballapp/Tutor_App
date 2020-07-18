package com.example.tutor_app.Dashboard.ui.AreaofInterest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.AreaFragmentAdapter;
import com.example.tutor_app.Adapters.AreaFragmentAdapterView;
import com.example.tutor_app.Dashboard.ui.References.ReferenceFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.Model_Classes.AreaFragment_List;
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
public class AreaFragment extends Fragment {

    private RelativeLayout btn_area_next,btn_area_add;
    private RecyclerView rl_recycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentTransaction fragmentTransaction;
    private List<String> area;
    private List<AreaFragment_List> list = new ArrayList<>();
    String Url = "http://pci.edusol.co/TeacherPortal/view_profile_api.php";
    JSONObject response;
    private Loader loader;
    String edt_classes_track, edt_pref_subject, spinner_area;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_area, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        btn_area_add = root.findViewById(R.id.btn_area_add);
        final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData_AreaFragment",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor areaFragment = area_fragmnt_data.edit();
        loader = new Loader(getContext());

//        spinner_area = root.findViewById(R.id.spinner_area);
//        edt_classes_track = root.findViewById(R.id.edt_classes_track);
//        edt_pref_subject = root.findViewById(R.id.edt_pref_subject);

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
        area.add("Nazimabad Town");
        area.add("Orangi Town");
        area.add(" Shah Faisal Town");
        area.add(" SITE Town");
        area.add("Lyari Town");
        area.add(" Malir Town");



        list.add(new AreaFragment_List(" "," "," "));

        layoutManager = new LinearLayoutManager(getContext());


        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new AreaFragmentAdapter(this.getContext(), list);
        rl_recycler.setAdapter(adapter);

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        String str_response = personal_profile1.getString("ViewProfileData", "");

        Log.i("Job Experience", str_response);

        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        if (! str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            Log.i("JobExperience", String.valueOf(response));

            viewProfile();
        }




        btn_area_next = root.findViewById(R.id.btn_area_next);
//        areaFragment.putString("ClassToTeach",String.valueOf(edt_classes_track.getText()));
//        areaFragment.putString("PreferredSubjects",String.valueOf(edt_pref_subject.getText()));
//        areaFragment.apply();

        btn_area_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.add(new AreaFragment_List("", "", ""));
                adapter.notifyDataSetChanged();
            }
        });




        btn_area_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData",
                        Context.MODE_PRIVATE);

                edt_classes_track = area_fragmnt_data.getString("classtoteach", "");
                edt_pref_subject = area_fragmnt_data.getString("prefsubject", "");
                spinner_area = area_fragmnt_data.getString("prefarea", "");

                if(!edt_classes_track.equals("") && !edt_pref_subject.equals("") && !spinner_area.equals(""))
                {
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new ReferenceFragment());
                    fragmentTransaction.commit();

                }
                else {

                    if (edt_classes_track.equals("")) {
                        Toast.makeText(getContext(), "Please Enter Class Field", Toast.LENGTH_SHORT).show();
                    } else if (edt_pref_subject.equals("")) {
                        Toast.makeText(getContext(), "Please Enter Subjects Field", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Please Enter Area Field", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        return root;
    }

    private void viewProfile() {

        loader.showLoader();
        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("ViewData",
                Context.MODE_PRIVATE);
        String userid = sharedPreferences1.getString("UserId", "");
        JSONObject map = new JSONObject();
        try {
            map.put("TutorId", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
              //  Log.i("Area of Interest", String.valueOf(response));
                loader.hideLoader();



                try {
                    JSONArray area = response.getJSONArray("AreaOfInterest");
                    Log.i("Area22", String.valueOf(area));
                    layoutManager = new LinearLayoutManager(getContext());

                    rl_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new AreaFragmentAdapterView(getContext(),area);
                    rl_recycler.setAdapter(adapter);

                    btn_area_add.setVisibility(View.GONE);

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
}
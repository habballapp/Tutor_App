package com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.tutor_app.Adapters.AreaFragmentAdapter;
import com.example.tutor_app.Adapters.AreaFragmentAdapterView;
import com.example.tutor_app.Adapters.MultiSelectApdater_T_Area;
import com.example.tutor_app.Adapters.MultiSelectApdater_timing;
import com.example.tutor_app.Dashboard.ui.Student.Profile.StateVO;
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

    private RelativeLayout btn_area_next, btn_area_add, back ,edt_area;
    private RecyclerView rl_recycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentTransaction fragmentTransaction;
    private List<String> area;
    private List<String> select_area;
    private List<AreaFragment_List> list = new ArrayList<>();
    String Url = "http://pci.edusol.co/TeacherPortal/view_profile_api.php";
    JSONObject response;
    private Loader loader;
    private Spinner spinr_area;
    String edt_classes_track, edt_pref_subject, spinner_area;
    private TextView text_area_selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_area, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        btn_area_add = root.findViewById(R.id.btn_area_add);
        text_area_selected = root.findViewById(R.id.text_area_selected);
        edt_area = root.findViewById(R.id.edt_area);
        back = root.findViewById(R.id.back);
        final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData_AreaFragment",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor areaFragment = area_fragmnt_data.edit();
        loader = new Loader(getContext());

        spinr_area = root.findViewById(R.id.spin_area);
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
        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < area.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(area.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }


        MultiSelectApdater_T_Area multiSelectApdater_t_area = new MultiSelectApdater_T_Area(getContext(), android.R.layout.simple_spinner_dropdown_item, listVOs);
        spinr_area.setAdapter(multiSelectApdater_t_area);

        spinr_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaFragment.putString("prefarea",(String.valueOf(area.get(position))));
                areaFragment.apply();
             Log.i("AreaSelected", area.get(position) + " - " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        list.add(new AreaFragment_List(" ", " ", " "));

        layoutManager = new LinearLayoutManager(getContext());


        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new AreaFragmentAdapter(this.getContext(), list);
        rl_recycler.setAdapter(adapter);

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        final String str_response = personal_profile1.getString("ViewProfileData", "");

        Log.i("Job Experience", str_response);

        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        if (!str_response.equals("")) {
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
                if (!str_response.equals("")) {
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new ReferenceFragment());
                    fragmentTransaction.commit();
                } else {
                    final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData",
                            Context.MODE_PRIVATE);

                    edt_classes_track = area_fragmnt_data.getString("classtoteach", "");
                    edt_pref_subject = area_fragmnt_data.getString("prefsubject", "");
                    spinner_area = area_fragmnt_data.getString("prefarea", "");

                    if (!edt_classes_track.equals("") && !edt_pref_subject.equals("") && !spinner_area.equals("")) {
                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new ReferenceFragment());
                        fragmentTransaction.commit();

                    } else {

                        if (edt_classes_track.equals("")) {
                            Toast.makeText(getContext(), "Please Enter Class Field", Toast.LENGTH_SHORT).show();
                        } else if (edt_pref_subject.equals("")) {
                            Toast.makeText(getContext(), "Please Enter Subjects Field", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Please Enter Area Field", Toast.LENGTH_SHORT).show();
                        }

                    }

                }


            }
        });

        return root;
    }

    private void viewProfile() {

        loader.showLoader();
        btn_area_add.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
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
                 Log.i("Area_Of_Interest", String.valueOf(response));
                loader.hideLoader();
                try {
                    text_area_selected.setVisibility(View.VISIBLE);
                    edt_area.setVisibility(View.GONE);
                    JSONArray areaSelect = response.getJSONArray("AreaOfInterested");
                    Log.i("areaSelect", String.valueOf(areaSelect));
                    for (int i = 0; i < areaSelect.length(); i++) {
                        try {
                            String id = areaSelect.getJSONObject(i).getString("PreferredArea");
                            Log.i("selectedDataArea", id );
                            //you can set value to text view here
                            text_area_selected.setText(id);
                            text_area_selected.setTextColor(getResources().getColor(R.color.text_color_selection));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    //spinner_area_textview.setText(areaSelect.toJSONObject().getString("PreferredArea"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    JSONArray area = response.getJSONArray("AreaOfInterest");
                    Log.i("Area22", String.valueOf(area));
                    text_area_selected.setVisibility(View.VISIBLE);
                    layoutManager = new LinearLayoutManager(getContext());

                    rl_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new AreaFragmentAdapterView(getContext(), area);
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

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.container, new JobExperienceFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });

    }
}
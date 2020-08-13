package com.example.tutor_app.Dashboard.ui.JobExperience;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.SelectClassAdapter;
import com.example.tutor_app.Adapters.SelectClassAdapterView;
import com.example.tutor_app.Dashboard.ui.AreaofInterest.AreaFragment;
import com.example.tutor_app.Dashboard.ui.Qualification.Qualification;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.Model_Classes.JobExperince_List;
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
public class JobExperienceFragment extends Fragment {

    private RecyclerView rl_recycler;
    private RelativeLayout btn_experience_next, btn_experience_add;
    private FragmentTransaction fragmentTransaction;
    RecyclerView.Adapter adapter;
    String Url = "http://pci.edusol.co/TeacherPortal/view_profile_api.php";
    JSONObject response;
    String edt_etitlement, edt_organization, edt_from, edt_till;
    private RecyclerView.LayoutManager layoutManager;
    private Loader loader;
    private List<JobExperince_List> List = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_job_experience, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);

        btn_experience_next = root.findViewById(R.id.btn_experience_next);
        btn_experience_add = root.findViewById(R.id.btn_experience_add);
        loader = new Loader(getContext());



        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        final String str_response = personal_profile1.getString("ViewProfileData", "");

        Log.i("Job Experience", str_response);

        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        if (! str_response.equals("")) {
                response = gson.fromJson(str_response, type);
                Log.i("JobExperience", String.valueOf(response));

            viewProfile();
        }


        layoutManager = new LinearLayoutManager(getContext());


        List.add(new JobExperince_List("", "", "", ""));

        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new SelectClassAdapter(this.getContext(), List);
        rl_recycler.setAdapter(adapter);

        btn_experience_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.add(new JobExperince_List("", "", "", ""));
                adapter.notifyDataSetChanged();
//                rl_recycler.notify();

            }
        });

        btn_experience_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! str_response.equals("")) {
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new AreaFragment());
                    fragmentTransaction.commit();
                } else {
                    final SharedPreferences job_experience = getContext().getSharedPreferences("SendData",
                            Context.MODE_PRIVATE);
                    edt_etitlement = job_experience.getString("JobEntitlement", "");
                    edt_organization = job_experience.getString("OrganizationName", "");
                    edt_from = job_experience.getString("FromTo", "");
                    edt_till = job_experience.getString("Till", "");


                    if (!edt_etitlement.equals("") && ! edt_organization.equals("") && ! edt_from.equals("") && ! edt_till.equals("")) {
                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new AreaFragment());
                        fragmentTransaction.commit();
                    } else {
                        if (edt_etitlement.equals("")) {
                            Toast.makeText(getContext(), "Please Enter Job Title", Toast.LENGTH_SHORT).show();
                        } else if (edt_organization.equals("")) {
                            Toast.makeText(getContext(), "Please Enter Name Of Organization", Toast.LENGTH_SHORT).show();
                        } else if (edt_from.equals("")) {
                            Toast.makeText(getContext(), "Please Enter From Field", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Please Enter Till Field", Toast.LENGTH_SHORT).show();
                        }


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
                Log.i("JobExperience", String.valueOf(response));
                loader.hideLoader();

                try {
                    JSONArray job_experience = response.getJSONArray("Experience");
                    layoutManager = new LinearLayoutManager(getContext());

                    rl_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new SelectClassAdapterView(getContext(), job_experience);
                    rl_recycler.setAdapter(adapter);

                    btn_experience_add.setVisibility(View.GONE);

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
                    fragmentTransaction.add(R.id.container, new Qualification()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });

    }

    }


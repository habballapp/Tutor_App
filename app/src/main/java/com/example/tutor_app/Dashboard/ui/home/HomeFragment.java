package com.example.tutor_app.Dashboard.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.MyAdapter_Child;
import com.example.tutor_app.Dashboard.ui.Institude.Dashboard_Drawer_Institute;
import com.example.tutor_app.Dashboard.ui.Student.Profile.StateVO;
import com.example.tutor_app.Dashboard.ui.Student.SearchFragment.FragmentSearch;
import com.example.tutor_app.Dashboard.ui.Institude.SearchFragment.InstituteSearchFragment;
import com.example.tutor_app.Dashboard.ui.Teacher.SearchFragment.TeacherSearchFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.MyJsonArrayRequest;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RelativeLayout btn_search, spinner_chlds;
    private FragmentTransaction fragmentTransaction;
    String userrole, teacherId;

    private LinearLayout total_salary;
    private TextView demo_request_value, demo_scheduled_value, demo_confrm_value, demo_recject_value, total_salary_value;
    String URL_TEACHER_DATA = "http://pci.edusol.co/TeacherPortal/view_demostatus_api.php";
    String URL_STUDENT_DATA = "http://pci.edusol.co/StudentPortal/view_demostatus_api.php";
    String URL_INSTITUTE_DATA = "http://pci.edusol.co/InstitutePortal/view_demostatus_api.php";
    String Url = "http://pci.edusol.co/StudentPortal/searchtutorApi.php";
    String Url_Institute = "http://pci.edusol.co/InstitutePortal/searchjobsApi.php";
    private Loader loader;
    private Spinner sp_child_list;
    private List<String> childs = new ArrayList<>();
    private String selectedId, selected_Insti;
    private List<String> institute = new ArrayList<>();
    private Map<String, String> instituteMap = new HashMap<>();

    private Map<String, String> childsMap = new HashMap<>();
    private String userid, childID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btn_search = root.findViewById(R.id.btn_search);
        demo_request_value = root.findViewById(R.id.demo_request_value);
        demo_scheduled_value = root.findViewById(R.id.demo_scheduled_value);
        demo_confrm_value = root.findViewById(R.id.demo_confrm_value);
        demo_recject_value = root.findViewById(R.id.demo_recject_value);
        total_salary_value = root.findViewById(R.id.total_salary_value);
        total_salary = root.findViewById(R.id.total_salary);
        sp_child_list = root.findViewById(R.id.sp_child_list);
        spinner_chlds = root.findViewById(R.id.spinner_chlds);
        loader = new Loader(getContext());


        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences2.getString("userid", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        Type typeMap = new TypeToken<Map<String, String>>() {
        }.getType();
        childs = gson.fromJson(sharedPreferences2.getString("children", ""), type);
        Log.i("Id", userid);
        // Log.i("Id", String.valueOf(childMap));

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userrole = sharedPreferences1.getString("userrole", "");

        Log.i("UserRole", userrole);
        if (userrole.equals("Teacher")) {
            total_salary.setVisibility(View.VISIBLE);
            viewProfileTeacher();
        }
        if (userrole.equals("Student")) {
            try {
                ViewChild();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ViewJobs();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        btn_search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                switch (userrole) {
                    case "Student":

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new FragmentSearch());
                        fragmentTransaction.commit();
                        ;
                        break;
                    case "Teacher":

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new TeacherSearchFragment());
                        fragmentTransaction.commit();

                        break;
                    case "Institute":

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteSearchFragment());
                        fragmentTransaction.commit();
                        break;
                }


            }
        });

        return root;
    }

    private void viewProfileTeacher() {
        spinner_chlds.setVisibility(View.GONE);
        loader.showLoader();
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        teacherId = sharedPreferences1.getString("userid", "");
        Log.i("ID", teacherId);
        JSONObject map = new JSONObject();
        try {
            map.put("TutorId", teacherId);
//
            Log.i("map_dashboard", String.valueOf(map));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, URL_TEACHER_DATA, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile_dashboard", String.valueOf(response));
                loader.hideLoader();
                try {
                    demo_request_value.setText(response.getString("demo_requested"));
                    demo_scheduled_value.setText(response.getString("demo_scheduled"));
                    demo_confrm_value.setText(response.getString("demo_confirmtution"));
                    demo_recject_value.setText(response.getString("demo_rejected"));
                    total_salary_value.setText(response.getString("demo_totalsalary"));
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

    private void viewProfileStudent() {

        loader.showLoader();
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        teacherId = sharedPreferences1.getString("userid", "");
        //  Log.i("ID",selectedID);
        JSONObject map = new JSONObject();
        try {
            map.put("StudentId", selectedId);
//
            Log.i("map_dashboard", String.valueOf(map));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, URL_STUDENT_DATA, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile_dashboard", String.valueOf(response));
                loader.hideLoader();
                try {
                    demo_request_value.setText(response.getString("demo_requested"));
                    demo_scheduled_value.setText(response.getString("demo_scheduled"));
                    demo_confrm_value.setText(response.getString("demo_confirmtution"));
                    demo_recject_value.setText(response.getString("demo_rejected"));
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

    private void viewProfileInstitute() {

        loader.showLoader();
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        teacherId = sharedPreferences1.getString("userid", "");
        Log.i("ID", teacherId);
        JSONObject map = new JSONObject();
        try {
            map.put("InstituteId", selected_Insti);
//
            Log.i("map_dashboard", String.valueOf(map));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, URL_INSTITUTE_DATA, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile_dashboard", String.valueOf(response));
                loader.hideLoader();
                try {
                    demo_request_value.setText(response.getString("demo_requested"));
                    demo_scheduled_value.setText(response.getString("demo_scheduled"));
                    demo_confrm_value.setText(response.getString("demo_confirmtution"));
                    demo_recject_value.setText(response.getString("demo_rejected"));
                    total_salary_value.setText(response.getString("demo_totalsalary"));
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

    private void ViewChild() throws JSONException {


        loader.showLoader();
        JSONObject map = new JSONObject();

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Log.i("Id", userid);
        map.put("userid", userid);
        Log.i("ID", String.valueOf(map));

        MyJsonArrayRequest sr = new MyJsonArrayRequest(Request.Method.POST, Url, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loader.hideLoader();
                childs = new ArrayList<>();
                childs.add("Select Child");
                Log.i("Search", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = new JSONObject(response.getString(i));
                        childs.add(obj.getString("StudentName"));
                        childsMap.put(obj.getString("StudentName"), obj.getString("Id"));
                        Log.i("students_id", String.valueOf(childsMap));
                        Log.i("student_name", String.valueOf(childs));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ArrayAdapter<String> adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, childs) {
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

                sp_child_list.setAdapter(adapter_location);
                sp_child_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        demo_request_value.setText("");
                        demo_scheduled_value.setText("");
                        demo_confrm_value.setText("");
                        demo_recject_value.setText("");
                        if (position == 0) {
                            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                            ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                            ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                        } else {
                            String selectChild = childs.get(position);
                            selectedId = childsMap.get(selectChild);
                            Log.i("selectedID", selectedId);
                            viewProfileStudent();
                            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                            ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                            ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

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
        // RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        // requestQueue.add(sr);

    }

    private void ViewJobs() throws JSONException {


        loader.showLoader();
        institute = new ArrayList<>();
        institute.add("Select Job");
        JSONObject map = new JSONObject();
        map.put("userid", userid);
        Log.i("userIDDDDD", userid);

        MyJsonArrayRequest sr = new MyJsonArrayRequest(Request.Method.POST, Url_Institute, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("Institute", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = new JSONObject(response.getString(i));
                        institute.add(obj.getString("Name"));
                        instituteMap.put(obj.getString("Name"), obj.getString("Id"));

                        Log.i("instituteMap", String.valueOf(instituteMap));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ArrayAdapter<String> adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, institute) {
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

                sp_child_list.setAdapter(adapter_location);
                sp_child_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        demo_request_value.setText("");
                        demo_scheduled_value.setText("");
                        demo_confrm_value.setText("");
                        demo_recject_value.setText("");
                        if (position == 0) {
                            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                            ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                            ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                        } else {
                            String selectjob = institute.get(position);
                            selected_Insti = instituteMap.get(selectjob);
                            Log.i("selected_ID", selectjob);
                            Log.i("selected_Job", selected_Insti);
                            viewProfileInstitute();
                            ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                            ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                            ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


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

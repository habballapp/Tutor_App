package com.example.tutor_app.Dashboard.ui.View;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.TeacherViewStudentAdapter;
import com.example.tutor_app.Dashboard.ui.Teacher.SearchFragment.TeacherSearchFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.Model_Classes.ViewStudent_List;
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
public class TeacherViewStudent extends Fragment {


    private RecyclerView rl_recycler;
    RecyclerView.Adapter adapter;
    private List<ViewStudent_List> list = new ArrayList<>();
    String Url = "http://pci.edusol.co/TeacherPortal/searchstudent_institutesubmit.php";
    String area,userid;
    private TextView txt_nodata;
    private Loader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view, container, false);


        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        txt_nodata = root.findViewById(R.id.txt_nodata);
        loader = new Loader(getContext());
        txt_nodata.setText("No Student Available");


        try {
            ViewStudent();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return root;
    }

    private void ViewStudent() throws JSONException {

        loader.showLoader();
        JSONObject map = new JSONObject();

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("SearchData",
                Context.MODE_PRIVATE);
        area = sharedPreferences2.getString("area", "");

        Log.i("Area2",area);
        Log.i("Id",userid);

        map.put("teacherid",userid);
        map.put("area",area);

        Log.i("url_map", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject result) {
                JSONArray response = null;
                loader.hideLoader();
                try {
                    response = result.getJSONArray("studentData");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("View", String.valueOf(response));
                if(response.length() <= 0){

                    txt_nodata.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"No Student Available",Toast.LENGTH_LONG).show();

                }
                else{
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<ViewStudent_List>>() {}.getType();
                    adapter = new TeacherViewStudentAdapter(getContext(), (List<ViewStudent_List>) gson.fromJson(response.toString(), type));
                    rl_recycler.setAdapter(adapter);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(sr);


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
                    fragmentTransaction.add(R.id.container, new TeacherSearchFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }
                return false;
            }
        });

    }
}

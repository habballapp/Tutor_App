package com.example.tutor_app.Dashboard.ui.Institude.SearchFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.InstituteViewTeacherAdapter;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.Model_Classes.InstituteViewTeacher_List;
import com.example.tutor_app.MyJsonArrayRequest;
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
public class ViewFragmentInstitute extends Fragment {

    private RecyclerView rl_recycler;
    RecyclerView.Adapter adapter;
    private TextView txt_nodata ,txt_heading;
    String locationarea,searchinstitute;
    private List<InstituteViewTeacher_List> list = new ArrayList<>();
    private Loader loader;
    private TextView tool_bar_heading;
    String Url = "http://pci.edusol.co/InstitutePortal/searchtutorsubmit.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        tool_bar_heading = toolbar.findViewById(R.id.tool_bar_heading);
        tool_bar_heading.setText("Search");


        rl_recycler = root.findViewById(R.id.rv_search);
        txt_heading = root.findViewById(R.id.txt_heading);
        txt_heading.setText("Teachers");
        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        txt_nodata = root.findViewById(R.id.txt_nodata);
        txt_nodata.setText("No Teachers are Available");
        loader = new Loader(getContext());

        try {
            ViewTeacher();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return root;
    }

    private void ViewTeacher() throws JSONException {

        JSONObject map = new JSONObject();
        loader.showLoader();

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("SearchData",
                Context.MODE_PRIVATE);
        locationarea = sharedPreferences1.getString("locationarea", "");
        searchinstitute = sharedPreferences1.getString("InstituteName", "");
        Log.i("Name",searchinstitute);



        JSONArray arr = new JSONArray(searchinstitute);
        Log.i("Area",locationarea);
        Log.i("Child",searchinstitute);
        map.put("searchinstitute", arr);
        map.put("locationarea", locationarea);
        Log.i("url_map", String.valueOf(map));

        MyJsonArrayRequest sr = new MyJsonArrayRequest(Request.Method.POST, Url, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("All_data ", String.valueOf(response));
                loader.hideLoader();
                if(response.length()<=0){

                    txt_nodata.setVisibility(View.VISIBLE);
                   // Toast.makeText(getContext(),"No Teachers are Available",Toast.LENGTH_LONG).show();
                }
                else{


                    Gson gson = new Gson();
                    Type type = new TypeToken<List<InstituteViewTeacher_List>>() {}.getType();
                    adapter = new InstituteViewTeacherAdapter((List<InstituteViewTeacher_List>) gson.fromJson(response.toString(), type),getContext());
                    rl_recycler.setAdapter(adapter);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                txt_nodata.setVisibility(View.VISIBLE);
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
    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    tool_bar_heading.setText("Search");
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.container, new InstituteSearchFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }


                return false;
            }
        });

    }


}

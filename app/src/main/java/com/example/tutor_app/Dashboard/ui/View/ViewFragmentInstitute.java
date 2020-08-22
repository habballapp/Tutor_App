package com.example.tutor_app.Dashboard.ui.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.Model_Classes.View_List;
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
    private TextView txt_nodata;
    String locationarea,searchinstitute;
    private List<View_List> list = new ArrayList<>();
    private Loader loader;
    String Url = "http://pci.edusol.co/InstitutePortal/searchtutorsubmit.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        txt_nodata = root.findViewById(R.id.txt_nodata);
        loader = new Loader(getContext());

        // List<View_List> view_list =;
        //  adapter = new ViewAdapter("Shabbir ","teacher","0323223","phone","shabbir@gmail.com","person" ,"abc","eng","400","monthy",getContext());


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
                    Toast.makeText(getContext(),"No Teachers are Available",Toast.LENGTH_LONG).show();
                }
                else{


                    Gson gson = new Gson();
                    Type type = new TypeToken<List<View_List>>() {}.getType();
                  //  adapter = new ViewAdapterInstitute(getContext(), (List<View_List>) gson.fromJson(response.toString(), type));
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



}

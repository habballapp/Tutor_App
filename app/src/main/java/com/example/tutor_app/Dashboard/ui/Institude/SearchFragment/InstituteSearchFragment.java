package com.example.tutor_app.Dashboard.ui.Institude.SearchFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.MyAdapter_Institute;
import com.example.tutor_app.Dashboard.ui.Student.Profile.StateVO;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.MyJsonArrayRequest;
import com.example.tutor_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstituteSearchFragment extends Fragment {

    private RelativeLayout rl_next;
    private List<String> area,childs;
    private FragmentTransaction fragmentTransaction;
    private Spinner spinner_location,add_institute;
    private Map<String, String> childsMap = new HashMap<>();
    String Url = "http://pci.edusol.co/InstitutePortal/searchtutorApi.php";
    String userid;
    private TextView tool_bar_heading;
    private Loader loader;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.activity_institute_search_fragment, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar();
        tool_bar_heading = toolbar.findViewById(R.id.tool_bar_heading);
        tool_bar_heading.setText("Search");
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


        SharedPreferences personal_profile = getContext().getSharedPreferences("SearchData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileStudent = personal_profile.edit();


        spinner_location = (Spinner) root.findViewById(R.id.spinner_location);
        add_institute = (Spinner) root.findViewById(R.id.add_institute);
        loader = new Loader(getContext());

        rl_next = root.findViewById(R.id.rl_next);

        rl_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new ViewFragmentInstitute());
                fragmentTransaction.commit();
            }
        });


        ArrayAdapter<String> adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,area) {
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

        spinner_location.setAdapter(adapter_location);
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                Log.i("Area", String.valueOf(area.get(position)));
                profileStudent.putString("locationarea", String.valueOf(area.get(position)));
                profileStudent.apply();
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


        try {
            ViewChild();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return root;

    }

    private void ViewChild() throws JSONException {


        JSONObject map = new JSONObject();
        loader.showLoader();

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Log.i("Id",userid);
        map.put("userid",userid);

        MyJsonArrayRequest sr = new MyJsonArrayRequest(Request.Method.POST, Url, map, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                loader.hideLoader();
                childs = new ArrayList<>();
                childs.add("Select Institute");
                Log.i("Search", String.valueOf(response));
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = new JSONObject(response.getString(i));
                        childs.add(obj.getString("InstituteName"));
                        childsMap.put(obj.getString("InstituteName"), obj.getString("Id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ArrayList<StateVO> listVOs = new ArrayList<>();

                for (int i = 0; i < childs.size(); i++) {
                    StateVO stateVO = new StateVO();
                    stateVO.setTitle(childs.get(i));
                    stateVO.setSelected(false);
                    listVOs.add(stateVO);
                }

               MyAdapter_Institute myAdapter = new MyAdapter_Institute(getContext(), android.R.layout.simple_spinner_dropdown_item, listVOs, childsMap);
                add_institute.setAdapter(myAdapter);

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
    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    tool_bar_heading.setText("Dashboard");
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

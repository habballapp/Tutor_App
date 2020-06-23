package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutor_app.Adapters.MyAdapter;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressClass extends Fragment {

    
    private Spinner spinner1,spinner2;
    private List<String> gender,timings;
    private String Filter_selected = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_address_class, container, false);

        spinner1 = (Spinner) root.findViewById(R.id.spinner_gender);
        spinner2 = (Spinner) root.findViewById(R.id.spinner_timings);

        gender = new ArrayList<>();
        gender.add("Select Preffered Gender");
        gender.add("Male");
        gender.add("Female");
        gender.add("Any");

        timings = new ArrayList<>();
        timings.add("Select Preffered Timings");
        for(int i = 8; i <= 23; i++)
        {
            timings.add(i+"-"+ ++i);
                                --i;
        }



        ArrayAdapter<String> spinner1_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gender) {
            @Override
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
        spinner1.setAdapter(spinner1_adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < timings.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(timings.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }


        MyAdapter myAdapter = new MyAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,listVOs);
        spinner2.setAdapter(myAdapter);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return root;
    }
}

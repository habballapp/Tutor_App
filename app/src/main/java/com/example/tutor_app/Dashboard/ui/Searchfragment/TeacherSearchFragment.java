package com.example.tutor_app.Dashboard.ui.Searchfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutor_app.Dashboard.ui.View.TeacherViewInstitute;
import com.example.tutor_app.Dashboard.ui.View.TeacherViewStudent;
import com.example.tutor_app.Dashboard.ui.View.ViewFragmentStudent;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;


public class TeacherSearchFragment extends Fragment {

    private Spinner spinner_location;
    private List<String> area;
    private RelativeLayout rl_search_institute,rl_search_student;
    private FragmentTransaction fragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_teacher_search, container, false);

        SharedPreferences personal_profile = getContext().getSharedPreferences("SearchData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileStudent = personal_profile.edit();

        spinner_location = root.findViewById(R.id.spinner_location);
        rl_search_institute = root.findViewById(R.id.rl_search_institute);
        rl_search_student = root.findViewById(R.id.rl_search_student);

        ArrayAdapter<String> adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,area) {
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

        spinner_location.setAdapter(adapter_location);
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.i("Area", String.valueOf(area.get(position)));
                profileStudent.putString("area", String.valueOf(area.get(position)));
                profileStudent.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rl_search_institute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new TeacherViewInstitute());
                fragmentTransaction.commit();
                
            }
        });

        rl_search_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new TeacherViewStudent());
                fragmentTransaction.commit();

            }
        });



        return  root;
    }
}

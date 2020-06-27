package com.example.tutor_app.Dashboard.ui.AreaofInterest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutor_app.Dashboard.ui.JobExperience.JobExperienceFragment;
import com.example.tutor_app.Dashboard.ui.References.ReferenceFragment;
import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends Fragment {

    private RelativeLayout btn_area_next;
    private Spinner spinner_area;
    private FragmentTransaction fragmentTransaction;
    private List<String> area;
    private EditText edt_classes_track,edt_pref_subject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_area, container, false);
        final SharedPreferences personal_profile = getContext().getSharedPreferences("SendData_AreaFragment",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor areaFragment = personal_profile.edit();

        spinner_area = root.findViewById(R.id.spinner_area);
        edt_classes_track = root.findViewById(R.id.edt_classes_track);
        edt_pref_subject = root.findViewById(R.id.edt_pref_subject);

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
        area.add("Orangi Town");
        area.add(" Shah Faisal Town");
        area.add(" SITE Town");
        area.add("Lyari Town");
        area.add(" Malir Town");


        final ArrayAdapter<String> spinner_area_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, area) {
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
        spinner_area.setAdapter(spinner_area_adapter);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areaFragment.putString("PreferredArea",String.valueOf(spinner_area));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        btn_area_next = root.findViewById(R.id.btn_area_next);
        areaFragment.putString("ClassToTeach",String.valueOf(edt_classes_track.getText()));
        areaFragment.putString("PreferredSubjects",String.valueOf(edt_pref_subject.getText()));
        areaFragment.apply();




        btn_area_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new ReferenceFragment());
                fragmentTransaction.commit();


            }
        });

        return root;
    }
}
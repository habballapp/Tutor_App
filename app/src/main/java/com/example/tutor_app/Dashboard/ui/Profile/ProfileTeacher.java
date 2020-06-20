package com.example.tutor_app.Dashboard.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tutor_app.Dashboard.ui.Qualification.Qualification;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileTeacher extends Fragment {

    private RelativeLayout btn_profile_next;
    private FragmentTransaction fragmentTransaction;
    private  TextView edt_conveyance_txt;
    private Spinner spinner,spinner1;
 //   private static final String[] paths = {"Are you a Teacher by Profession?", "Yes", "No"};
 //    private static final String[] paths1 = {"Do you have conveyance?", "Yes", "No"};
    private List<String> paths,paths1;
    private String Filter_selected = "";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        edt_conveyance_txt = root.findViewById(R.id.edt_conveyance_txt);

        btn_profile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new Qualification());
                fragmentTransaction.commit();
            }
        });

        paths = new ArrayList<>();
        paths.add("Are you a Teacher by Profession?");
        paths.add("Yes");
        paths.add("No");

        paths1 = new ArrayList<>();
        paths1.add("Do you have conveyance?");
        paths1.add("Yes");
        paths1.add("No");


        spinner = (Spinner) root.findViewById(R.id.spinner);
        spinner1 = (Spinner) root.findViewById(R.id.spinner1);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, paths);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, paths) {
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
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, paths1);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, paths1) {
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
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    edt_conveyance_txt.setVisibility(View.GONE);

                }else{


                }
                Filter_selected = paths1.get(position);

                if (Filter_selected.equals("Yes")) {

                    edt_conveyance_txt.setVisibility(View.VISIBLE);

                } else if (Filter_selected.equals("No")) {

                    edt_conveyance_txt.setVisibility(View.GONE);
                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return root;
    }
}

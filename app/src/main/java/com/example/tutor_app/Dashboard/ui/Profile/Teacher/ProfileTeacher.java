package com.example.tutor_app.Dashboard.ui.Profile.Teacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private EditText edt_fullname,edt_fname,edt_mtongue,edt_occupation,edt_cnic,edt_present_address,
                       edt_permanent_address,edt_dob,edt_nationality,edt_religion,edt_phone1,edt_phone2,
                      edt_email,edt_conveyance_txt,edt_age;

    private Spinner teacher_profession,spinner_conveyance;
 //   private static final String[] paths = {"Are you a Teacher by Profession?", "Yes", "No"};
 //    private static final String[] paths1 = {"Do you have conveyance?", "Yes", "No"};
    private List<String> paths,paths1;
    private String Filter_selected = "";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        final SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
         final SharedPreferences.Editor profileTeacher = personal_profile.edit();


        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        edt_conveyance_txt = root.findViewById(R.id.edt_conveyance_txt);
        edt_fullname = root.findViewById(R.id.edt_fullname);
        edt_fname = root.findViewById(R.id.edt_fname);
        edt_mtongue = root.findViewById(R.id.edt_mtongue);
        edt_occupation = root.findViewById(R.id.edt_occupation);
        edt_cnic = root.findViewById(R.id.edt_cnic);
        edt_present_address = root.findViewById(R.id.edt_present_address);
        edt_permanent_address = root.findViewById(R.id.edt_permanent_address);
        edt_dob = root.findViewById(R.id.edt_dob);
        edt_age  = root.findViewById(R.id.edt_age);
        edt_email = root.findViewById(R.id.edt_email);
        edt_phone1 = root.findViewById(R.id.edt_phone1);
        edt_phone2 = root.findViewById(R.id.edt_phone2);
        edt_conveyance_txt = root.findViewById(R.id.edt_conveyance_txt);
        edt_nationality = root.findViewById(R.id.edt_nationality);
        edt_religion = root.findViewById(R.id.edt_religion);
       






        paths = new ArrayList<>();
        paths.add("Are you a Teacher by Profession?");
        paths.add("Yes");
        paths.add("No");

        paths1 = new ArrayList<>();
        paths1.add("Do you have conveyance?");
        paths1.add("Yes");
        paths1.add("No");


        teacher_profession = root.findViewById(R.id.teacher_profession);
        spinner_conveyance = root.findViewById(R.id.spinner_conveyance);


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
        teacher_profession.setAdapter(adapter);
        teacher_profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                profileTeacher.putString("teacherbyprofession",String.valueOf(teacher_profession));

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
        spinner_conveyance.setAdapter(adapter1);
        spinner_conveyance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    edt_conveyance_txt.setVisibility(View.GONE);

                }else{


                }
                Filter_selected = paths1.get(position);

                if (Filter_selected.equals("Yes")) {

                    edt_conveyance_txt.setVisibility(View.VISIBLE);
                    profileTeacher.putString("personalconveyance",String.valueOf(spinner_conveyance));
                    profileTeacher.putString("carbike",String.valueOf(edt_conveyance_txt.getText()));

                } else if (Filter_selected.equals("No")) {

                    edt_conveyance_txt.setVisibility(View.GONE);
                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_profile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                profileTeacher.putString("fullname",String.valueOf(edt_fullname.getText()));
                profileTeacher.putString("fathusname",String.valueOf(edt_fname.getText()));
                profileTeacher.putString("mothnametounge",String.valueOf(edt_mtongue.getText()));
                profileTeacher.putString("dob",String.valueOf(edt_dob.getText()));
                profileTeacher.putString("age",String.valueOf(edt_age.getText()));
                profileTeacher.putString("nationality",String.valueOf(edt_nationality.getText()));
                profileTeacher.putString("religion",String.valueOf(edt_religion.getText()));
                profileTeacher.putString("cnicno",String.valueOf(edt_cnic.getText()));
                profileTeacher.putString("presentadd",String.valueOf(edt_present_address.getText()));
                profileTeacher.putString("permanentadd",String.valueOf(edt_permanent_address.getText()));
                profileTeacher.putString("phoneno1",String.valueOf(edt_phone1.getText()));
                profileTeacher.putString("phoneno2",String.valueOf(edt_phone2.getText()));
                profileTeacher.putString("email",String.valueOf(edt_email.getText()));

              //  profileTeacher.putString("teacherbyprofession",String.valueOf(spinner_));
                // profileTeacher.putString("dateofsubmission",String.valueOf(edt_da.getText()));
                profileTeacher.apply();

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new Qualification());
                fragmentTransaction.commit();
            }
        });



        return root;
    }
}

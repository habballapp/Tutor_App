package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.example.tutor_app.R;


public class ProfileStudent extends Fragment {
    private RelativeLayout btn_class_next;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_email,edt_fullname,edt_phone1,edt_phone2,edt_phone3,edt_fname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile_student, container, false);

        btn_class_next = root.findViewById(R.id.btn_class_next);
        edt_email = root.findViewById(R.id.edt_email);
        edt_fullname = root.findViewById(R.id.edt_fullname);
        edt_fname = root.findViewById(R.id.edt_fname);
        edt_phone1 = root.findViewById(R.id.edt_phone1);
        edt_phone2 = root.findViewById(R.id.edt_phone2);
        edt_phone3 = root.findViewById(R.id.edt_phone3);


        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor profileStudent = personal_profile.edit();
                profileStudent.putString("name",String.valueOf(edt_fullname.getText()));
                profileStudent.putString("fathername",String.valueOf(edt_fname.getText()));
                profileStudent.putString("email",String.valueOf(edt_email.getText()));
                profileStudent.putString("contactno1",String.valueOf(edt_phone1.getText()));
                profileStudent.putString("contactno2",String.valueOf(edt_phone2.getText()));
                profileStudent.putString("contactno3",String.valueOf(edt_phone3.getText()));
                profileStudent.apply();

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new SelectClass());
                fragmentTransaction.commit();



            }
        });

        return root;
    }


}

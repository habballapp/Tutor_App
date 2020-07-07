package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tutor_app.R;

import org.json.JSONException;

import java.util.ArrayList;


public class ProfileStudent extends Fragment {
    private RelativeLayout btn_class_next;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_email,edt_fullname,edt_phone1,edt_phone2,edt_phone3,edt_fname;

    String userid;



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

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);


        if (!userid.equals("")) {
            try {
                getProfileData();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



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

    private void getProfileData() throws JSONException {

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("UserId",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        Log.i("UserId", userid);

        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("AddProfilePreviousData",
                Context.MODE_PRIVATE);



        edt_fname.setText(sharedPreferences2.getString("FatherName",""));
        edt_phone1.setText(sharedPreferences2.getString("ContactNo1",""));
        edt_phone2.setText(sharedPreferences2.getString("ContactNo2",""));
        edt_phone3.setText(sharedPreferences2.getString("ContactNo3",""));
        edt_email.setText(sharedPreferences2.getString("StudentEmail",""));
    }
}

package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.tutor_app.R;


public class ProfileStudent extends Fragment {
    private RelativeLayout btn_class_next;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile_student, container, false);

        btn_class_next = root.findViewById(R.id.btn_class_next);

        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new SelectClass());
                fragmentTransaction.commit();

            }
        });

        return root;
    }
}

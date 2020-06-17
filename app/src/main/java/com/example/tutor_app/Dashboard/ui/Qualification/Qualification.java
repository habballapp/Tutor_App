package com.example.tutor_app.Dashboard.ui.Qualification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.tutor_app.Dashboard.ui.JobExperience.JobExperienceFragment;
import com.example.tutor_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Qualification extends Fragment {

    private RelativeLayout btn_qualification_next;
    private FragmentTransaction fragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_section2, container, false);

        btn_qualification_next = root.findViewById(R.id.btn_qualification_next);

        btn_qualification_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new JobExperienceFragment());
                fragmentTransaction.commit();
            }
        });

        return root;
    }
}

package com.example.tutor_app.Dashboard.ui.JobExperience;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.tutor_app.Dashboard.ui.AreaofInterest.AreaFragment;
import com.example.tutor_app.Dashboard.ui.References.ReferenceFragment;
import com.example.tutor_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobExperienceFragment extends Fragment {

    private RelativeLayout btn_experience_next;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_job_experience, container, false);

        btn_experience_next = root.findViewById(R.id.btn_experience_next);

        btn_experience_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new AreaFragment());
                fragmentTransaction.commit();
            }
        });

        return root;
    }
}

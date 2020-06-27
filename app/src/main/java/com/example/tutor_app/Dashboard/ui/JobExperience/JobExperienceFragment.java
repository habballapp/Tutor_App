package com.example.tutor_app.Dashboard.ui.JobExperience;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    private EditText edt_etitlement,edt_organization,edt_from,edt_till;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_job_experience, container, false);

        btn_experience_next = root.findViewById(R.id.btn_experience_next);
        edt_etitlement = root.findViewById(R.id.edt_etitlement);
        edt_organization = root.findViewById(R.id.edt_organization);
        edt_from = root.findViewById(R.id.edt_from);
        edt_till = root.findViewById(R.id.edt_till);

        final SharedPreferences job_experience = getContext().getSharedPreferences("SendData_Experience",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher_experience = job_experience.edit();
        profileTeacher_experience.putString("JobEntitlement",String.valueOf(edt_etitlement.getText()));
        profileTeacher_experience.putString("OrganizationName",String.valueOf(edt_organization.getText()));
        profileTeacher_experience.putString("FromTo",String.valueOf(edt_from.getText()));
        profileTeacher_experience.putString("Till",String.valueOf(edt_till.getText()));
        profileTeacher_experience.apply();

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

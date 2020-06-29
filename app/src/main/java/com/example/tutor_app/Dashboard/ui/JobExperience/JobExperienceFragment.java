package com.example.tutor_app.Dashboard.ui.JobExperience;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.SelectClassAdapter;
import com.example.tutor_app.Dashboard.ui.AreaofInterest.AreaFragment;
import com.example.tutor_app.Model_Classes.JobExperince_List;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobExperienceFragment extends Fragment {

    private RecyclerView rl_recycler;
    private RelativeLayout btn_experience_next, btn_experience_add;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_etitlement,edt_organization,edt_from,edt_till;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<JobExperince_List> List = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_job_experience, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);

        btn_experience_next = root.findViewById(R.id.btn_experience_next);
        btn_experience_add = root.findViewById(R.id.btn_experience_add);
//        edt_etitlement = root.findViewById(R.id.edt_etitlement);
//        edt_organization = root.findViewById(R.id.edt_organization);
//        edt_from = root.findViewById(R.id.edt_from);
//        edt_till = root.findViewById(R.id.edt_till);
//


        layoutManager = new LinearLayoutManager(getContext());


        List.add(new JobExperince_List("", "", "", ""));

        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new SelectClassAdapter(this.getContext(), List);
        rl_recycler.setAdapter(adapter);

        btn_experience_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.add(new JobExperince_List("", "", "", ""));
                adapter.notifyDataSetChanged();
//                rl_recycler.notify();

            }
        });

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

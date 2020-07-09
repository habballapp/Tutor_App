package com.example.tutor_app.Dashboard.ui.AreaofInterest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutor_app.Adapters.AreaFragmentAdapter;
import com.example.tutor_app.Adapters.SelectClassAdapter;
import com.example.tutor_app.Dashboard.ui.JobExperience.JobExperienceFragment;
import com.example.tutor_app.Dashboard.ui.References.ReferenceFragment;
import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
import com.example.tutor_app.Model_Classes.AreaFragment_List;
import com.example.tutor_app.Model_Classes.JobExperince_List;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends Fragment {

    private RelativeLayout btn_area_next,btn_area_add;
    private RecyclerView rl_recycler;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentTransaction fragmentTransaction;
    private List<String> area;
    private EditText edt_classes_track,edt_pref_subject;
    private List<AreaFragment_List> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_area, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        btn_area_add = root.findViewById(R.id.btn_area_add);
        final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData_AreaFragment",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor areaFragment = area_fragmnt_data.edit();

//        spinner_area = root.findViewById(R.id.spinner_area);
//        edt_classes_track = root.findViewById(R.id.edt_classes_track);
//        edt_pref_subject = root.findViewById(R.id.edt_pref_subject);

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
        area.add("Nazimabad Town");
        area.add("Orangi Town");
        area.add(" Shah Faisal Town");
        area.add(" SITE Town");
        area.add("Lyari Town");
        area.add(" Malir Town");



        list.add(new AreaFragment_List(" "," "," "));

        layoutManager = new LinearLayoutManager(getContext());


        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new AreaFragmentAdapter(this.getContext(), list);
        rl_recycler.setAdapter(adapter);





        btn_area_next = root.findViewById(R.id.btn_area_next);
//        areaFragment.putString("ClassToTeach",String.valueOf(edt_classes_track.getText()));
//        areaFragment.putString("PreferredSubjects",String.valueOf(edt_pref_subject.getText()));
//        areaFragment.apply();

        btn_area_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.add(new AreaFragment_List("", "", ""));
                adapter.notifyDataSetChanged();
            }
        });




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
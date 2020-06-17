package com.example.tutor_app.Dashboard.ui.Searchfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentSearch extends Fragment {

    private Spinner spinner_container_fees,spinner_container_fees1;
    private List<String> consolidate_felter;
    private ArrayAdapter<String> arrayAdapterFeltter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_search, container, false);

//        consolidate_felter = new ArrayList<>();
//        consolidate_felter.add("Select Criteria");
//        consolidate_felter.add("Payment ID");
//        consolidate_felter.add("Company");
//        consolidate_felter.add("Status");
//        consolidate_felter.add("Paid Date");
//        consolidate_felter.add("Amount");
//
//        spinner_container_fees =  root.findViewById(R.id.spinner_container_fees);



        return root;




    }
}

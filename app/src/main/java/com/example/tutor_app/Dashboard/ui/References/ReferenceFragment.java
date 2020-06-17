package com.example.tutor_app.Dashboard.ui.References;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tutor_app.R;


public class ReferenceFragment extends Fragment {

    private RelativeLayout btn_reference_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reference, container, false);

        btn_reference_user = root.findViewById(R.id.btn_reference_user);

        btn_reference_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG);

            }
        });

        return root;
    }
}

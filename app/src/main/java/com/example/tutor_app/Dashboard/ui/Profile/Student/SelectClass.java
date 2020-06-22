package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.SelectClassAdapter;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClass extends Fragment {


    private RecyclerView rl_recycler;
    private Parcelable recyclerViewState;
    RecyclerView.Adapter adapter;
    private RelativeLayout btn_class_add;
    List<String> classes = new ArrayList<>();
    List<String> subjects = new ArrayList<>();
    int count = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_class__selection, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        btn_class_add = root.findViewById(R.id.btn_class_add);
        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        count++;
        classes.add("Class " + count);

        adapter = new SelectClassAdapter(getContext(), classes);
        rl_recycler.setAdapter(adapter);


        btn_class_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                classes.add("Class " + count);

                adapter = new SelectClassAdapter(getContext(), classes);
                rl_recycler.setAdapter(adapter);



            }
        });

        return root;
}
}

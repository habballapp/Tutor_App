package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.MyAdapter;
import com.example.tutor_app.Adapters.MyAdapter2;
import com.example.tutor_app.Adapters.SelectClassAdapter;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClass extends Fragment {



    public EditText edt_email;
    public Spinner spinner_class,spinner_subject;
    RecyclerView.Adapter adapter;
    private RelativeLayout btn_profile_next;
    private FragmentTransaction fragmentTransaction;
    List<String> classes = new ArrayList<>();
   List<String> subjects = new ArrayList<>();
    int count = 0;
   // List<String> spinner1,subjects;
    


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_class__selection, container, false);
//        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        edt_email = root.findViewById(R.id.edt_email);
        spinner_class = root.findViewById(R.id.spinner_class);
        spinner_subject = root.findViewById(R.id.spinner_subject);
//        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        count++;
       // classes.add("Class " + count);

        classes = new ArrayList<>();
        classes.add("Select Class");
        for(int i = 1; i <= 12; i++)
        {
            classes.add("Class " + i);
            classes.add("O-Level");
            classes.add("A-Level");


        }

        subjects = new ArrayList<>();
        subjects.add("Select Subject");
        subjects.add("Mathematics");
        subjects.add("English");
        subjects.add("Urdu");
        subjects.add("Islamiyat");
        subjects.add("P. St.");
        subjects.add("Geography");
        subjects.add("History");
        
        
        
        

//        adapter = new SelectClassAdapter(getContext(), classes);
//        rl_recycler.setAdapter(adapter);


//        btn_class_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//                classes.add("Class " + count);
//
//                adapter = new SelectClassAdapter(getContext(), classes);
//                rl_recycler.setAdapter(adapter);
//
//
//
//            }
//        });


        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(classes.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }


        MyAdapter2 myAdapter = new MyAdapter2(getContext(), android.R.layout.simple_spinner_dropdown_item,listVOs);
        spinner_class.setAdapter(myAdapter);

        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<StateVO> listSubjects = new ArrayList<>();

        for (int i = 0; i < subjects.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(subjects.get(i));
            stateVO.setSelected(false);
            listSubjects.add(stateVO);
        }


        MyAdapter2 myAdapter1 = new MyAdapter2(getContext(), android.R.layout.simple_spinner_dropdown_item,listSubjects);
        spinner_subject.setAdapter(myAdapter1);

        spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btn_profile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new AddressClass());
                fragmentTransaction.commit();
            }
        });

        return root;
}
}

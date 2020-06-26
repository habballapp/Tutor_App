package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.MyAdapter2;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClass extends Fragment {



    public EditText edt_school;
    public Spinner spinner_class,spinner_subject;
    private StateVO stateVO;
    RecyclerView.Adapter adapter;
    private Button btn_profile_next;
    private FragmentTransaction fragmentTransaction;
    List<String> classes = new ArrayList<>();
   List<String> subjects = new ArrayList<>();
    int count = 0;
   // List<String> spinner1,subjects;
    


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_class__selection, container, false);
//        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        edt_school = root.findViewById(R.id.edt_school);
        spinner_class = root.findViewById(R.id.spinner_class);
        spinner_subject = root.findViewById(R.id.spinner_subject);
//        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        count++;
       // classes.add("Class " + count);





        classes.add("Select Class");
        classes.add("O-Level");
        classes.add("A-Level");
        for(int i = 1; i <= 12; i++)
        {
            classes.add("Class " + i);
        }


        subjects.add("Select Subject");
        subjects.add("Select All");
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


//        ArrayList<StateVO> listVOs = new ArrayList<>();
//
//        for (int i = 0; i < classes.size(); i++) {
//            StateVO stateVO = new StateVO();
//            stateVO.setTitle(classes.get(i));
//            stateVO.setSelected(false);
//            listVOs.add(stateVO);
//        }

//
//        MyAdapter2 myAdapter = new MyAdapter2(getContext(), android.R.layout.simple_spinner_dropdown_item,listVOs);
//        spinner_class.setAdapter(myAdapter);

        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, classes) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);

                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);
                return view;
            }
        };

        spinner_class.setAdapter(adapter_class);

        SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileStudent = personal_profile.edit();

        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    profileStudent.putString("class", String.valueOf(classes.get(position)));
                    profileStudent.apply();
                    Log.i("Value:", String.valueOf(String.valueOf(classes.get(position))));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayList<StateVO> listSubjects = new ArrayList<>();

        for (int i = 0; i < subjects.size(); i++) {
            stateVO = new StateVO();
            stateVO.setTitle(subjects.get(i));
            stateVO.setSelected(false);
            listSubjects.add(stateVO);
        }


        final MyAdapter2 myAdapter1 = new MyAdapter2(getContext(), android.R.layout.simple_spinner_dropdown_item, listSubjects);
        spinner_subject.setAdapter(myAdapter1);

        spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


//                if (listSubjects.get(position).equals("Select All")) {
//                    for (int i = 0; i < subjects.size(); i++) {
//                        stateVO = new StateVO();
//                        stateVO.setSelected(true);
//                        listSubjects.add(stateVO);
//                    }
//                    myAdapter1.notifyDataSetChanged();
//                }

                Log.i("Subject", String.valueOf(String.valueOf(subjects.get(position))));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btn_profile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profileStudent.putString("schoolcollege",String.valueOf(edt_school.getText()));
                profileStudent.apply();

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new AddressClass());
                fragmentTransaction.commit();
            }
        });



        return root;
}
}

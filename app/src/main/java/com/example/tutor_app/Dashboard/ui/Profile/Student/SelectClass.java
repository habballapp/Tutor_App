package com.example.tutor_app.Dashboard.ui.Profile.Student;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.ImageSpan;
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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.MyAdapter_Subjects;
import com.example.tutor_app.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectClass extends Fragment {



    public EditText edt_school;
    public TextView txt;
    public Spinner spinner_class,spinner_subject;
    private StateVO stateVO;
    RecyclerView.Adapter adapter;
    private Button btn_profile_next;
    private FragmentTransaction fragmentTransaction;
    List<String> classes = new ArrayList<>();
    List<String> subjects = new ArrayList<>();
    JSONObject response = new JSONObject();
    ArrayAdapter<String> adapter_class;
    MyAdapter_Subjects myAdapter1;
    private TextView spinner_class_textview, spinner_subject_textview;
   // List<String> spinner1,subjects;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        txt = root.findViewById(R.id.txt);
        spinner_class_textview = root.findViewById(R.id.spinner_class_textview);
        spinner_subject_textview = root.findViewById(R.id.spinner_subject_textview);
//        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

       // classes.add("Class " + count);

        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        String str_response = personal_profile1.getString("ViewProfileData", "");
        if (! str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            try {
                viewProfile();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        String msg="   "+"To add another child to your account you must complete and submit your application first and select add child option to add another child";

        ImageSpan mImageSpan = new ImageSpan(getContext(), R.drawable.ic_info_black_24dp);
        SpannableString text = new SpannableString(msg);
        text.setSpan(mImageSpan, 0, 1, 0);
        txt.setElegantTextHeight(true);
        txt.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        txt.setSingleLine(false);
        txt.setText(text);


        classes.add("Select Desired Class");
        for(int i = 1; i <= 8; i++)
        {
            classes.add("Class " + i);
        }
        classes.add("Matric 9");
        classes.add("Matric 10");
        classes.add("Inter year 1");
        classes.add("Inter year 2");
        classes.add("OLevel year 1");
        classes.add("OLevel year 2");
        classes.add("OLevel year 3");
        classes.add("ALevel year 1");
        classes.add("ALevel year 2");

        subjects.add("Select Subjects");
        subjects.add("Maths");
        subjects.add("English");
        subjects.add("Urdu");
        subjects.add("Islamiat");
        subjects.add("Pak. Studies");
        subjects.add("Geography");
        subjects.add("History");
        subjects.add("Chemistry");
        subjects.add("Science");
        subjects.add("Computer");
        subjects.add("Sindhi");
        subjects.add("Biology");
        subjects.add("Physics");
        subjects.add("Add Maths");
        subjects.add("Others");

        SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileStudent = personal_profile.edit();

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
        if (str_response.equals("")) {
            adapter_class = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, classes) {
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


            myAdapter1 = new MyAdapter_Subjects(getContext(), android.R.layout.simple_spinner_dropdown_item, listSubjects);
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

        }


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

    private void viewProfile() throws JSONException {
        edt_school.setEnabled(false);
//        spinner_class.setSelected();
        spinner_class.setClickable(false);
        spinner_class.setEnabled(false);
//        spinner_class.setVisibility(View.GONE);

        spinner_subject.setClickable(false);
        spinner_subject.setEnabled(false);
//        spinner_subject.setVisibility(View.GONE);

        spinner_class_textview.setVisibility(View.VISIBLE);
        spinner_class_textview.setText(response.getString("Class"));
        spinner_class_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

        spinner_subject_textview.setVisibility(View.VISIBLE);
        spinner_subject_textview.setText(response.getString("Subjects"));
        spinner_subject_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

        txt.setVisibility(View.GONE);

        edt_school.setText(response.getString("SchoolCollege"));
        edt_school.setTextColor(getResources().getColor(R.color.text_color_selection));
    }
}

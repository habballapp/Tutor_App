package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutor_app.Adapters.AreaFragmentAdapter;
import com.example.tutor_app.Adapters.MyAdapter_Subjects;
import com.example.tutor_app.Dashboard.ui.Profile.Student.AddressClass;
import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.Dashboard.ui.Qualification.ExpandOrCollapse;
import com.example.tutor_app.Model_Classes.AreaFragment_List;
import com.example.tutor_app.Model_Classes.Institute_Information_List;
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
public class InstituteClassFragment extends Fragment {

    private Spinner spinner_class, spinner_subject;
    public TextView txt;
    List<String> classes = new ArrayList<>();
    List<String> subjects = new ArrayList<>();
    private StateVO stateVO;
    //    private RecyclerView rl_recycler;
    private RelativeLayout btn_class_add, btn_class_next, add_more;
    private EditText ctype, stype;
    private boolean isVisible = false;
    RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ExpandOrCollapse mAnimationManager;
    private FragmentTransaction fragmentTransaction;
    private List<Institute_Information_List> list = new ArrayList<>();
    private JSONObject response = new JSONObject();
    private TextView spinner_class_textview, spinner_subject_textview;
    String selectedsubject = "";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_institute_class, container, false);

        spinner_class = root.findViewById(R.id.spinner_class);
        spinner_subject = root.findViewById(R.id.spinner_subject);
        spinner_class_textview = root.findViewById(R.id.spinner_class_textview);
        spinner_subject_textview = root.findViewById(R.id.spinner_subject_textview);
        txt = root.findViewById(R.id.txt);
//        ctype = root.findViewById(R.id.ctype);
//        stype = root.findViewById(R.id.stype);
//        btn_class_add = root.findViewById(R.id.btn_class_add);
//        rl_recycler = root.findViewById(R.id.rv_fragment);
//          add_more = root.findViewById(R.id.add_more);
        btn_class_next = root.findViewById(R.id.btn_class_next);

        String msg = "   " + "To add another job to your account you must complete and submit your application first and select add job option to add another job";

        ImageSpan mImageSpan = new ImageSpan(getContext(), R.drawable.ic_info_black_24dp);
        SpannableString text = new SpannableString(msg);
        text.setSpan(mImageSpan, 0, 1, 0);
        txt.setElegantTextHeight(true);
        txt.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        txt.setSingleLine(false);
        txt.setText(text);


        classes.add("Select Class");

        for (int i = 1; i <= 8; i++) {
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
        subjects.add("Islamiyat");
        subjects.add("Pak.Studies");
        subjects.add("Geography");
        subjects.add("History");
        subjects.add("Chemistry");
        subjects.add("Sindhi");
        subjects.add("Physics");
        subjects.add("Add. Maths");
        subjects.add("Others");


        Gson gson = new Gson();
        Type type = new TypeToken<JSONObject>() {
        }.getType();

        SharedPreferences personal_profile1 = getContext().getSharedPreferences("ViewProfile",
                Context.MODE_PRIVATE);
        final String str_response = personal_profile1.getString("ViewProfileData", "");

        if (!str_response.equals("")) {
            response = gson.fromJson(str_response, type);
            try {
                viewProfile();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences institute_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileInstitute = institute_profile.edit();

//        final MyAdapter2 Adapter_Classes = new MyAdapter2(getContext(), android.R.layout.simple_spinner_dropdown_item,listClasses);
//        spinner_class.setAdapter(Adapter_Classes);
        if (str_response.equals("")) {

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


            ArrayList<StateVO> listSubjects = new ArrayList<>();

            for (int i = 0; i < subjects.size(); i++) {
                stateVO = new StateVO();
                stateVO.setTitle(subjects.get(i));
                stateVO.setSelected(false);
                listSubjects.add(stateVO);
            }

            MyAdapter_Subjects Adapter_Subjects = new MyAdapter_Subjects(getContext(), android.R.layout.simple_spinner_dropdown_item, listSubjects);
            spinner_subject.setAdapter(Adapter_Subjects);
        }
//
//
//        btn_class_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                  add_more.setVisibility(View.VISIBLE);
//                    list.add(new Institute_Information_List("", ""));
//                    adapter.notifyDataSetChanged();
//            }
//
//        });

//        btn_class_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                        if (!isVisible) {
//                    mAnimationManager.expand(add_more, 0);
//                    isVisible = true;
//                } else if (isVisible){
//                mAnimationManager.expand(add_more, 0);
//                    isVisible = false;
//                     getResources().getDrawable(R.drawable.ic_remove_black_24dp);
//            }
//
//
//            }
//        });


//        layoutManager = new LinearLayoutManager(getContext());
//        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        adapter = new InstituteInformationAdapter(getContext(), list);
//        rl_recycler.setAdapter(adapter);


        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("CheckField",
                        Context.MODE_PRIVATE);

                selectedsubject = sharedPreferences1.getString("selectedsubjects", "");
                Log.i("SelectedSubject",selectedsubject);

                if (str_response.equals("")) {

                    if(spinner_class.getSelectedItemPosition() !=0 && !selectedsubject.equals("")){

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteAddressFragment());
                        fragmentTransaction.commit();

                    }
                    else
                    {
                        Toast.makeText(getContext(),"Please Enter All Fields",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteAddressFragment());
                    fragmentTransaction.commit();

                }

                SharedPreferences check_field1 = getContext().getSharedPreferences("CheckField",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor profileStudent1 = check_field1.edit();
                profileStudent1.putString("selectedsubjects", "");
                profileStudent1.apply();




            }
        });
        return root;
    }


    private void viewProfile() throws JSONException {
        spinner_class.setClickable(false);
        spinner_class.setEnabled(false);
//        spinner_class.setVisibility(View.GONE);

        spinner_subject.setClickable(false);
        spinner_subject.setEnabled(false);
//        spinner_subject.setVisibility(View.GONE);

        spinner_class_textview.setVisibility(View.VISIBLE);
        spinner_class_textview.setText(response.getString("InstituteClass"));
        spinner_class_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

        spinner_subject_textview.setVisibility(View.VISIBLE);
        spinner_subject_textview.setText(response.getString("Subjects"));
        spinner_subject_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

        txt.setVisibility(View.GONE);
    }
}

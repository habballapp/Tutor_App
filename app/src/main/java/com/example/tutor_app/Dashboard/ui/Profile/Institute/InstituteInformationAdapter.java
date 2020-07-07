package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.AreaFragmentAdapter;
import com.example.tutor_app.Adapters.MyAdapter_Subjects;
import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.Model_Classes.AreaFragment_List;
import com.example.tutor_app.Model_Classes.Institute_Information_List;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

class InstituteInformationAdapter extends RecyclerView.Adapter<InstituteInformationAdapter.ViewHolder> {

    private Context context;
    private List<Institute_Information_List> list;
    private List<String> classes = new ArrayList<>();
    private List<String> subjects = new ArrayList<>();
    private StateVO stateVO;



    public InstituteInformationAdapter(Context context, List<Institute_Information_List> list) {
        this.context = context;
        this.list = list;
        classes.add("Select Class");

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
        subjects.add("Islamiyat");
        subjects.add("Pak.Studies");
        subjects.add("Geography");
        subjects.add("History");
        subjects.add("Chemistry");
        subjects.add("Sindhi");
        subjects.add("Physics");
        subjects.add("Add. Maths");
        subjects.add("Others");

    }

    @NonNull
    @Override
    public InstituteInformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.add_recycler, null);
        return new InstituteInformationAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final InstituteInformationAdapter.ViewHolder holder, int position) {

        SharedPreferences institute_profile = context.getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileInstitute = institute_profile.edit();

//        holder.ctype.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                if(!(String.valueOf(holder.ctype.getText()) == null))
//                {
//                    profileInstitute.putString("otherclass",String.valueOf(holder.ctype.getText()));
//                    profileInstitute.apply();
//
//
//                }
//                else
//                {
//                    profileInstitute.putString("otherclass"," ");
//                    profileInstitute.apply();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        holder.stype.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                if(!(String.valueOf(holder.stype.getText()) == null))
//                {
//                    profileInstitute.putString("othersubjects",String.valueOf(holder.stype.getText()));
//                    profileInstitute.apply();
//
//
//                }
//                else
//                {
//                    profileInstitute.putString("othersubjects"," ");
//                    profileInstitute.apply();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


        ArrayAdapter<String> adapter_class = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, classes) {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(context.getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);

                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(context.getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);
                return view;
            }
        };

        holder.spinner_class.setAdapter(adapter_class);

        SharedPreferences personal_profile = context.getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileStudent = personal_profile.edit();

        holder.spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

//                    profileStudent.putString("class", String.valueOf(classes.get(position)));
//                    profileStudent.apply();
//                    Log.i("Value:", String.valueOf(String.valueOf(classes.get(position))));
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

        MyAdapter_Subjects Adapter_Subjects = new  MyAdapter_Subjects(context, android.R.layout.simple_spinner_dropdown_item,listSubjects);
        holder.spinner_subject.setAdapter(Adapter_Subjects);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Spinner spinner_class,spinner_subject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner_class = itemView.findViewById(R.id. spinner_class);
            spinner_subject = itemView.findViewById(R.id.spinner_subject);


        }
    }
}

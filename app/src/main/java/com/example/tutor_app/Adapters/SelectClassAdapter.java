package com.example.tutor_app.Adapters;

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

import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.Model_Classes.JobExperince_List;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

public class SelectClassAdapter extends RecyclerView.Adapter<SelectClassAdapter.ViewHolder> {


    private Context context;
    private List<JobExperince_List> list;
    private RecyclerView rl_recycler;
    private RecyclerView.Adapter mAdapter;

    public SelectClassAdapter(Context context, List<JobExperince_List> list) {
        this.context = context;
        this.list = list;

    }

//    String email;
//    private List<String> classes;
//    private List<String> subjects;
//    List<String> spinner1,spinner2;



//    public SelectClassAdapter(final Context context, List<String> classItem) {
//        this.context = context;
//        this.classes = classItem;
//        spinner1 = new ArrayList<>();
//        spinner1.add("Select Class");
//        for(int i = 1; i <= 12; i++)
//            spinner1.add("Class " + i);
//        spinner1.add("O-Level");
//        spinner1.add("A-Level");
//
//
//        spinner2 = new ArrayList<>();
//        spinner2.add("Select Subject");
//        spinner2.add("Mathematics");
//        spinner2.add("English");
//        spinner2.add("Urdu");
//        spinner2.add("Islamiyat");
//        spinner2.add("P. St.");
//        spinner2.add("Geography");
//        spinner2.add("History");
//    }

    @NonNull
    @Override
    public SelectClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.class_recycler, null);
        return new SelectClassAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectClassAdapter.ViewHolder holder, int position) {

         holder.edt_etitlement.setText(list.get(position).getJobtitle());
         holder.edt_organization.setText(list.get(position).getOrgname());
         holder.edt_from.setText(list.get(position).getFromto());
         holder.edt_till.setText(list.get(position).getTill());

        final SharedPreferences job_experience = context.getSharedPreferences("SendData_Experience",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher_experience = job_experience.edit();


        holder.edt_organization.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                profileTeacher_experience.putString("OrganizationName",(String.valueOf(s)));



            }
        });


        holder.edt_etitlement.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                profileTeacher_experience.putString("JobEntitlement",(String.valueOf(s)));
                profileTeacher_experience.apply();



            }
        });

        holder.edt_from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                profileTeacher_experience.putString("FromTo",(String.valueOf(s)));
                profileTeacher_experience.apply();

            }
        });

        holder.edt_till.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                profileTeacher_experience.putString("Till",String.valueOf(String.valueOf(s)));
                profileTeacher_experience.apply();

            }
        });




    }

    @Override

    public int getItemCount() {

        Log.e("List","List size is "+list.size());
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public EditText edt_etitlement,edt_organization,edt_from,edt_till;
       // public Spinner spinner_class,spinner_subject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


          //  spinner_class = itemView.findViewById(R.id.spinner_class);

            edt_etitlement = itemView.findViewById(R.id.edt_etitlement);
            edt_organization =itemView.findViewById(R.id.edt_organization);
            edt_from = itemView.findViewById(R.id.edt_from);
            edt_till = itemView.findViewById(R.id.edt_till);



        }
    }
}

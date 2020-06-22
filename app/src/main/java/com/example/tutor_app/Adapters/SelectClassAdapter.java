package com.example.tutor_app.Adapters;

import android.content.Context;
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
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

public class SelectClassAdapter extends RecyclerView.Adapter<SelectClassAdapter.ViewHolder> {


    private Context context;
//    String email;
    private List<String> classes;
    private List<String> subjects;
    List<String> spinner1,spinner2;



    public SelectClassAdapter(final Context context, List<String> classItem) {
        this.context = context;
        this.classes = classItem;
        spinner1 = new ArrayList<>();
        spinner1.add("Select Class");
        for(int i = 1; i <= 12; i++)
            spinner1.add("Class " + i);
        spinner1.add("O-Level");
        spinner1.add("A-Level");

        
        spinner2 = new ArrayList<>();
        spinner2.add("Select Subject");
        spinner2.add("Mathematics");
        spinner2.add("English");
        spinner2.add("Urdu");
        spinner2.add("Islamiyat");
        spinner2.add("P. St.");
        spinner2.add("Geography");
        spinner2.add("History");
    }

    @NonNull
    @Override
    public SelectClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.class_recycler, null);
        return new SelectClassAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectClassAdapter.ViewHolder holder, int position) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, spinner1) {
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
        holder.spinner_class.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, spinner2) {
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

        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < spinner2.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(spinner2.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }


        MyAdapter myAdapter = new MyAdapter(this.context, 0,
                listVOs);
         holder.spinner_subject.setAdapter(myAdapter);

        holder.spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.spinner_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public EditText edt_email;
        public Spinner spinner_class,spinner_subject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edt_email = itemView.findViewById(R.id.edt_email);
            spinner_class = itemView.findViewById(R.id.spinner_class);
            spinner_subject = itemView.findViewById(R.id.spinner_subject);


        }
    }
}

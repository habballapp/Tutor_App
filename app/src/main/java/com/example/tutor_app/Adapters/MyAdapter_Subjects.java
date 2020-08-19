package com.example.tutor_app.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutor_app.Dashboard.ui.Student.Profile.StateVO;
import com.example.tutor_app.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter_Subjects extends ArrayAdapter<StateVO> {

    private Context mContext;
    private ArrayList<StateVO> listState;
    private MyAdapter_Subjects myAdapter;
    private boolean isFromView = false;
    private List<String> selectedSubjects = new ArrayList<>();


    public MyAdapter_Subjects(Context context, int resource, List<StateVO> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<StateVO>) objects;
        this.myAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {


        final MyAdapter_Subjects.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.select_subject = (EditText) convertView
                    .findViewById(R.id.select_subject);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (MyAdapter_Subjects.ViewHolder) convertView.getTag();
        }



        holder.mTextView.setText(listState.get(position).getTitle());
//         To check weather checked event fire from getview() or user input
//        isFromView = true;
//        holder.mCheckBox.setChecked(listState.get(position).isSelected());
//        isFromView = false;
//
//        if ((position == 0)) {
//            holder.mCheckBox.setVisibility(View.INVISIBLE);
//        } else {
//            holder.mCheckBox.setVisibility(View.VISIBLE);
//        }
//        holder.mCheckBox.setTag(position);
//        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.i("selectAll", String.valueOf(position));
//                if(position == 1) {
//                    if(isChecked) {
//                        for (int i = 0; i < listState.size(); i++) {
//                            listState.get(i).setSelected(true);
//                            Log.i("selectAll", String.valueOf(listState.get(i).getTitle()));
//                        }
//                    } else {
//                        for (int i = 0; i < listState.size(); i++) {
//                            listState.get(i).setSelected(false);
//                            Log.i("selectAll", String.valueOf(listState.get(i).getTitle()));
//                        }
//                    }
//                } else {
//                    if(isChecked) {
//                        if (!selectedSubjects.contains(listState.get(position).getTitle()))
//                            selectedSubjects.add(listState.get(position).getTitle());
//                    } else {
//                        if (selectedSubjects.contains(listState.get(position).getTitle()))
//                            selectedSubjects.remove(listState.get(position).getTitle());
//                    }
//                }
//                int getPosition = (Integer) buttonView.getTag();
//                Log.i("subjectsSelected", String.valueOf(selectedSubjects));
//
//                Gson gson = new Gson();
//                String json = gson.toJson(selectedSubjects);
//
//                SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
//                        Context.MODE_PRIVATE);
//                final SharedPreferences.Editor profileStudent = personal_profile.edit();
//
//                profileStudent.putString("subjects", String.valueOf(json));
//                profileStudent.apply();
//
//                if (!isFromView) {
//                    listState.get(position).setSelected(isChecked);
//                }
//            }
//
//        });

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;
        if (position==0){
            holder.mCheckBox.setVisibility(View.INVISIBLE);
            holder.select_subject.setVisibility(View.GONE);
            holder.mTextView.setOnClickListener(null);
        }
       else if ((position>11)) {

            holder.mCheckBox.setVisibility(View.INVISIBLE);
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.select_subject.setVisibility(View.VISIBLE);
                    String otherSubject = holder.select_subject.getText().toString();
                    Log.i("subjectOther" ,otherSubject);

                    Toast.makeText(mContext, "aaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                }
            });



        } else {
            holder.select_subject.setVisibility(View.GONE);
            holder.mCheckBox.setVisibility(View.VISIBLE);
            holder.mTextView.setOnClickListener(null);

        }

        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if(isChecked){

                    selectedSubjects.add(listState.get(position).getTitle());
                    Gson gson = new Gson();
                    String json = gson.toJson(selectedSubjects);

                    SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                            Context.MODE_PRIVATE);
                    final SharedPreferences.Editor profileStudent = personal_profile.edit();
                    Log.i("Subjects", String.valueOf(selectedSubjects));
                    profileStudent.putString("subjects", String.valueOf(json));
                    profileStudent.apply();


                }else{
                    selectedSubjects.remove(listState.get(position).getTitle());
                }


                SharedPreferences check_field = getContext().getSharedPreferences("CheckField",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor profileStudent1 = check_field.edit();
                Gson gson1 = new Gson();
                String json1 = gson1.toJson(selectedSubjects);

                profileStudent1.putString("selectedsubjects", String.valueOf(json1));
                profileStudent1.apply();
                Log.i("subjectsSelected", String.valueOf(selectedSubjects));



                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);

                }

            }
        });


        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
        private EditText select_subject;
    }
}

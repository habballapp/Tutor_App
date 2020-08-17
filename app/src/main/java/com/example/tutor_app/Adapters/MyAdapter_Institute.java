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
import android.widget.TextView;

import com.example.tutor_app.Dashboard.ui.Student.Profile.StateVO;
import com.example.tutor_app.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter_Institute extends ArrayAdapter<StateVO> {
    private Context mContext;
    private ArrayList<StateVO> listState;
    private MyAdapter_Institute myAdapter;
    private boolean isFromView = false;
    private int totalChecked = 0;
    private List<String> selectedChilds = new ArrayList<>();
    private Map<String, String> childsMap = new HashMap<>();

    public MyAdapter_Institute(Context context, int resource, List<StateVO> objects, Map<String, String> childsMap) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<StateVO>) objects;
        this.myAdapter = this;
        this.childsMap = childsMap;
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



        final MyAdapter_Institute.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new MyAdapter_Institute.ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (MyAdapter_Institute.ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
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
//                        for (int i = 1; i < listState.size(); i++) {
//                            listState.get(i).setSelected(true);
//                            Log.i("selectAll", String.valueOf(listState.get(i).getTitle()));
//                        }
//                    } else {
//                        for (int i = 1; i < listState.size(); i++) {
//                            listState.get(i).setSelected(false);
//                            Log.i("selectAll", String.valueOf(listState.get(i).getTitle()));
//                        }
//                    }
//                } else {
//                    if(isChecked) {
//                        if (!selectedClasses.contains(listState.get(position).getTitle()))
//                            selectedClasses.add(listState.get(position).getTitle());
//                    } else {
//                        if (selectedClasses.contains(listState.get(position).getTitle()))
//                            selectedClasses.remove(listState.get(position).getTitle());
//                    }
//                }
//                int getPosition = (Integer) buttonView.getTag();
//                Log.i("classessSelected", String.valueOf(selectedClasses));
//
//                Gson gson = new Gson();
//                String json = gson.toJson(selectedClasses);
//
//                final SharedPreferences area_of_interest = getContext().getSharedPreferences("SendData",
//                        Context.MODE_PRIVATE);
//                final SharedPreferences.Editor profileArea_of_interest = area_of_interest.edit();
//
//                profileArea_of_interest.putString("classtoteach",String.valueOf(json));
//                profileArea_of_interest.apply();
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

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
                if(isChecked){
//                    selectedChilds.add(listState.get(position).getTitle());
                    selectedChilds.add(childsMap.get(listState.get(position).getTitle()));
                    Gson gson = new Gson();
                    String json = gson.toJson(selectedChilds);

                    Log.i("childsSelected", String.valueOf(childsMap.get(listState.get(position).getTitle())));

                    SharedPreferences personal_profile = getContext().getSharedPreferences("SearchData",
                            Context.MODE_PRIVATE);
                    final SharedPreferences.Editor profileStudent = personal_profile.edit();

                    profileStudent.putString("InstituteName", String.valueOf(json));
                    profileStudent.apply();


                }else{
//                    selectedChilds.remove(listState.get(position).getTitle());
                    selectedChilds.remove(childsMap.get(listState.get(position).getTitle()));
                }





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
    }
}

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
import android.widget.Toast;

import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<StateVO> {
    private Context mContext;
    private ArrayList<StateVO> listState;
    private MyAdapter myAdapter;
    private boolean isFromView = false;
    private int totalChecked = 0;
    private List<String> spinner_timings = new ArrayList<>();;

    public MyAdapter(Context context, int resource, List<StateVO> objects) {
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

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

//        spinner_timings.add( listState.get(position).getTitle());

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


                if (!isFromView) {
                    if (totalChecked < 3) {
                        listState.get(position).setSelected(isChecked);
                        buttonView.setChecked(isChecked);
                        if (isChecked) {
                            totalChecked++;
                            Log.i("Checked Add", String.valueOf(totalChecked));
                            if (!spinner_timings.contains(listState.get(position).getTitle()))
                                spinner_timings.add(listState.get(position).getTitle());


                        }
                        else{
                            totalChecked--;
                            if (spinner_timings.contains(listState.get(position).getTitle()))
                                spinner_timings.remove(listState.get(position).getTitle());

                        }


                        Log.i("Checked", String.valueOf(totalChecked));
                    }
                    else {
                        buttonView.setChecked(false);
                        if (isChecked) {

                            Toast.makeText(getContext(),
                                    "Limit reached!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            totalChecked--; Log.i("Checked Remove", String.valueOf(totalChecked));
                        }
                    }
                }

                Gson gson = new Gson();
                String json = gson.toJson(spinner_timings);

                SharedPreferences sh_spinner_timings = getContext().getSharedPreferences("SendData",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor ed_spinnerTimings = sh_spinner_timings.edit();

                ed_spinnerTimings.putString("desiredtiming", String.valueOf(json));
                ed_spinnerTimings.apply();
                Log.i("desiredtiming", String.valueOf(json));

                Gson gson1 = new Gson();
                String json1 = gson1.toJson(spinner_timings);

                SharedPreferences sh_spinner_timings1 = getContext().getSharedPreferences("CheckField",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor ed_spinnerTimings1 = sh_spinner_timings1.edit();

                ed_spinnerTimings1.putString("timingSelected", String.valueOf(json1));
                ed_spinnerTimings1.apply();
                Log.i("timingSelected", String.valueOf(json1));




            }


        });

        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}
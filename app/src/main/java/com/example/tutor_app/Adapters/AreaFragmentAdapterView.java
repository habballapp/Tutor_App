package com.example.tutor_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.R;

import org.json.JSONArray;

public class AreaFragmentAdapterView extends RecyclerView.Adapter<AreaFragmentAdapterView.ViewHolder> {

    private Context context;
    private JSONArray area;

    public AreaFragmentAdapterView(Context context, JSONArray area) {
        this.context = context;
        this.area = area;
    }

    @NonNull
    @Override
    public AreaFragmentAdapterView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_areafragment, null);
        return new AreaFragmentAdapterView.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaFragmentAdapterView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return area.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText edt_pref_subject;
        public Spinner spinner_area, spinner_class;
        public TextView spinner_area_textview,spinner_class_textview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            spinner_area = itemView.findViewById(R.id.spinner_area);
            edt_pref_subject = itemView.findViewById(R.id.edt_pref_subject);
            spinner_class = itemView.findViewById(R.id.spinner_class);
            spinner_area_textview = itemView.findViewById(R.id.spinner_area_textview);
            spinner_class_textview = itemView.findViewById(R.id.spinner_class_textview);
        }
    }
}

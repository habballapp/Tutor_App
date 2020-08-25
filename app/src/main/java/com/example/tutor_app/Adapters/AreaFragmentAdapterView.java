package com.example.tutor_app.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.R;

import org.json.JSONArray;
import org.json.JSONException;

public class AreaFragmentAdapterView extends RecyclerView.Adapter<AreaFragmentAdapterView.ViewHolder> {

    private Context context;
    private JSONArray area;

    public AreaFragmentAdapterView(Context context, JSONArray area) {
        this.context = context;
        this.area = area;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public AreaFragmentAdapterView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_areafragment, null);
        return new AreaFragmentAdapterView.ViewHolder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            holder.spinner_class_textview.setText(area.getJSONObject(position).getString("ClassToTeach"));
            holder.spinner_class_textview.setEnabled(false);
            holder.spinner_class_textview.setTextColor(context.getColor(R.color.text_color_selection));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.edt_pref_subject.setText(area.getJSONObject(position).getString("PreferredSubjects"));
                holder.edt_pref_subject.setTextColor(context.getColor(R.color.text_color_selection));
                holder.edt_pref_subject.setEnabled(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return area.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText edt_pref_subject;
        public Spinner spinner_area, spinner_class;
        public TextView spinner_area_textview,spinner_class_textview;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            spinner_area = itemView.findViewById(R.id.spinner_area);
            edt_pref_subject = itemView.findViewById(R.id.edt_pref_subject);
            spinner_class = itemView.findViewById(R.id.spinner_class);
            spinner_area_textview = itemView.findViewById(R.id.spinner_area_textview);
            spinner_class_textview = itemView.findViewById(R.id.spinner_class_textview);
//            spinner_area.setClickable(false);
//            spinner_area.setEnabled(false);
            spinner_class.setClickable(false);
            spinner_class.setEnabled(false);
         //   spinner_area_textview.setVisibility(View.VISIBLE);
          //  spinner_area_textview.setTextColor(context.getColor(R.color.text_color_selection));
            spinner_class_textview.setVisibility(View.VISIBLE);
            spinner_class_textview.setTextColor(context.getColor(R.color.textcolor));

        }
    }
}

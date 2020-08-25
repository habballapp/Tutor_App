package com.example.tutor_app.Adapters;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.R;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SelectClassAdapterView extends RecyclerView.Adapter<com.example.tutor_app.Adapters.SelectClassAdapterView.ViewHolder> {

    private Context context;
    private JSONArray experience;

    public SelectClassAdapterView(Context context, JSONArray experience) {
        this.context = context;
        this.experience = experience;
    }

    @NonNull
    @Override
    public com.example.tutor_app.Adapters.SelectClassAdapterView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.class_recycler, null);
        return new SelectClassAdapterView.ViewHolder(inflate);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull SelectClassAdapterView.ViewHolder holder, int position) {

        try {
            JSONObject obj = experience.getJSONObject(position);
            holder.edt_etitlement.setText(obj.getString("JobEntitlement"));
            holder.edt_etitlement.setTextColor(context.getColor(R.color.text_color_selection));
            holder.edt_organization.setText(obj.getString("OrganizationName"));
            holder.edt_organization.setTextColor(context.getColor(R.color.text_color_selection));
            holder.edt_from.setText(obj.getString("FromTo"));
            holder.edt_from.setTextColor(context.getColor(R.color.text_color_selection));
            holder.edt_till.setText(obj.getString("Till"));
            holder.edt_till.setTextColor(context.getColor(R.color.text_color_selection));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return experience.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText edt_etitlement,edt_organization,edt_from,edt_till;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edt_etitlement = itemView.findViewById(R.id.edt_etitlement);
            edt_organization =itemView.findViewById(R.id.edt_organization);
            edt_from = itemView.findViewById(R.id.edt_from);
            edt_till = itemView.findViewById(R.id.edt_till);
        }
    }
}
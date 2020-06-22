package com.example.tutor_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Model_Classes.View_List;
import com.example.tutor_app.R;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private String name,name_value,contact,contact_value,email,email_value,subjects,subjects_value,
                   fees,fees_value;

    Context context;

    public ViewAdapter(String name, String name_value, String contact, String contact_value, String email, String email_value, String subjects, String subjects_value, String fees, String fees_value, Context context) {
        this.name = name;
        this.name_value = name_value;
        this.contact = contact;
        this.contact_value = contact_value;
        this.email = email;
        this.email_value = email_value;
        this.subjects = subjects;
        this.subjects_value = subjects_value;
        this.fees = fees;
        this.fees_value = fees_value;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_recycler, null);
        return new ViewAdapter.ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.ViewHolder holder, int position) {

//        holder.name.setText(view_list.get(position).getName());
//        holder.name_value.setText(view_list.get(position).getName_value());
//        holder.contact.setText(view_list.get(position).getContact());
//        holder.contact_value.setText(view_list.get(position).getContact_value());
//        holder.email.setText(view_list.get(position).getEmail());
//        holder.email_value.setText(view_list.get(position).getEmail_value());
//        holder.subjects.setText(view_list.get(position).getSubjects());
//        holder.subjects_value.setText(view_list.get(position).getSubjects_value());
//        holder.fees.setText(view_list.get(position).getFees());
//        holder.fees_value.setText(view_list.get(position).getFees_value());

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView name,name_value,contact,contact_value,email,email_value,subjects,subjects_value,
                fees,fees_value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            name_value = (TextView)itemView.findViewById(R.id.name_value);
            contact = (TextView)itemView.findViewById(R.id.tv_contact);
            contact_value = (TextView)itemView.findViewById(R.id.contact_value);
            email = (TextView)itemView.findViewById(R.id.email_value);
            email_value = (TextView)itemView.findViewById(R.id.email_value);
            subjects = (TextView)itemView.findViewById(R.id.tv_subjects);
            subjects_value = (TextView)itemView.findViewById(R.id.subjects_value);
            fees = (TextView)itemView.findViewById(R.id.tv_fees);
            fees_value = (TextView)itemView.findViewById(R.id.fees_value);
        }
    }
}

package com.example.tutor_app.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Model_Classes.View_List;
import com.example.tutor_app.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewAdapterInstitute extends RecyclerView.Adapter<ViewAdapterInstitute.ViewHolder> {


    private List<View_List> view_list;
    Context context;

    public ViewAdapterInstitute(Context context,List<View_List> list) {
        this.view_list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewAdapterInstitute.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_recycler, null);
        return new ViewAdapterInstitute.ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapterInstitute.ViewHolder holder, int position) {

//        holder.name.setText(view_list.get(position).getName());
        holder.name_value.setText(view_list.get(position).getFullName());
//        holder.contact.setText(view_list.get(position).getContact());
        holder.contact_value.setText(view_list.get(position).getPhoneNo1());
//        holder.email.setText(view_list.get(position).getEmail());
        holder.email_value.setText(view_list.get(position).getEmail());
//        holder.subjects.setText(view_list.get(position).getSubjects());
        holder.subjects_value.setText(view_list.get(position).getPreferredSubjects());
        holder.profile_image.setImageBitmap(convertBase64ToBitmap(view_list.get(position).getTutorImage()));
//        holder.fees.setText(view_list.get(position).getFees());
//        holder.fees_value.setText(view_list.get(position).getFees_value());

    }

    private Bitmap convertBase64ToBitmap(String b64) {
        final String pureBase64Encoded = b64.substring(b64.indexOf(",")  + 1);
        byte[] imageAsBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    @Override
    public int getItemCount() {
        return view_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,name_value,contact,contact_value,email,email_value,subjects,subjects_value,
                fees,fees_value;
        public CircleImageView profile_image;

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
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}


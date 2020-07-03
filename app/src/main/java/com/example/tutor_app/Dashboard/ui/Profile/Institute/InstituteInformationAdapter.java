package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Adapters.AreaFragmentAdapter;
import com.example.tutor_app.Model_Classes.AreaFragment_List;
import com.example.tutor_app.Model_Classes.Institute_Information_List;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

class InstituteInformationAdapter extends RecyclerView.Adapter<InstituteInformationAdapter.ViewHolder> {

    private Context context;
    private List<Institute_Information_List> list;


    public InstituteInformationAdapter(Context context, List<Institute_Information_List> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstituteInformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.add_recycler, null);
        return new InstituteInformationAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final InstituteInformationAdapter.ViewHolder holder, int position) {

        SharedPreferences institute_profile = context.getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileInstitute = institute_profile.edit();

        holder.ctype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!(String.valueOf(holder.ctype.getText()) == null))
                {
                    profileInstitute.putString("otherclass",String.valueOf(holder.ctype.getText()));
                    profileInstitute.apply();


                }
                else
                {
                    profileInstitute.putString("otherclass"," ");
                    profileInstitute.apply();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.stype.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!(String.valueOf(holder.stype.getText()) == null))
                {
                    profileInstitute.putString("othersubjects",String.valueOf(holder.stype.getText()));
                    profileInstitute.apply();


                }
                else
                {
                    profileInstitute.putString("othersubjects"," ");
                    profileInstitute.apply();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText ctype,stype;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ctype = itemView.findViewById(R.id.ctype);
            stype = itemView.findViewById(R.id.stype);


        }
    }
}

package com.example.tutor_app.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Model_Classes.InstituteViewTeacher_List;
import com.example.tutor_app.Model_Classes.ViewTeacher_List;
import com.example.tutor_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstituteViewTeacherAdapter extends RecyclerView.Adapter<InstituteViewTeacherAdapter.ViewHolder> {


    private List<InstituteViewTeacher_List> teacher_lists;
    Context context;
    String Url = "http://pci.edusol.co/TeacherPortal/RequestDemo.php";
    String userid;

    public InstituteViewTeacherAdapter(List<InstituteViewTeacher_List> teacher_lists, Context context) {
        this.teacher_lists = teacher_lists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_teacher_view, null);
        return new ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name_value.setText(teacher_lists.get(position).getFullName());
        holder.txt_age_value.setText(teacher_lists.get(position).getAge());
        holder.qualification_value.setText(teacher_lists.get(position).getQualification());
        holder.subjects_value.setText(teacher_lists.get(position).getPreferredSubjects());
        holder.address_value.setText(teacher_lists.get(position).getPresentAddress());
        holder.experience_value.setText(teacher_lists.get(position).getTotalExperience());

        holder.profile_image.setImageBitmap(convertBase64ToBitmap(teacher_lists.get(position).getTutorImage()));


        if (teacher_lists.get(position).getStatus().equals("null")) {
            holder.request_demo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        RequestDemo(holder, teacher_lists.get(position).getId(), teacher_lists.get(position).getType(), teacher_lists.get(position).getJobId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else if (teacher_lists.get(position).getStatus().equals("Scheduled")) {
            holder.request_demo.setEnabled(false);
            holder.request_demo.setText("Demo Scheduled");
            holder.request_demo.setBackground(context.getResources().getDrawable(R.drawable.btn_round_gray));
        } else if (teacher_lists.get(position).getStatus().equals("Pending")) {
            holder.request_demo.setEnabled(false);
            holder.request_demo.setText("\tDemo Requested\t");
            holder.request_demo.setBackground(context.getResources().getDrawable(R.drawable.btn_round_gray));
        }


    }

    private void RequestDemo(final ViewHolder holder, String id, String type, String JobId) throws JSONException {

        JSONObject map = new JSONObject();

        SharedPreferences sharedPreferences1 = context.getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Log.i("ID", userid);


        map.put("Type", type);
        map.put("Id", id);
        map.put("TeacherId", userid);
        map.put("JobId", JobId);

        Log.i("map", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject result) {
                Log.i("Response", String.valueOf(result));
                try {
                    Toast.makeText(context, result.getString("message"), Toast.LENGTH_SHORT).show();
                    holder.request_demo.setEnabled(false);
                    holder.request_demo.setText("\tDemo Requested\t");
                    holder.request_demo.setBackground(context.getResources().getDrawable(R.drawable.btn_round_gray));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }
        };
        Volley.newRequestQueue(context).add(sr);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(sr);


    }

    private Bitmap convertBase64ToBitmap(String b64) {
        final String pureBase64Encoded = b64.substring(b64.indexOf(",") + 1);
        byte[] imageAsBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    @Override
    public int getItemCount() {
        return teacher_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name_value, txt_age_value, qualification_value, experience_value, address_value, subjects_value;
        public CircleImageView profile_image;

        public Button request_demo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_value = (TextView) itemView.findViewById(R.id.name_value);
            txt_age_value = (TextView) itemView.findViewById(R.id.txt_age_value);
            qualification_value = (TextView) itemView.findViewById(R.id.qualification_value);
            experience_value = (TextView) itemView.findViewById(R.id.experience_value);
            subjects_value = (TextView) itemView.findViewById(R.id.subjects_value);
            address_value = (TextView) itemView.findViewById(R.id.address_value);
            profile_image = itemView.findViewById(R.id.profile_image);
            request_demo = itemView.findViewById(R.id.request_demo);
        }
    }
}


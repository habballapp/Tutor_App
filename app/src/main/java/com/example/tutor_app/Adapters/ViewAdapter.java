package com.example.tutor_app.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Model_Classes.ViewStudent_List;
import com.example.tutor_app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {


    private List<ViewStudent_List> ViewStudent_List;
    Context context;
    String locationarea,searchchildren;
    String Url ="http://pci.edusol.co/StudentPortal/RequestDemo.php";
    private FragmentTransaction fragmentTransaction;

    public ViewAdapter(List<ViewStudent_List> ViewStudent_List, Context context) {
        this.ViewStudent_List = ViewStudent_List;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_student_list, null);
        return new ViewAdapter.ViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewAdapter.ViewHolder holder, final int position) {

//        holder.name.setText(ViewStudent_List.get(position).getName());
        holder.name_value.setText(ViewStudent_List.get(position).getName());
        holder.class_value.setText(ViewStudent_List.get(position).getClassName());
        holder.subjects_value.setText(ViewStudent_List.get(position).getSubjects());
       // holder.profile_image.setImageBitmap(convertBase64ToBitmap(ViewStudent_List.get(position).getTutorImage()));
        if(ViewStudent_List.get(position).getStatus().equals("null")) {
            holder.request_demo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        RequestDemo(holder, ViewStudent_List.get(position).getId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
//        } else if (ViewStudent_List.get(position).getStatus().equals("Pending")) {
//            holder.request_demo.setEnabled(false);
//            holder.request_demo.setText("Demo Requested");
//            holder.request_demo.setBackgroundColor(context.getResources().getColor(R.color.green_color));
        } else if (ViewStudent_List.get(position).getStatus().equals("Pending")) {
            holder.request_demo.setEnabled(false);
            holder.request_demo.setText("\tDemo Requested\t");
            holder.request_demo.setBackground(context.getResources().getDrawable(R.drawable.btn_round_gray));
        }
    }

    private void RequestDemo(@NonNull final ViewAdapter.ViewHolder holder, String teacherId) throws JSONException {

        JSONObject map = new JSONObject();
        SharedPreferences sharedPreferences1 = context.getSharedPreferences("SearchData",
                Context.MODE_PRIVATE);

        searchchildren = sharedPreferences1.getString("searchchildren", "");
        JSONArray arr = new JSONArray(searchchildren);
        map.put("Students", arr);
        map.put("TeacherId", teacherId);

        Log.i("map", String.valueOf(map));

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject result) {
               Log.i("Response", String.valueOf(result));
                try {
                    Toast.makeText(context,result.getString("message"),Toast.LENGTH_SHORT).show();
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


//    private Bitmap convertBase64ToBitmap(String b64) {
//        final String pureBase64Encoded = b64.substring(b64.indexOf(",")  + 1);
//        byte[] imageAsBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//    }

    @Override
    public int getItemCount() {
        return ViewStudent_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public  TextView name,name_value,class_value,subjects_value;
        public CircleImageView profile_image;
        public Button request_demo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_value = (TextView)itemView.findViewById(R.id.name_value);
            class_value = (TextView)itemView.findViewById(R.id.class_value);
            subjects_value = (TextView)itemView.findViewById(R.id.subjects_value);
            request_demo = itemView.findViewById(R.id.request_demo);
        }
    }
}

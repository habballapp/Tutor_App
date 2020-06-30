package com.example.tutor_app.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.Model_Classes.AreaFragment_List;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

public class AreaFragmentAdapter extends RecyclerView.Adapter<AreaFragmentAdapter.ViewHolder> {

    private Context context;
    private List<AreaFragment_List> list;
    private List<String> area;
    private String subject;
    List<String> classes = new ArrayList<>();
    

    public AreaFragmentAdapter(Context context, List<AreaFragment_List> list) {

        this.context = context;
        this.list = list;

        classes.add("Select Class");
        for(int i = 1; i <= 8; i++)
        {
            classes.add("Class " + i);
        }
        classes.add("Matric 9");
        classes.add("Matric 10");
        classes.add("Inter year 1");
        classes.add("Inter year 2");
        classes.add("O-Level year 1");
        classes.add("O-Level year 2");
        classes.add("O-Level year 3");
        classes.add("A-Level year 1");
        classes.add("A-Level year 2");

        area = new ArrayList<>();
        area.add("Select Area");
        area.add("Baldia Town");
        area.add(" Bin Qasim Town");
        area.add("Gadap Town");
        area.add("Gulberg Town");
        area.add("Gulshan Town");
        area.add("Baldia Town");
        area.add("Kiamari Town");
        area.add("Korangi Town");
        area.add("Landhi Town");
        area.add("Liaquatabad Town");
        area.add("New Karachi Town");
        area.add("North Nazimabad Town");
        area.add("Orangi Town");
        area.add(" Shah Faisal Town");
        area.add(" SITE Town");
        area.add("Lyari Town");
        area.add(" Malir Town");
    }



    @NonNull
    @Override
    public AreaFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rv_areafragment, null);
        return new AreaFragmentAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final AreaFragmentAdapter.ViewHolder holder, int position) {


        final SharedPreferences area_of_interest = context.getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileArea_of_interest = area_of_interest.edit();


        holder.edt_pref_subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                profileArea_of_interest.putString("prefsubject",(String.valueOf(holder.edt_pref_subject.getText())));
                profileArea_of_interest.apply();
                Log.i("prefsubject",(String.valueOf(holder.edt_pref_subject.getText())));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        final ArrayAdapter<String> spinner_area_adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, area) {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(context.getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);

                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(context.getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);
                return view;
            }
        };
         holder.spinner_area.setAdapter(spinner_area_adapter);
         holder.spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             profileArea_of_interest.putString("prefarea",(String.valueOf(area.get(position))));
             profileArea_of_interest.apply();
             Log.i("AreaSelected", area.get(position) + " - " + position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayList<StateVO> listClasses = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            holder.stateVO = new StateVO();
            holder.stateVO .setTitle(classes.get(i));
            holder.stateVO .setSelected(false);
            listClasses.add( holder.stateVO );
        }

        final MyAdapter3 myAdapter1 = new MyAdapter3(context, android.R.layout.simple_spinner_dropdown_item,listClasses);
        holder.spinner_class.setAdapter(myAdapter1);






    }

    @Override
    public int getItemCount() {

        Log.e("List","List size is "+list.size());
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public EditText edt_pref_subject;
        public Spinner spinner_area, spinner_class;
        private StateVO stateVO;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            spinner_area = itemView.findViewById(R.id.spinner_area);
            edt_pref_subject = itemView.findViewById(R.id.edt_pref_subject);
            spinner_class = itemView.findViewById(R.id.spinner_class);

        }
    }
}

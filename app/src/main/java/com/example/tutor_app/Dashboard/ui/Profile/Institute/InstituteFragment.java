package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutor_app.Dashboard.ui.Qualification.Qualification;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstituteFragment extends Fragment {

    private Spinner spinner_type;
    private RelativeLayout btn_class_next;
    private List<String> itype;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_institutename,edt_phone1,edt_phone2,edt_phone3,edt_email,contact_person;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_institute, container, false);

        itype = new ArrayList<>();
        itype.add("Name of Institute");
        itype.add("School");
        itype.add("College");
        itype.add("University");
        itype.add("Coaching Center");
        itype.add("Other");

        btn_class_next =root.findViewById(R.id.btn_class_next);


        edt_institutename = root.findViewById(R.id.edt_institutename) ;
        edt_phone1 = root.findViewById(R.id.edt_phone1);
        edt_phone2 = root.findViewById(R.id.edt_phone2);
        edt_phone3 = root.findViewById(R.id.edt_phone3);
        edt_email = root.findViewById(R.id.edt_email);
        contact_person = root.findViewById(R.id.contact_person);


        SharedPreferences institute_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileInstitute = institute_profile.edit();

        spinner_type = root.findViewById(R.id.spinner_type);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, itype) {

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);

                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.text_color_selection));
                text.setTextSize((float) 13.6);
                text.setPadding(30, 0, 30, 0);
                return view;
            }
        };

        spinner_type.setAdapter(adapter1);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                profileInstitute.putString("typeofInstitute", String.valueOf(itype.get(position)));
                profileInstitute.apply();
                Log.i("Value:", String.valueOf(String.valueOf(itype.get(position))));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                profileInstitute.putString("nameofInstitute",String.valueOf(edt_institutename.getText()));
                profileInstitute.putString("contactperson",String.valueOf(contact_person.getText()));
                profileInstitute.putString("contactno1",String.valueOf(edt_phone1.getText()));
                profileInstitute.putString("contactno2",String.valueOf(edt_phone2.getText()));
                profileInstitute.putString("contactno3",String.valueOf(edt_phone3.getText()));
                profileInstitute.putString("email",String.valueOf(edt_email.getText()));
                profileInstitute.apply();

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteClassFragment());
                fragmentTransaction.commit();


            }
        });

        return root;
    }
}

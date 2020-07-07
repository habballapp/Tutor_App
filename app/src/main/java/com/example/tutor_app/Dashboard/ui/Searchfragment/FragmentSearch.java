package com.example.tutor_app.Dashboard.ui.Searchfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutor_app.Adapters.MyAdapter_Child;
import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.Dashboard.ui.View.ViewFragment;
import com.example.tutor_app.R;


import java.util.ArrayList;
import java.util.List;


public class FragmentSearch extends Fragment {
    
    private RelativeLayout rl_next;
    private List<String> area,childs;
    private FragmentTransaction fragmentTransaction;
    private Spinner spinner_location,add_child;
    String Url = "http://pci.edusol.co/StudentPortal/searchtutorApi.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        area = new ArrayList<>();
        area.add("Select Area");
        area.add("Baldia Town");
        area.add("Bin Qasim Town");
        area.add("Gadap Town");
        area.add("Gulberg Town");
        area.add("Gulshan Town");
        area.add("Jamshed Town");
        area.add("Kiamari Town");
        area.add("Korangi Town");
        area.add("Landhi Town");
        area.add("Liaquatabad Town");
        area.add("New Karachi Town");
        area.add("North Nazimabad Town");
        area.add("Nazimabad Town");
        area.add("Orangi Town");
        area.add("Shah Faisal Town");
        area.add("SITE Town");
        area.add("Saddar Town");
        area.add("Lyari Town");
        area.add("Malir Town");

        childs = new ArrayList<>();
        childs.add("Select Child");
        childs.add("child1");
        childs.add("child1");
        childs.add("child1");



        spinner_location = (Spinner) root.findViewById(R.id.spinner_location);
        add_child = (Spinner) root.findViewById(R.id.add_child);

        rl_next = root.findViewById(R.id.rl_next);

        rl_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new ViewFragment());
                fragmentTransaction.commit();
            }
        });


        ArrayAdapter<String> adapter_location = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,area) {
            @Override
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

        spinner_location.setAdapter(adapter_location);
        spinner_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<StateVO> listVOs = new ArrayList<>();

        for (int i = 0; i < childs.size(); i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(childs.get(i));
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }


        MyAdapter_Child myAdapter = new MyAdapter_Child(getContext(), android.R.layout.simple_spinner_dropdown_item, listVOs);
        add_child.setAdapter(myAdapter);




//        ArrayAdapter<String> adapter_subject = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,childs) {
//            @Override
//            public View getDropDownView(int position, View convertView, ViewGroup parent) {
//                // TODO Auto-generated method stub
//                View view = super.getView(position, convertView, parent);
//                TextView text = (TextView) view.findViewById(android.R.id.text1);
//                text.setTextColor(getResources().getColor(R.color.text_color_selection));
//                text.setTextSize((float) 13.6);
//                text.setPadding(30, 0, 30, 0);
//
//                return view;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                // TODO Auto-generated method stub
//                View view = super.getView(position, convertView, parent);
//                TextView text = (TextView) view.findViewById(android.R.id.text1);
//                text.setTextColor(getResources().getColor(R.color.text_color_selection));
//                text.setTextSize((float) 13.6);
//                text.setPadding(30, 0, 30, 0);
//                return view;
//            }
//        };
//
//        add_child.setAdapter(adapter_subject);
//        add_child.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        return root;




    }
}

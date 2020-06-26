package com.example.tutor_app.Dashboard.ui.Profile.Institute;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.tutor_app.Adapters.MyAdapter;
import com.example.tutor_app.Adapters.MyAdapter2;
import com.example.tutor_app.Adapters.MyAdapter_Subjects;
import com.example.tutor_app.Dashboard.ui.Profile.Student.StateVO;
import com.example.tutor_app.Dashboard.ui.Qualification.ExpandOrCollapse;
import com.example.tutor_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstituteClassFragment extends Fragment {

    private Spinner spinner_class,spinner_subject;
    List<String> classes = new ArrayList<>();
    List<String> subjects = new ArrayList<>();
    private StateVO stateVO;
    private RelativeLayout btn_class_add,btn_class_next;
    private EditText ctype,stype;
    private LinearLayout add_more;
    private boolean isVisible = false;
    private ExpandOrCollapse mAnimationManager;
    private FragmentTransaction fragmentTransaction;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_institute_class, container, false);

        spinner_class = root.findViewById(R.id.spinner_class);
        spinner_subject = root.findViewById(R.id.spinner_subject);
        ctype = root.findViewById(R.id.ctype);
        stype = root.findViewById(R.id.stype);
        btn_class_add = root.findViewById(R.id.btn_class_add);
        add_more = root.findViewById(R.id.add_more);
        btn_class_next = root.findViewById(R.id.btn_class_next);

        classes.add("Select Class");
        classes.add("Select All");
        classes.add("O-Level");
        classes.add("A-Level");
        for(int i = 1; i <= 12; i++)
        {
            classes.add("Class " + i);
        }

        ArrayList<StateVO> listClasses = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            stateVO = new StateVO();
            stateVO.setTitle(classes.get(i));
            stateVO.setSelected(false);
            listClasses.add(stateVO);
        }

        subjects.add("Select Subject");
        subjects.add("Select All");
        subjects.add("Mathematics");
        subjects.add("English");
        subjects.add("Urdu");
        subjects.add("Islamiyat");
        subjects.add("P. St.");
        subjects.add("Geography");
        subjects.add("History");

        final MyAdapter2 Adapter_Classes = new MyAdapter2(getContext(), android.R.layout.simple_spinner_dropdown_item,listClasses);
        spinner_class.setAdapter(Adapter_Classes);

        ArrayList<StateVO> listSubjects = new ArrayList<>();

        for (int i = 0; i < subjects.size(); i++) {
            stateVO = new StateVO();
            stateVO.setTitle(subjects.get(i));
            stateVO.setSelected(false);
            listSubjects.add(stateVO);
        }

         MyAdapter_Subjects Adapter_Subjects = new  MyAdapter_Subjects(getContext(), android.R.layout.simple_spinner_dropdown_item,listSubjects);
        spinner_subject.setAdapter(Adapter_Subjects);

        btn_class_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                add_more.setVisibility(View.VISIBLE);
            }

        });

        btn_class_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isVisible) {
                    mAnimationManager.expand(add_more, 0);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(add_more, 0);
                    isVisible = false;
                }
            }
        });

        btn_class_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteAddressFragment());
                fragmentTransaction.commit();

            }
        });







        return root;
    }
}

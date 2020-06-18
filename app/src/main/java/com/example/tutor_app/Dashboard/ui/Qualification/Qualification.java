package com.example.tutor_app.Dashboard.ui.Qualification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tutor_app.Dashboard.ui.AreaofInterest.AreaFragment;
import com.example.tutor_app.Dashboard.ui.JobExperience.JobExperienceFragment;
import com.example.tutor_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Qualification extends Fragment {

    private boolean isVisible = false;
    private RelativeLayout mRelativeZaplon,mRelativeZaplon1,mRelativeZaplon2,mRelativeZaplon3,mRelativeZaplon4,mRelativeZaplon5;
    private RelativeLayout mRelativeToSlide,mRelativeToSlide1,mRelativeToSlide2,mRelativeToSlide3,mRelativeToSlide4,mRelativeToSlide5;
    private ExpandOrCollapse mAnimationManager;
    private RelativeLayout btn_qualification_next;
    private FragmentTransaction fragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_section2, container, false);

        mAnimationManager = new ExpandOrCollapse();


        mRelativeZaplon = (RelativeLayout) root.findViewById(R.id.relativeZaplon);
        mRelativeZaplon1 = (RelativeLayout) root.findViewById(R.id.relativeZaplon1);
        mRelativeZaplon2 = (RelativeLayout) root.findViewById(R.id.relativeZaplon2);
        mRelativeZaplon3 = (RelativeLayout) root.findViewById(R.id.relativeZaplon3);
        mRelativeZaplon4 = (RelativeLayout) root.findViewById(R.id.relativeZaplon4);


        mRelativeToSlide = (RelativeLayout) root.findViewById(R.id.relativevToSlide);
        mRelativeToSlide1 = (RelativeLayout) root.findViewById(R.id.relativevToSlide1);
        mRelativeToSlide2 = (RelativeLayout) root.findViewById(R.id.relativevToSlide2);
        mRelativeToSlide3 = (RelativeLayout) root.findViewById(R.id.relativevToSlide3);
        mRelativeToSlide4 = (RelativeLayout) root.findViewById(R.id.relativevToSlide4);

        btn_qualification_next = root.findViewById(R.id.btn_qualification_next);

        btn_qualification_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new JobExperienceFragment());
                fragmentTransaction.commit();
            }
        });


        mRelativeZaplon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide, 1000);
                    isVisible = false;
                }
            }
        });

        mRelativeZaplon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide1, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide1, 1000);
                    isVisible = false;
                }
            }
        });
        mRelativeZaplon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide2, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide2, 1000);
                    isVisible = false;
                }
            }
        });

        mRelativeZaplon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide3, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide3, 1000);
                    isVisible = false;
                }
            }
        });

        mRelativeZaplon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisible) {
                    mAnimationManager.expand(mRelativeToSlide4, 1000);
                    isVisible = true;
                } else if (isVisible){
                    mAnimationManager.expand(mRelativeToSlide4, 1000);
                    isVisible = false;
                }
            }
        });

        return root;
    }
}

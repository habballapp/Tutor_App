package com.example.tutor_app.Dashboard.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.tutor_app.Dashboard.ui.Student.SearchFragment.FragmentSearch;
import com.example.tutor_app.Dashboard.ui.Institude.SearchFragment.InstituteSearchFragment;
import com.example.tutor_app.Dashboard.ui.Teacher.SearchFragment.TeacherSearchFragment;
import com.example.tutor_app.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RelativeLayout btn_search;
    private FragmentTransaction fragmentTransaction;
    String userrole;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btn_search = root.findViewById(R.id.btn_search);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userrole = sharedPreferences1.getString("userrole", "");

        Log.i("UserRole",userrole);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                switch (userrole) {
                    case "Student":

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new FragmentSearch());
                        fragmentTransaction.commit();
                        ;
                        break;
                    case "Teacher":

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new TeacherSearchFragment());
                        fragmentTransaction.commit();

                        break;
                    case "Institute":

                        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteSearchFragment());
                        fragmentTransaction.commit();
                        break;
                }



            }
        });

        return root;
    }
}

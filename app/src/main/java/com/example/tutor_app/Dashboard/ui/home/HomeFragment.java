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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.tutor_app.Dashboard.ui.Profile.Institute.InstituteFragment;
import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
import com.example.tutor_app.Dashboard.ui.Searchfragment.InstituteSearchFragment;
import com.example.tutor_app.Dashboard.ui.Searchfragment.TeacherSearchFragment;
import com.example.tutor_app.R;

import java.util.Objects;

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

                        fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new FragmentSearch()).addToBackStack("null");
                        fragmentTransaction.commit();

                        break;
                    case "Teacher":

                        fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new TeacherSearchFragment()).addToBackStack("null");
                        fragmentTransaction.commit();

                        break;
                    case "Institute":

                        fragmentTransaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new InstituteSearchFragment()).addToBackStack("null");
                        fragmentTransaction.commit();
                        break;
                }



            }
        });

        return root;
    }
}

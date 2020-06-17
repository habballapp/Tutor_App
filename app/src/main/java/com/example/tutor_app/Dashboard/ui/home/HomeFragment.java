package com.example.tutor_app.Dashboard.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
import com.example.tutor_app.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RelativeLayout btn_search;
    private FragmentTransaction fragmentTransaction;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btn_search = root.findViewById(R.id.btn_search);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new FragmentSearch());
                fragmentTransaction.commit();

            }
        });

        return root;
    }
}

package com.example.tutor_app.Dashboard.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tutor_app.Dashboard.ui.Profile.Institute.InstituteFragment;
import com.example.tutor_app.Dashboard.ui.Profile.Student.ProfileStudent;
import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
import com.example.tutor_app.Dashboard.ui.Searchfragment.InstituteSearchFragment;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.R;
import com.example.tutor_app.Signin.SignIn;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techatmosphere.expandablenavigation.model.ChildModel;
import com.techatmosphere.expandablenavigation.model.HeaderModel;
import com.techatmosphere.expandablenavigation.view.ExpandableNavigationListView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard_Drawer_Institute extends AppCompatActivity {

    private ExpandableNavigationListView navigationExpandableListView;
    private AppBarConfiguration mAppBarConfiguration;
    private RelativeLayout btn_search;
    private FragmentTransaction fragmentTransaction;
    private TextView footer_item_1;
    String userid;
    private List<String> institute = new ArrayList<>();
    private Map<String, String> intituteMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();

        SharedPreferences sharedPreferences1 = getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        Type typeMap = new TypeToken<Map<String, String>>() {
        }.getType();
        institute = gson.fromJson(sharedPreferences1.getString("institute", ""), type);
        intituteMap = gson.fromJson(sharedPreferences1.getString("instituteMap", ""), typeMap);
        Log.i("Id", userid);
        Log.i("Id", String.valueOf(intituteMap));


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
//        drawer.setScrimColor(Color.parseColor("#33000000"));
        drawer.setScrimColor(getResources().getColor(android.R.color.transparent));

        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        drawer.setDrawerListener(toggle);

        navigationExpandableListView = findViewById(R.id.expandable_navigation);
//        footer_item_1 = findViewById(R.id.footer_item_1);
//        footer_item_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                drawer.closeDrawer(GravityCompat.START);
//
//            }
//        });
        navigationExpandableListView.init(this);
        navigationExpandableListView.addHeaderModel(new HeaderModel("Home"));

        HeaderModel headerModel = new HeaderModel("View Job");
        for (int i = 0; i < institute.size(); i++)
            headerModel.addChildModel(new ChildModel(institute.get(i)));
        navigationExpandableListView.addHeaderModel(headerModel);

        Log.i("Institute11", String.valueOf(institute));
        if(institute.size() <= 0){
            navigationExpandableListView.addHeaderModel(new HeaderModel("Add Profile")
            );
        }else{
            navigationExpandableListView.addHeaderModel(new HeaderModel("Edit Profile")
            );
        }

        navigationExpandableListView.addHeaderModel(new HeaderModel("Add Job")
        );

        navigationExpandableListView.addHeaderModel(new HeaderModel("Search"));
        navigationExpandableListView.addHeaderModel(new HeaderModel("Logout"));

        navigationExpandableListView.build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);

                        if (id == 0) {
                            Log.i("Dashboard", "Dashboard Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {

                        } else if (id == 2) {

                            SharedPreferences institute_profile1 = getSharedPreferences("ViewProfile",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileInstitute1 = institute_profile1.edit();
                            profileInstitute1.putString("UserId", "");
                            profileInstitute1.putString("ViewProfileData", "");
                            profileInstitute1.apply();

                            SharedPreferences personal_profile = getSharedPreferences("UserId",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileStudent = personal_profile.edit();
                            profileStudent.putString("UserId", "");
                            profileStudent.apply();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new InstituteFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        } else if (id == 3) {

                            SharedPreferences institute_profile1 = getSharedPreferences("ViewProfile",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileInstitute1 = institute_profile1.edit();
                            profileInstitute1.putString("UserId", "");
                            profileInstitute1.putString("ViewProfileData", "");
                            profileInstitute1.apply();

                            SharedPreferences personal_profile = getSharedPreferences("UserId",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileStudent = personal_profile.edit();
                            profileStudent.putString("UserId", userid);
                            profileStudent.apply();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new InstituteFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);


                        } else if (id == 4) {
                            Log.i("Make Payment", "Make Payment Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new InstituteSearchFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        } else if (id == 5) {

                            Intent intent = new Intent(Dashboard_Drawer_Institute.this, SignIn.class);
                            startActivity(intent);
                        }


                        return false;
                    }
                }).
                addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition, childPosition);
                        if (groupPosition == 1) {
                            String selectedInstitute = institute.get(childPosition).replaceAll("\t\t\t", "");
                            String selectedInstituteId = intituteMap.get(selectedInstitute);

                            SharedPreferences personal_profile = getSharedPreferences("ViewProfile",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileInstitute = personal_profile.edit();
                            profileInstitute.putString("UserId", selectedInstituteId);
                            profileInstitute.apply();

                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new InstituteFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                            // drawer.closeDrawer(GravityCompat.START);
                        }

                        drawer.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });


        navigationExpandableListView.setSelected(0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard__drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}

    


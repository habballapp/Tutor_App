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
import android.widget.Toast;

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
import com.example.tutor_app.Dashboard.ui.Profile.Student.ProfileStudent;
import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
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
import java.util.List;


public class Dashboard_Drawer_Student extends AppCompatActivity {

    private ExpandableNavigationListView navigationExpandableListView;
    private AppBarConfiguration mAppBarConfiguration;
    private RelativeLayout btn_search;
    private FragmentTransaction fragmentTransaction;
    private TextView footer_item_1;
    String userid;
    private List<String> childs = new ArrayList<>();

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
        Type type = new TypeToken<List<String>>(){}.getType();
        childs = gson.fromJson(sharedPreferences1.getString("children", ""), type);
        Log.i("Id",userid);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
//        drawer.setScrimColor(Color.parseColor("#33000000"));
        drawer.setScrimColor(getResources().getColor(android.R.color.transparent));

        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        drawer.setDrawerListener(toggle);

        navigationExpandableListView = findViewById(R.id.expandable_navigation);

        navigationExpandableListView.init(this);
        navigationExpandableListView.addHeaderModel(new HeaderModel("Home"));
        HeaderModel headerModel = new HeaderModel("View Profile");
        for (int i = 0; i < childs.size(); i++)
            headerModel.addChildModel(new ChildModel(childs.get(i)));
        navigationExpandableListView.addHeaderModel(headerModel);



        Log.i("Child11", String.valueOf(childs));

        navigationExpandableListView.addHeaderModel(new HeaderModel(" Add Profile")
        );
        navigationExpandableListView.addHeaderModel(new HeaderModel("Add Child")
        );
        navigationExpandableListView.addHeaderModel(new HeaderModel("Search"));
        navigationExpandableListView.addHeaderModel(new HeaderModel("Logout"));
//        navigationExpandableListView.addHeaderModel(
//                new HeaderModel("Payment")
////                                  .addChildModel(new ChildModel("\tPayments Summary"))
//                        .addChildModel(new ChildModel("\tConsolidate Payments"))
//                        .addChildModel(new ChildModel("\tMake Payment"))
//                        .addChildModel(new ChildModel("\tPayment Ledger"))
//                        .addChildModel(new ChildModel("\tProof of Payments"))
//
//        );
//        navigationExpandableListView.addHeaderModel(new HeaderModel("Profile"));
//        navigationExpandableListView.addHeaderModel(new HeaderModel("Support"));
//        navigationExpandableListView.addHeaderModel(new HeaderModel("Logout"));
//                .addHeaderModel(new HeaderModel("\n\n\n\nTerms And Conditions"))
        navigationExpandableListView.build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);


                        if (id == 0) {
                            Toast.makeText(Dashboard_Drawer_Student.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            Log.i("Dashboard", "Dashboard Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        else if (id == 1) {
//                            Toast.makeText(Dashboard_Drawer_Student.this, "selected"+id, Toast.LENGTH_SHORT).show();
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.nav_host_fragment, new ProfileStudent());
////                          fragmentTransaction.replace(R.id.container, new My_Network_Fragment()).addToBackStack("tag");
//                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);
                        }
                        else if (id == 2) {

                            SharedPreferences personal_profile = getSharedPreferences("UserId",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileStudent = personal_profile.edit();
                            profileStudent.putString("UserId","");
                            profileStudent.apply();

                            Toast.makeText(Dashboard_Drawer_Student.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new ProfileStudent());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        } else if (id == 3) {


                            SharedPreferences personal_profile = getSharedPreferences("UserId",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileStudent = personal_profile.edit();
                            profileStudent.putString("UserId",userid);
                            profileStudent.apply();

                            Toast.makeText(Dashboard_Drawer_Student.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new ProfileStudent());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        }
                        else if (id == 4) {
                            Toast.makeText(Dashboard_Drawer_Student.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            Log.i("Make Payment", "Make Payment Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new FragmentSearch());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        }
                        else if (id == 5) {

                            Intent intent = new Intent(Dashboard_Drawer_Student.this, SignIn.class);
                            startActivity(intent);

                        }
//                        else if (id == 4) {
//                            Log.i("Profile", "Profile Activity");
////                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                            fragmentTransaction.replace(R.id.main_container_ret, new Profile_Tabs()).addToBackStack("tag");
////                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);
//                        } else if (id == 5) {
////                            Log.i("Support", "Support Activity");
////                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
////                            fragmentTransaction.replace(R.id.main_container_ret, new SupportFragment()).addToBackStack("tag");
////                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);
////                        } else if (NavList.contains("Logout") && NavList.indexOf("Logout") == id) {
//
////                            Intent dashboard = new Intent(RetailorDashboard.this, RetailerLogin.class);
////                            startActivity(dashboard);
//                            Intent intent = new Intent(Dashboard_Drawer.this, SignIn.class);
//                            startActivity(intent);
//                            finish();
//                            drawer.closeDrawer(GravityCompat.START);
//                        }

                        return false;
                    }
                });
//                .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//                    @Override
//                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                        navigationExpandableListView.setSelected(groupPosition, childPosition);
//                        if (groupPosition == 3 && childPosition == 0) {
//                            Log.i("Payments Summary", "Child");
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.main_container_ret, new Payment_Summary()).addToBackStack("tag");
//                            ;
//                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);
//                        } else if (groupPosition == 3 && childPosition == 1) {
//                            Log.i("Payment Request", "Child");
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.main_container_ret, new CreatePaymentRequestFragment()).addToBackStack(null);
//                            ;
//                            fragmentTransaction.commit();
//                        } else if (groupPosition == 2 && childPosition == 0) {
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.main_container_ret, new PlaceOrderFragment()).addToBackStack("tag");
//                            ;
//                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);
//                        }
//                        drawer.closeDrawer(GravityCompat.START);
//                        return false;
//                    }
//                });

        navigationExpandableListView.setSelected(0);



//
//
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_profile, R.id.nav_search,R.id.nav_profile_student)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
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


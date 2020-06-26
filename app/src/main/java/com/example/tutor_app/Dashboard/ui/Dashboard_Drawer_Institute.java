package com.example.tutor_app.Dashboard.ui;

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

import com.example.tutor_app.Dashboard.ui.Profile.Institute.InstituteFragment;
import com.example.tutor_app.Dashboard.ui.Profile.Student.ProfileStudent;
import com.example.tutor_app.Dashboard.ui.Searchfragment.FragmentSearch;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.R;
import com.google.android.material.navigation.NavigationView;
import com.techatmosphere.expandablenavigation.model.ChildModel;
import com.techatmosphere.expandablenavigation.model.HeaderModel;
import com.techatmosphere.expandablenavigation.view.ExpandableNavigationListView;

public class Dashboard_Drawer_Institute extends AppCompatActivity {

    private ExpandableNavigationListView navigationExpandableListView;
    private AppBarConfiguration mAppBarConfiguration;
    private RelativeLayout btn_search;
    private FragmentTransaction fragmentTransaction;
    private TextView footer_item_1;

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
        navigationExpandableListView.addHeaderModel(new HeaderModel("Edit Profile")
        );
        navigationExpandableListView.addHeaderModel(new HeaderModel("View Profile")
                .addChildModel(new ChildModel("\tProfile1"))
                .addChildModel(new ChildModel("\tProfile1"))
        );
        navigationExpandableListView.addHeaderModel(new HeaderModel(" Add Profile")
        );

        navigationExpandableListView.addHeaderModel(new HeaderModel("Search"));
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
                            Toast.makeText(Dashboard_Drawer_Institute.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            Log.i("Dashboard", "Dashboard Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        else if (id == 1) {
                            Toast.makeText(Dashboard_Drawer_Institute.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, new ProfileStudent());
//                          fragmentTransaction.replace(R.id.container, new My_Network_Fragment()).addToBackStack("tag");
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        else if (id == 2) {

//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.main_container_ret, new PlaceOrderFragment());
//                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.replace(R.id.conainer, new Retailer_Place_Order()).addToBackStack("tag");
//                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);

                        } else if (id == 3) {
                            Toast.makeText(Dashboard_Drawer_Institute.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new InstituteFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        }
                        else if (id == 4) {
                            Toast.makeText(Dashboard_Drawer_Institute.this, "selected"+id, Toast.LENGTH_SHORT).show();
                            Log.i("Make Payment", "Make Payment Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new FragmentSearch());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

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

    


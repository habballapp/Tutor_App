package com.example.tutor_app.Dashboard.ui.Teacher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms.ProfileTeacher;
import com.example.tutor_app.Dashboard.ui.Teacher.SearchFragment.TeacherSearchFragment;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.R;
import com.example.tutor_app.Session;
import com.example.tutor_app.Signin.SignIn;
import com.google.android.material.navigation.NavigationView;
import com.techatmosphere.expandablenavigation.model.ChildModel;
import com.techatmosphere.expandablenavigation.model.HeaderModel;
import com.techatmosphere.expandablenavigation.view.ExpandableNavigationListView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard_Drawer_Teacher extends AppCompatActivity {

    private ExpandableNavigationListView navigationExpandableListView;
    private AppBarConfiguration mAppBarConfiguration;
    private RelativeLayout btn_search;
    private FragmentTransaction fragmentTransaction;
    String userid;
    private Session session;
    private TextView tool_bar_heading;
    private boolean doubleBackToExitPressedOnce = false;
    DrawerLayout drawer;
    private List<String> dashboard_titles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tool_bar_heading = toolbar.findViewById(R.id.tool_bar_heading);

        setSupportActionBar(toolbar);
        session = new Session(this);


        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();
        tool_bar_heading.setText("Dashboard");
        SharedPreferences sharedPreferences1 = getSharedPreferences("LoginData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("userid", "");
        Log.i("ID", userid);

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
        dashboard_titles.add("Home");
        navigationExpandableListView.addHeaderModel(new HeaderModel("Search"));
        dashboard_titles.add("Search");
        navigationExpandableListView.addHeaderModel(new HeaderModel("Add Profile")
        );
        dashboard_titles.add("Add Profile");
        navigationExpandableListView.addHeaderModel(new HeaderModel("View Profile")
        );
        dashboard_titles.add("View Profile");

        navigationExpandableListView.addHeaderModel(new HeaderModel("Logout"));
        navigationExpandableListView.build()
                .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        navigationExpandableListView.setSelected(groupPosition);

                        if (id == 0) {
                            tool_bar_heading.setText("Dashboard");
                            Log.i("Dashboard", "Dashboard Activity");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, new HomeFragment());
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);
                        } else if (id == 1) {

                            tool_bar_heading.setText("Search");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.add(R.id.nav_host_fragment, new TeacherSearchFragment()).addToBackStack("tag");
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);

                        } else if (id == 2) {
                          //  if (dashboard_titles.get((int) id).equals("Add Profile")) {
                                tool_bar_heading.setText("Profile");
                                SharedPreferences personal_profile = getSharedPreferences("ViewData",
                                        Context.MODE_PRIVATE);
                                final SharedPreferences.Editor profileTeacher = personal_profile.edit();
                                profileTeacher.putString("UserId", "");
                                profileTeacher.apply();
                                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.add(R.id.nav_host_fragment, new ProfileTeacher()).addToBackStack("tag");
                                fragmentTransaction.commit();
                                drawer.closeDrawer(GravityCompat.START);
                        //    }
                          //  else if (dashboard_titles.get((int) id).equals("View Profile")) {


                        }
                        else if (id==3) {
                            tool_bar_heading.setText("View Profile");
                            SharedPreferences sharedPreferences1 = getSharedPreferences("LoginData",
                                    Context.MODE_PRIVATE);
                            userid = sharedPreferences1.getString("userid", "");
                            Log.i("ID", userid);

                            SharedPreferences personal_profile = getSharedPreferences("ViewData",
                                    Context.MODE_PRIVATE);
                            final SharedPreferences.Editor profileTeacher = personal_profile.edit();
                            profileTeacher.putString("UserId", userid);
                            profileTeacher.apply();
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            // fragmentTransaction.replace(R.id.container, new Fragment()).addToBackStack("tag1");
                            fragmentTransaction.add(R.id.nav_host_fragment, new ProfileTeacher()).addToBackStack("tag");
                            fragmentTransaction.commit();
                            drawer.closeDrawer(GravityCompat.START);
                        }

                        //else if (dashboard_titles.get((int) id).equals("View Profile")) {
//                            tool_bar_heading.setText("Profile");
//                            SharedPreferences personal_profile = getSharedPreferences("ViewData",
//                                    Context.MODE_PRIVATE);
//                            final SharedPreferences.Editor profileTeacher = personal_profile.edit();
//                            profileTeacher.putString("UserId", "");
//                            profileTeacher.apply();
//                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            fragmentTransaction.add(R.id.nav_host_fragment, new ProfileTeacher()).addToBackStack("tag");
//                            fragmentTransaction.commit();
//                            drawer.closeDrawer(GravityCompat.START);


                        else if (id == 4) {
                            logoutUser();
                        }


                        return false;
                    }
                });
//

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


    @Override
    public void onBackPressed() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//            super.onBackPressed();
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
        } else {
//            FragmentManager fm = getSupportFragmentManager();
//            if (fm.getBackStackEntryCount() == 0) {
            if (doubleBackToExitPressedOnce) {
//                    super.onBackPressed();
//                    finishAffinity();
                logoutUser();

                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 1500);
//            } else {
////            super.onBackPressed();
//                fm.popBackStack();
//            }
        }
    }

    private void logoutUser() {


        final AlertDialog alertDialog = new AlertDialog.Builder(Dashboard_Drawer_Teacher.this).create();
        LayoutInflater inflater = LayoutInflater.from(Dashboard_Drawer_Teacher.this);
        View view_popup = inflater.inflate(R.layout.discard_changes, null);
        TextView tv_discard = view_popup.findViewById(R.id.tv_discard);
        tv_discard.setText("Logout");
        TextView tv_discard_txt = view_popup.findViewById(R.id.tv_discard_txt);
        tv_discard_txt.setText("Are you sure, you want to logout?");
        alertDialog.setView(view_popup);
        alertDialog.getWindow().setGravity(Gravity.TOP | Gravity.START | Gravity.END);
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        layoutParams.y = 200;
        layoutParams.x = -70;// top margin
        alertDialog.getWindow().setAttributes(layoutParams);
        Button btn_discard = (Button) view_popup.findViewById(R.id.btn_discard);
        btn_discard.setText("Logout");
        btn_discard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent login = new Intent(Dashboard_Drawer_Teacher.this, SignIn.class);
                session.remove();
                startActivity(login);
                finish();
            }
        });

        ImageButton img_email = (ImageButton) view_popup.findViewById(R.id.btn_close);
        img_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}

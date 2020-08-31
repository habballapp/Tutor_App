package com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tutor_app.Adapters.MultipleFilesAdapter;
import com.example.tutor_app.Dashboard.ui.Teacher.TeacherForms.Qualification;
import com.example.tutor_app.Dashboard.ui.home.HomeFragment;
import com.example.tutor_app.Loader.Loader;
import com.example.tutor_app.R;
import com.google.gson.Gson;
import com.jaiselrahman.filepicker.activity.FilePickerActivity;
import com.jaiselrahman.filepicker.config.Configurations;
import com.jaiselrahman.filepicker.model.MediaFile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileTeacher extends Fragment implements DatePickerDialog.OnDateSetListener {

    private RelativeLayout btn_profile_next, btn_profile_upload, back, btn_upload_files;
    private FragmentTransaction fragmentTransaction;
    private EditText edt_fullname, edt_fname, edt_mtongue, edt_cnic, edt_present_address,
            edt_permanent_address, edt_nationality, edt_religion, edt_phone1, edt_phone2,
            edt_email, edt_conveyance_txt, edt_age, experience_year;
    private String selectedFileType, imageName, fileName;
    private String imageBitmapBase64 = "";
    private TextView FileName;
    private String FileBitmapBase64 = "";
    private static final int REQUEST_CAMERA = 2;
    private static final int SELECT_FILE = 1;
    private List<String> gender, catogery;
    String Url = "http://pci.edusol.co/TeacherPortal/view_profile_api.php";
    String userid;
    private Loader loader;

    private Spinner teacher_profession, spinner_conveyance, spinner1, spinner_catogery;
    //   private static final String[] paths = {"Are you a Teacher by Profession?", "Yes", "No"};
    //    private static final String[] paths1 = {"Do you have conveyance?", "Yes", "No"};
    private List<String> paths, paths1;
    private String Filter_selected = "", Filter_selected1 = "";
    private int year1, year2, month1, month2, date1, date2;
    private String fromDate, toDate;
    private ImageButton first_date_btn, second_date_btn;
    private TextView first_date, second_date;
    private String dateType = "";
    private TextView spinner_category_textview, spinner_conveyance_textview, spinner_gender_textview, teacher_profession_textview;
    private ImageView image_view_uploaded_image;
    final List<EditText> allFields = new ArrayList<EditText>();
    //multiple files
    private final static int FILE_REQUEST_CODE = 1;
    private MultipleFilesAdapter fileListAdapter;
    private ArrayList<MediaFile> mediaFiles = new ArrayList<>();
    private TextView tool_bar_heading;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar();
        tool_bar_heading = toolbar.findViewById(R.id.tool_bar_heading);
        //multiple files setAdapter
        //  RecyclerView recyclerView = root.findViewById(R.id.file_list);
        btn_upload_files = root.findViewById(R.id.btn_upload_files);
        // fileListAdapter = new MultipleFilesAdapter(mediaFiles);
        // recyclerView.setAdapter(fileListAdapter);


        btn_upload_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooserDialog();
            }
        });

        final SharedPreferences job_experience = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher_experience = job_experience.edit();
        profileTeacher_experience.clear();
        profileTeacher_experience.apply();

        final SharedPreferences area_fragmnt_data = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor profileArea_of_interest = area_fragmnt_data.edit();
        profileArea_of_interest.clear();
        profileArea_of_interest.apply();

        final SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher = personal_profile.edit();


        btn_profile_next = root.findViewById(R.id.btn_profile_next);
        edt_conveyance_txt = root.findViewById(R.id.edt_conveyance_txt);
        edt_fullname = root.findViewById(R.id.edt_fullname);
        edt_fname = root.findViewById(R.id.edt_fname);
        edt_mtongue = root.findViewById(R.id.edt_mtongue);
        experience_year = root.findViewById(R.id.experience_year);
        back = root.findViewById(R.id.back);

        loader = new Loader(getContext());

        edt_cnic = root.findViewById(R.id.edt_cnic);
        edt_present_address = root.findViewById(R.id.edt_present_address);
        edt_permanent_address = root.findViewById(R.id.edt_permanent_address);
        spinner_catogery = root.findViewById(R.id.spinner_category);
        spinner_category_textview = root.findViewById(R.id.spinner_category_textview);
//        edt_dob = root.findViewById(R.id.edt_dob);
        edt_age = root.findViewById(R.id.edt_age);
        edt_email = root.findViewById(R.id.edt_email);
        edt_phone1 = root.findViewById(R.id.edt_phone1);
        edt_phone2 = root.findViewById(R.id.edt_phone2);
        edt_conveyance_txt = root.findViewById(R.id.edt_conveyance_txt);
        edt_nationality = root.findViewById(R.id.edt_nationality);
        edt_religion = root.findViewById(R.id.edt_religion);
        btn_profile_upload = root.findViewById(R.id.btn_profile_upload);
        spinner1 = root.findViewById(R.id.spinner_gender);
        spinner_gender_textview = root.findViewById(R.id.spinner_gender_textview);
//        edt_date_of_submission = root.findViewById(R.id.edt_date_of_submission);
        first_date = root.findViewById(R.id.edt_dob);

        first_date_btn = root.findViewById(R.id.first_date_btn);
        second_date = root.findViewById(R.id.edt_date_of_submission);
        second_date_btn = root.findViewById(R.id.second_date_btn);
        image_view_uploaded_image = root.findViewById(R.id.image_view_uploaded_image);

        teacher_profession = root.findViewById(R.id.teacher_profession);
        teacher_profession_textview = root.findViewById(R.id.teacher_profession_textview);
        spinner_conveyance = root.findViewById(R.id.spinner_conveyance);
        spinner_conveyance_textview = root.findViewById(R.id.spinner_conveyance_textview);


        first_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalenderPopup("first date");
            }
        });

        second_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalenderPopup("second date");
            }
        });


        gender = new ArrayList<>();
        gender.add("Select Preffered Gender");
        gender.add("Male");
        gender.add("Female");

        catogery = new ArrayList<>();
        catogery.add("Select Desired Catogery");
        catogery.add("Home Tution");
        catogery.add("Tution Job");
        catogery.add("Both");

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("ViewData",
                Context.MODE_PRIVATE);
        userid = sharedPreferences1.getString("UserId", "");
        if (!userid.equals("")) {
            viewProfile();
        } else {
            sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                    Context.MODE_PRIVATE);
            userid = sharedPreferences1.getString("userid", "");
            Log.i("ID", userid);
            //Teacher Id:
            Log.i("TeacherID", userid);


            final ArrayAdapter<String> spinner1_adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gender) {
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
            spinner1.setAdapter(spinner1_adapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    profileTeacher.putString("gender", String.valueOf(gender.get(position)));
                    Log.i("genderOfteacher" , String.valueOf(gender));

                    if (position == 0) {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    } else {

                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            final ArrayAdapter<String> spinner_adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, catogery) {
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
            spinner_catogery.setAdapter(spinner_adapter2);
            spinner_catogery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    profileTeacher.putString("IfInstituteOther", String.valueOf(catogery.get(position)));
                    profileTeacher.apply();
                    if (position == 0) {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    } else {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            paths = new ArrayList<>();
            paths.add("Are you a Teacher by Profession?");
            paths.add("Yes");
            paths.add("No");

            paths1 = new ArrayList<>();
            paths1.add("Do you have conveyance?");
            paths1.add("Yes");
            paths1.add("No");


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, paths) {
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
            teacher_profession.setAdapter(adapter);
            teacher_profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    profileTeacher.putString("teacherbyprofession", String.valueOf(paths.get(position)));
                    profileTeacher.apply();
                    if (position == 0) {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    } else {
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);
                    }
//                    Filter_selected1 = paths.get(position);
//
//                    if (Filter_selected1.equals("Yes")){
//                        experience_year.setVisibility(View.VISIBLE);
//                    }
//                    else
//                    {
//
//                        experience_year.setVisibility(View.GONE);
//                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, paths1) {
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
            spinner_conveyance.setAdapter(adapter1);
            spinner_conveyance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


                    if (position == 0) {

                        edt_conveyance_txt.setVisibility(View.GONE);
                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.text_color_selection));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);

                    } else {

                        ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.textcolor));
                        ((TextView) adapterView.getChildAt(0)).setTextSize((float) 13.6);
                        ((TextView) adapterView.getChildAt(0)).setPadding(50, 0, 50, 0);

                    }
                    Filter_selected = paths1.get(position);

                    if (Filter_selected.equals("Yes")) {

                        edt_conveyance_txt.setVisibility(View.VISIBLE);
                        //profileTeacher.putString("personalconveyance",String.valueOf(edt_conveyance_txt.getText()));
                        // Log.i("personalconveyance", String.valueOf(edt_conveyance_txt.getText()));


                    } else if (Filter_selected.equals("No")) {

                        edt_conveyance_txt.setVisibility(View.GONE);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            btn_profile_upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openImageChooserDialog();
//                    if (checkFields()) {
//                        openImageChooserDialog();
//
//                    }

                }
            });

        }

        List<EditText> allFields = new ArrayList<EditText>();

        allFields.add(edt_fullname);
        allFields.add(edt_fname);
        allFields.add(edt_mtongue);
        allFields.add(edt_cnic);
        allFields.add(edt_present_address);
        allFields.add(edt_email);
        allFields.add(edt_permanent_address);
        allFields.add(edt_nationality);
        allFields.add(edt_religion);
        allFields.add(edt_phone1);
        allFields.add(edt_cnic);
        allFields.add(edt_age);
        allFields.add(edt_nationality);


        btn_profile_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("ViewData",
//                        Context.MODE_PRIVATE);
//                userid = sharedPreferences1.getString("UserId", "");
                SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("LoginData",
                        Context.MODE_PRIVATE);
                userid = sharedPreferences1.getString("userid", "");
//                if (!userid.equals("")) {
//                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.nav_host_fragment, new Qualification()).addToBackStack("tag");
//                    fragmentTransaction.commit();
//                } else {
//
//                    Log.i("ID", userid);
//                    //Teacher Id:
//                    Log.i("TeacherID", userid);

                    if (checkFields() && !userid.equals("")) {
                        if (!imageBitmapBase64.equals("")) {
                            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.nav_host_fragment, new Qualification()).addToBackStack("tag");
                            fragmentTransaction.commit();
                        } else {
                            Toast.makeText(getContext(), "Please Upload Image", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



//

        });


        return root;
    }

    private boolean checkFields() {

        final SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher = personal_profile.edit();
        Log.i("profileTeacher" , String.valueOf(profileTeacher));
        List<EditText> ErrorFields = new ArrayList<EditText>();//empty Edit text arraylist
        for (int j = 0; j < allFields.size(); j++) {
            if (TextUtils.isEmpty(allFields.get(j).getText())) {
                // EditText was empty
                //   Fields.add(allFields.get(j).getText().toString());
                ErrorFields.add(allFields.get(j));//add empty Edittext only in this ArayList
                break;
            }
        }
        for (int i = 0; i < ErrorFields.size(); i++) {
            //Fields.add(ErrorFields.get(i).getText().toString());
            EditText currentField = ErrorFields.get(i);
            currentField.setError("this field required");
            ErrorFields.set(i, currentField);
            currentField.requestFocus();
            return false;
        }
        Log.i("first_date.getText()", String.valueOf(first_date.getText()));
        if (ErrorFields.isEmpty() && spinner1.getSelectedItemPosition() != 0 && teacher_profession.getSelectedItemPosition() != 0
                && spinner_catogery.getSelectedItemPosition() != 0 && spinner_conveyance.getSelectedItemPosition() != 0
                && !first_date.getText().toString().equals("Date Of Birth") && !second_date.getText().toString().equals("Date Of Submission")

        ) {


            profileTeacher.putString("personalconveyance", String.valueOf(edt_conveyance_txt.getText()));
            Log.i("personalconveyance", String.valueOf(edt_conveyance_txt.getText()));
            profileTeacher.putString("fullname", String.valueOf(edt_fullname.getText()));
            profileTeacher.putString("fathusname", String.valueOf(edt_fname.getText()));
            profileTeacher.putString("mothnametounge", String.valueOf(edt_mtongue.getText()));
            profileTeacher.putString("age", String.valueOf(edt_age.getText()));
            profileTeacher.putString("nationality", String.valueOf(edt_nationality.getText()));
            profileTeacher.putString("religion", String.valueOf(edt_religion.getText()));
            profileTeacher.putString("cnicno", String.valueOf(edt_cnic.getText()));
            profileTeacher.putString("presentadd", String.valueOf(edt_present_address.getText()));
            profileTeacher.putString("permanentadd", String.valueOf(edt_permanent_address.getText()));
            profileTeacher.putString("phoneno1", String.valueOf(edt_phone1.getText()));
            profileTeacher.putString("phoneno2", String.valueOf(edt_phone2.getText()));
            profileTeacher.putString("email", String.valueOf(edt_email.getText()));
            profileTeacher.putString("experienceYear", String.valueOf(experience_year.getText()));
            profileTeacher.putString("tutorimageBase64", String.valueOf("data:image/png;base64," + imageBitmapBase64));
            profileTeacher.apply();


            Toast.makeText(getContext(), " All Fields", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            if (ErrorFields.size() <= 0) {
                Toast.makeText(getContext(), "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                if (first_date.getText().toString().equals("DD-MM-YYYY")) {
                    first_date.setError("this field required");
                    first_date.setPadding(0, 10, 50, 0);
                    first_date.requestFocus();
                }
                if (second_date.getText().toString().equals("DD-MM-YYYY")) {
                    second_date.setError("this field required");
                    second_date.setPadding(0, 10, 50, 0);
                    second_date.requestFocus();
                }
                if (spinner1.getSelectedItemPosition() == 0) {
                    ((TextView) spinner1.getSelectedView()).setError("Error message");
                    spinner1.setPadding(0, 10, 50, 0);
                    spinner1.requestFocus();
                }
                if (spinner_catogery.getSelectedItemPosition() == 0) {
                    ((TextView) spinner_catogery.getSelectedView()).setError("Error message");
                    spinner_catogery.setPadding(0, 10, 50, 0);
                    spinner_catogery.requestFocus();
                }
                if (teacher_profession.getSelectedItemPosition() == 0) {
                    teacher_profession.setPadding(0, 10, 50, 0);
                    ((TextView) teacher_profession.getSelectedView()).setError("Error message");
                    teacher_profession.requestFocus();
                }
                if (spinner_conveyance.getSelectedItemPosition() == 0) {
                    spinner_conveyance.setPadding(0, 10, 50, 0);
                    ((TextView) spinner_conveyance.getSelectedView()).setError("Error message");
                    spinner_conveyance.requestFocus();
                }

            }
            return false;
//                             first_date.setError("this field required");
//                             second_date.setError("this field required");
//                             first_date.setPadding(0, 10, 50, 0);
//                             second_date.setPadding(0, 10, 50, 0);

        }
    }

    private void openImageChooserDialog() {
        final CharSequence[] items = {"Take Photo", "Gallery", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void openFileChooserDialog() {
        final CharSequence[] items = {"File Manager", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Files");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("File Manager")) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (dateType.equals("first date")) {
            year1 = year;
            month1 = month;
            date1 = dayOfMonth;
        } else if (dateType.equals("second date")) {
            year2 = year;
            month2 = month;
            date2 = dayOfMonth;
        }
        updateDisplay(dateType);
    }

    private void updateDisplay(String date_type) {
        if (date_type.equals("first date")) {
            fromDate = year1 + "-" + String.format("%02d", (month1 + 1)) + "-" + String.format("%02d", date1);
            Log.i("fromDate", fromDate);

            first_date.setText(new StringBuilder()
                    .append(date1).append("-").append(month1 + 1).append("-").append(year1));
            first_date.setError(null);
        } else if (date_type.equals("second date")) {
            toDate = year2 + "-" + String.format("%02d", (month2 + 1)) + "-" + String.format("%02d", date2);
            second_date.setText(new StringBuilder()
                    .append(date2).append("-").append(month2 + 1).append("-").append(year2));
            second_date.setError(null);
        }


        final SharedPreferences personal_profile = getContext().getSharedPreferences("SendData",
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor profileTeacher = personal_profile.edit();
        profileTeacher.putString("dob", String.valueOf(fromDate));
        profileTeacher.putString("dateofsubmission", String.valueOf(toDate));
        profileTeacher.apply();
    }

    private void openCalenderPopup(String date_type) {
        dateType = date_type;
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.DialogTheme, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void viewProfile() {
        back.setVisibility(View.GONE);
        btn_profile_upload.setVisibility(View.GONE);
        btn_upload_files.setVisibility(View.GONE);
        JSONObject map = new JSONObject();
        loader.showLoader();

        try {
            map.put("TutorId", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, Url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("ViewProfile", String.valueOf(response));
                loader.hideLoader();
                Gson gson = new Gson();

                SharedPreferences personal_profile = getContext().getSharedPreferences("ViewProfile",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor profileTeacher = personal_profile.edit();
                profileTeacher.putString("ViewProfileData", gson.toJson(response));
                profileTeacher.apply();

                edt_fullname.setEnabled(false);
                edt_fullname.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_fname.setEnabled(false);
                edt_fname.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_mtongue.setEnabled(false);
                edt_mtongue.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_age.setEnabled(false);
                edt_age.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_phone1.setEnabled(false);
                edt_phone1.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_phone2.setEnabled(false);
                edt_phone2.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_email.setEnabled(false);
                edt_email.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_cnic.setEnabled(false);
                edt_cnic.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_present_address.setEnabled(false);
                edt_present_address.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_permanent_address.setEnabled(false);
                edt_permanent_address.setTextColor(getResources().getColor(R.color.text_color_selection));

                first_date.setEnabled(false);
                first_date.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_nationality.setEnabled(false);
                edt_nationality.setTextColor(getResources().getColor(R.color.text_color_selection));

                edt_religion.setEnabled(false);
                edt_religion.setTextColor(getResources().getColor(R.color.text_color_selection));

                second_date.setEnabled(false);
                second_date.setTextColor(getResources().getColor(R.color.text_color_selection));
                experience_year.setEnabled(false);
                experience_year.setTextColor(getResources().getColor(R.color.text_color_selection));
                spinner1.setClickable(false);
                spinner1.setEnabled(false);
//        spinner1.setVisibility(View.GONE);
                spinner_gender_textview.setVisibility(View.VISIBLE);
                spinner_gender_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

                spinner_catogery.setClickable(false);
                spinner_catogery.setEnabled(false);
//        spinner1.setVisibility(View.GONE);
                spinner_category_textview.setVisibility(View.VISIBLE);
                spinner_category_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

                spinner_conveyance.setClickable(false);
                spinner_conveyance.setEnabled(false);
//        spinner1.setVisibility(View.GONE);
                spinner_conveyance_textview.setVisibility(View.VISIBLE);
                spinner_conveyance_textview.setTextColor(getResources().getColor(R.color.text_color_selection));

                teacher_profession.setClickable(false);
                teacher_profession.setEnabled(false);
//        spinner1.setVisibility(View.GONE);
                teacher_profession_textview.setVisibility(View.VISIBLE);
                teacher_profession_textview.setTextColor(getResources().getColor(R.color.text_color_selection));
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                try {
                    edt_fullname.setText(response.getString("FullName"));
                    edt_fname.setText(response.getString("FatherHusbandName"));
                    edt_mtongue.setText(response.getString("MotherNameTounge"));
                    edt_age.setText(response.getString("Age"));
                    edt_phone1.setText(response.getString("PhoneNo1"));
                    edt_phone2.setText(response.getString("PhoneNo2"));
                    edt_email.setText(response.getString("Email"));
                    edt_cnic.setText(response.getString("CnicNo"));
                    edt_present_address.setText(response.getString("PresentAddress"));
                    edt_permanent_address.setText(response.getString("PermanentAddress"));
                    Date DateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(response.getString("DateOfBirth"));
                    Date DateOfFormSubmission = new SimpleDateFormat("yyyy-MM-dd").parse(response.getString("DateOfFormSubmission"));

                    first_date.setText(format.format(DateOfBirth));
                    first_date_btn.setEnabled(false);
                    edt_nationality.setText(response.getString("Nationality"));
                    edt_religion.setText(response.getString("Religion"));
                    second_date.setText(format.format(DateOfFormSubmission));
                    second_date_btn.setEnabled(false);

                    spinner_gender_textview.setText(response.getString("Gender"));
                    spinner_category_textview.setText(response.getString("TutorCategory"));
                    spinner_conveyance_textview.setText(response.getString("PersonalConveyance"));
                    teacher_profession_textview.setText(response.getString("TeacherByProfession"));
                    experience_year.setText(response.getString("TotalExperience"));
                    image_view_uploaded_image.setVisibility(View.VISIBLE);
                    image_view_uploaded_image.setImageBitmap(convertBase64ToBitmap(response.getString("TutorImage")));
                    btn_profile_upload.setVisibility(View.GONE);
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Content-Type", "json");
                return map;
            }
        };
        Volley.newRequestQueue(getContext()).add(sr);
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tool_bar_heading.setText("Dashboard");
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.container, new HomeFragment()).addToBackStack("null");
                    fragmentTransaction.commit();
                    return true;

                }


                return false;
            }
        });

    }

    private String getRealPathFromURI(Uri contentURI) {

        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = getContext().getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            thePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return thePath;
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] b = baos.toByteArray();

        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.NO_WRAP);

        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }

    private Bitmap convertBase64ToBitmap(String b64) {
        final String pureBase64Encoded = b64.substring(b64.indexOf(",") + 1);
        byte[] imageAsBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_FILE: {
                if (resultCode == RESULT_OK) {
                    if (null != data) { // checking empty selection
                        if (null != data.getClipData()) { // checking multiple selection or not
                            for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                                Uri uri = data.getClipData().getItemAt(i).getUri();
                                Log.i("multiple_select", String.valueOf(uri));
                                InputStream imageStream = null;
                                try {
                                    imageStream = getContext().getContentResolver().openInputStream(uri);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("imageStream  " + imageStream);

                                Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                                System.out.println("yourSelectedImage  " + yourSelectedImage);

                                FileBitmapBase64 = encodeTobase64(yourSelectedImage);
                                Log.e("imageBase64", FileBitmapBase64);
                                fileName = getRealPathFromURI(uri);
                                // image_view_uploaded_image.setImageBitmap(imageName);
                                Toast.makeText(getContext(), "selected images" + fileName, Toast.LENGTH_LONG).show();
                            }
                        } else {

                            final Uri imageUri = data.getData();
                            System.out.println("data" + data.getData());
//
//                    Bundle extras = data.getExtras();
//                    Bitmap bmp = (Bitmap) extras.get("dx`xata");
//                    System.out.println("bmp " + bmp);

                            InputStream imageStream = null;
                            try {
                                imageStream = getContext().getContentResolver().openInputStream(imageUri);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            System.out.println("imageStream  " + imageStream);

                            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                            System.out.println("yourSelectedImage  " + yourSelectedImage);

                            imageBitmapBase64 = encodeTobase64(yourSelectedImage);
                            Log.e("imageBase64", imageBitmapBase64);
                            imageName = getRealPathFromURI(imageUri);
                            // image_view_uploaded_image.setImageBitmap(imageName);
                            Toast.makeText(getContext(), "selected images" + imageName, Toast.LENGTH_LONG).show();
                        }
                    }
                }
//              //  Log.i("data_image" , String.valueOf(data.getClipData()));
//                if (resultCode == RESULT_OK && data != null) {
//                    final Uri imageUri = data.getData();
//                    System.out.println("data" + data.getData());
////
////                    Bundle extras = data.getExtras();
////                    Bitmap bmp = (Bitmap) extras.get("dx`xata");
////                    System.out.println("bmp " + bmp);
//
//                    InputStream imageStream = null;
//                    try {
//                        imageStream = getContext().getContentResolver().openInputStream(imageUri);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("imageStream  " + imageStream);
//
//                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
//                    System.out.println("yourSelectedImage  " + yourSelectedImage);
//
//                    imageBitmapBase64 = encodeTobase64(yourSelectedImage);
//                    Log.e("imageBase64", imageBitmapBase64);
//                    imageName = getRealPathFromURI(imageUri);
//                    // image_view_uploaded_image.setImageBitmap(imageName);
//                    Toast.makeText(getContext(), "selected images" + imageName, Toast.LENGTH_LONG).show();
//
//                }
                else {
                    Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
                }
                break;

            }
            case REQUEST_CAMERA: {
                if (resultCode == RESULT_OK && data != null) {
                    Bundle extras = data.getExtras();
                    // Get the returned image from extra
                    Bitmap bmp = (Bitmap) extras.get("data");
                    imageBitmapBase64 = encodeTobase64(bmp);
                    Log.e("imageBase64", imageBitmapBase64);

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyyyyhhmmss");
                    String datetime = dateformat.format(c.getTime());
                    System.out.println(datetime);

                    imageName = datetime + ".jpg";
//                    FileName.setText(imageName);
                    // Toast.makeText(getContext(), imageName, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
                }
                break;
            }


        }
    }
}



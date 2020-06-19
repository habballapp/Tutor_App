package com.example.tutor_app.Dashboard.ui.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tutor_app.Adapters.ViewAdapter;
import com.example.tutor_app.Model_Classes.View_List;
import com.example.tutor_app.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    private RecyclerView rl_recycler;
    RecyclerView.Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_view, container, false);
        rl_recycler = root.findViewById(R.id.rv_fragment_payments);
        rl_recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

       // List<View_List> view_list =;
        adapter = new ViewAdapter("Shabbir ","teacher","0323223","phone","shabbir@gmail.com","person" ,"abc","eng","400","monthy",getContext());
        rl_recycler.setAdapter(adapter);





        return root;
    }
}

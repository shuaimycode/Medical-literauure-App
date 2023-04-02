package com.he.yiliao;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.he.yiliao.adapter.ShowLiteratureAdapter;
import com.he.yiliao.bean.LiteratureObj;

import java.util.ArrayList;
import java.util.List;

public class home extends Fragment {

    private List<LiteratureObj> literatureList = new ArrayList<>();
    private LiteratureSQLite sQlite;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        EditText etSearch = getActivity().findViewById(R.id.et_search);
        etSearch.setFocusable(false);
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        initdata();
        RecyclerView recyclerView = (RecyclerView)getActivity().findViewById(R.id.LiteratureList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ShowLiteratureAdapter adapter = new ShowLiteratureAdapter(getActivity(), literatureList);
        recyclerView.setAdapter(adapter);
    }

    private void initdata(){
        sQlite = new LiteratureSQLite(getActivity());
        literatureList = sQlite.getAllDATA();
    }

}

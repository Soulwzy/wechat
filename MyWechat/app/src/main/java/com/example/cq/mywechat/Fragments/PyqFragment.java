package com.example.cq.mywechat.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cq.mywechat.Adapters.PyqAdapter;
import com.example.cq.mywechat.Entity.PyqInfo;
import com.example.cq.mywechat.R;
import com.example.cq.mywechat.SQLite.DBManager;

import java.util.ArrayList;
import java.util.List;

public class PyqFragment extends Fragment {
    private ListView lv_pyq;
    private DBManager dbManager;
    private List<PyqInfo> pyq = new ArrayList<>();
    private PyqAdapter pyqAdapter;
    private View view;
    private TextView tv_detail;
    private boolean isExpend = false;
    private TextView tv_pyqmsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.pyq_layout, container, false);
            lv_pyq = view.findViewById(R.id.lv_pyq);

            initData();
            initView();

        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    private void initView() {
        Log.d("cq","INNNNNNNNNNNNNNNNNNNNNNNNNN");
        View pyq_layout = getLayoutInflater().inflate(R.layout.pyq_item,null);
        tv_detail = pyq_layout.findViewById(R.id.tv_detail);
        tv_pyqmsg = pyq_layout.findViewById(R.id.tv_pyqmsg);

    }

    private void initData() {
        dbManager = new DBManager(getActivity());
        pyq = new ArrayList<>();
        //dbManager.addPyq();
        pyq.addAll(dbManager.queryMsg());
       // Log.d("cq", "PYQQQQQQQQQQQQQQQQQQQQQQQ" + dbManager.queryMsg().size());
        pyqAdapter = new PyqAdapter(getActivity(), pyq);
        lv_pyq.setAdapter(pyqAdapter);
    }

}




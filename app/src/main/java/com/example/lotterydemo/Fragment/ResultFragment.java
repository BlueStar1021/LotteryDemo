package com.example.lotterydemo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lotterydemo.Activity.DLTResultActivity;
import com.example.lotterydemo.Activity.FCSDResultActivity;
import com.example.lotterydemo.Activity.PLSResultActivity;
import com.example.lotterydemo.Activity.PLWResultActivity;
import com.example.lotterydemo.Activity.QLCResultActivity;
import com.example.lotterydemo.Activity.QXCResultActivity;
import com.example.lotterydemo.Activity.SSQResultActivity;
import com.example.lotterydemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    LinearLayout ssq,dlt,qlc,fcsd,qxc,pls,plw;      //七个彩种的板块

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        //绑定控件
        ssq = (LinearLayout)view.findViewById(R.id.fragment_result_ssq);
        dlt = (LinearLayout)view.findViewById(R.id.fragment_result_dlt);
        qlc = (LinearLayout)view.findViewById(R.id.fragment_result_qlc);
        fcsd = (LinearLayout)view.findViewById(R.id.fragment_result_fcsd);
        qxc = (LinearLayout)view.findViewById(R.id.fragment_result_qxc);
        pls = (LinearLayout)view.findViewById(R.id.fragment_result_pls);
        plw = (LinearLayout)view.findViewById(R.id.fragment_result_plw);



        //触发事件
        ssq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SSQResultActivity.class);
                startActivity(intent);
            }
        });

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DLTResultActivity.class);
                startActivity(intent);
            }
        });

        qlc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QLCResultActivity.class);
                startActivity(intent);
            }
        });

        qxc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QXCResultActivity.class);
                startActivity(intent);
            }
        });

        fcsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FCSDResultActivity.class);
                startActivity(intent);
            }
        });

        pls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PLSResultActivity.class);
                startActivity(intent);
            }
        });

        plw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PLWResultActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}

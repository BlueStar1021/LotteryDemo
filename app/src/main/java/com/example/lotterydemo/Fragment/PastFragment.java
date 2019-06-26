package com.example.lotterydemo.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.lotterydemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PastFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastFragment extends Fragment {

    LinearLayout ssq,dlt,qlc,fcsd,qxc,pls,plw;      //七个彩种的板块

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_past, container, false);

        //绑定控件
        ssq = (LinearLayout)view.findViewById(R.id.fragment_past_ssq);
        dlt = (LinearLayout)view.findViewById(R.id.fragment_past_dlt);
        qlc = (LinearLayout)view.findViewById(R.id.fragment_past_qlc);
        fcsd = (LinearLayout)view.findViewById(R.id.fragment_past_fcsd);
        qxc = (LinearLayout)view.findViewById(R.id.fragment_past_qxc);
        pls = (LinearLayout)view.findViewById(R.id.fragment_past_pls);
        plw = (LinearLayout)view.findViewById(R.id.fragment_past_plw);

        return view;
    }


}

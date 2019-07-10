package com.example.lotterydemo.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lotterydemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MemoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemoFragment extends Fragment {

    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fragment_memo_fab);

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}

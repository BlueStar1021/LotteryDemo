package com.example.lotterydemo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lotterydemo.Activity.NewsDetailActivity;
import com.example.lotterydemo.Activity.TopImageDetailActivity;
import com.example.lotterydemo.Interface.DynamicBGArea;
import com.example.lotterydemo.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment implements DynamicBGArea {

    //控件集
    ImageView topImg;               //上方图片区域

    TextView news_1,news_2,news_3;    //中部最新讯息

    TextView ideas_1,ideas_2,ideas_3;   //中部彩票心理学

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        //绑定控件
        topImg = (ImageView)view.findViewById(R.id.fragment_info_topimg);
        news_1 = (TextView)view.findViewById(R.id.fragment_info_news_1);
        news_2 = (TextView)view.findViewById(R.id.fragment_info_news_2);
        news_3 = (TextView)view.findViewById(R.id.fragment_info_news_3);
        ideas_1 = (TextView)view.findViewById(R.id.fragment_info_ideas_1);
        ideas_2 = (TextView)view.findViewById(R.id.fragment_info_ideas_2);
        ideas_3 = (TextView)view.findViewById(R.id.fragment_info_ideas_3);

        //初始化背景
        createNewThread(topImg, R.drawable.info_img);

        //触发事件
        topImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), TopImageDetailActivity.class);
                startActivity(intent);

            }
        });

        news_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("name", getResources().getString(R.string.info_news_1));
                intent.putExtra("url", getResources().getString(R.string.info_news_1_url));
                startActivity(intent);
            }
        });

        news_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("name", getResources().getString(R.string.info_news_2));
                intent.putExtra("url", getResources().getString(R.string.info_news_2_url));
                startActivity(intent);
            }
        });

        news_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("name", getResources().getString(R.string.info_news_3));
                intent.putExtra("url", getResources().getString(R.string.info_news_3_url));
                startActivity(intent);
            }
        });

        ideas_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("name", getResources().getString(R.string.info_ideas_1));
                intent.putExtra("url", getResources().getString(R.string.info_ideas_1_url));
                startActivity(intent);
            }
        });

        ideas_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("name", getResources().getString(R.string.info_ideas_2));
                intent.putExtra("url", getResources().getString(R.string.info_ideas_2_url));
                startActivity(intent);
            }
        });

        ideas_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("name", getResources().getString(R.string.info_ideas_3));
                intent.putExtra("url", getResources().getString(R.string.info_ideas_3_url));
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void setBackground(View view, int imageId) {
        view.setBackgroundResource(imageId);
    }

    @Override
    public void createNewThread(final View view, final int imageId) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setBackground(view, imageId);
            }
        });
    }
}

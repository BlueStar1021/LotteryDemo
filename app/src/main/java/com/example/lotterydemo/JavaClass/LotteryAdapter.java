package com.example.lotterydemo.JavaClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lotterydemo.R;

import java.util.List;

/**
 * Created by BlueStar on 2019/6/26.
 */

public class LotteryAdapter extends ArrayAdapter<Lottery> {

    private int resourceId;

    public LotteryAdapter(Context context, int textViewResourceId, List<Lottery> objects){

        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Lottery lottery = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView no = (TextView) view.findViewById(R.id.past_list_item_no);
        TextView date = (TextView) view.findViewById(R.id.past_list_item_date);
        no.setText("第" + lottery.getNo() + "期");
        date.setText(lottery.getDate());
        return view;
    }

}

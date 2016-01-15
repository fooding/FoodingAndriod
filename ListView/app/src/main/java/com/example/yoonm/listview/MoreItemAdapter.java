package com.example.yoonm.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yoonm on 2016-01-15.
 */
class MoreItemAdapter extends ArrayAdapter<String> {

    Context ctx;

    List<String> mDatas;

    int resId;


    public MoreItemAdapter(Context context, int textViewResourceId, List<String> items) {

        super(context, textViewResourceId, items);

        ctx = context;

        mDatas = items;

        resId = textViewResourceId;

    }



/* (non-Javadoc)

* @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)

*/

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        TextView holder;


        if (row == null) {

            LayoutInflater inflator = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = inflator.inflate(resId, null);

            holder = (TextView) row.findViewById(R.id.tv_list_item);

            row.setTag(holder);

        } else {

            holder = (TextView) row.getTag();

        }


        final String str = mDatas.get(position);


        if (str != null) {

            holder.setText(str);

        }


        return row;

    }

}

package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoonmin on 2015-11-25.
 */
public class Featured_Discover extends Activity{

    TextView Title, Context1, Context2;

    ImageView FD;

    Adapter adapter = null;

    GridView Grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_discover);

        Grid = (GridView) findViewById(R.id.Grid);

        adapter = new Adapter(this);

        Grid.setAdapter(adapter);

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "context", "context2");

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "sadsadsa", "context2");

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "cfsdfdsfds", "context2");

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "cfsdfdsfds", "context2");

    }

    class Adapter extends BaseAdapter{

        private Context mContext;
        private ArrayList<ListData2> arrayList = new ArrayList<>();

        public Adapter(Context mContext){
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                LayoutInflater v = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = v.inflate(R.layout.featured_discover_item_2, null);

                FD = (ImageView) convertView.findViewById(R.id.FD);

                Title = (TextView) convertView.findViewById(R.id.Title);
                Context1 = (TextView) convertView.findViewById(R.id.Context_1);
                Context2 = (TextView) convertView.findViewById(R.id.Context_2);

            }

            ListData2 mList = arrayList.get(position);

            FD.setImageDrawable(mList.FD);
            Title.setText(mList.Title);
            Context1.setText(mList.Context1);
            Context2.setText(mList.Context2);

            return convertView;
        }

        public void additem(Drawable fd, String title, String context1, String context2){

            ListData2 info = null;

            info = new ListData2();

            info.FD = fd;
            info.Title = title;
            info.Context1 = context1;
            info.Context2 = context2;

            arrayList.add(info);

        }
    }
}

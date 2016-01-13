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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yoonmin on 2015-11-25.
 */
public class Featured_Discover extends Activity{

    TextView TimeLine_Title, TimeLine_Information;

    ImageView TimeLine_Image;

    Adapter adapter = null;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_discover);

        list = (ListView) findViewById(R.id.list);

        adapter = new Adapter(this);

        list.setAdapter(adapter);

        adapter.additem(getResources().getDrawable(R.drawable.tbk), "떡볶이", "항상 이시간 쯤에 떡볶이를 드시죠?");

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "sadsadsa");

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "cfsdfdsfds");

        adapter.additem(getResources().getDrawable(R.drawable.jajang), "title", "cfsdfdsfds");

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

                TimeLine_Image = (ImageView) convertView.findViewById(R.id.TimeLine_Image);

                TimeLine_Title = (TextView) convertView.findViewById(R.id.TimeLine_Title);
                TimeLine_Information = (TextView) convertView.findViewById(R.id.TimeLine_Information);

            }

            ListData2 mList = arrayList.get(position);

            TimeLine_Image.setImageDrawable(mList.TimeLine_Image);
            TimeLine_Title.setText(mList.TimeLine_Title);
            TimeLine_Information.setText(mList.TimeLine_Information);

            return convertView;
        }

        public void additem(Drawable TimeLine_Image, String TimeLine_Title, String TimeLine_Information){

            ListData2 info = null;

            info = new ListData2();

            info.TimeLine_Image = TimeLine_Image;
            info.TimeLine_Title = TimeLine_Title;
            info.TimeLine_Information = TimeLine_Information;

            arrayList.add(info);

        }
    }
}

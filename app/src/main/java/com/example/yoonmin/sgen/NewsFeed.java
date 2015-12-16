package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yoonmin on 2015-11-22.
 */
public class NewsFeed extends Activity{

    TextView Tag1, Tag2, Tag3, Tag4, CommentNum, LikeNum, information, Minute, Name;

    ImageView UserPicture, UploadPicture;

    Adapter adapter = null;

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        listview = (ListView)findViewById(R.id.list);

        adapter = new Adapter(this);

        listview.setAdapter(adapter);

        adapter.additem(getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.drawable.jajang), "전상현",
                "5분 전", "맛 있는 짜장면", "#짜장", "#맛있다", "", "", "23", "4");

        adapter.additem(getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.drawable.jajang), "전상현",
                "5분 전", "맛 있는 짜장면", "#짜장", "#맛있다", "", "", "23", "4");

        adapter.additem(getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.drawable.jajang), "전상현",
                "5분 전", "맛 있는 짜장면", "#짜장", "#맛있다", "", "", "23", "4");

    }


    class Adapter extends BaseAdapter{

        private Context mContext;
        private ArrayList<ListData> arrayList = new ArrayList<>();

        public Adapter(Context context){
            super();
            this.mContext = context;

        }

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
            if(convertView == null){
                LayoutInflater v = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = v.inflate(R.layout.newsfeed_item_2, null);

                Name = (TextView) convertView.findViewById(R.id.Name);
                Minute = (TextView) convertView.findViewById(R.id.Minute);
                information = (TextView) convertView.findViewById(R.id.information);
                Tag1 = (TextView) convertView.findViewById(R.id.Tag1);
                Tag2 = (TextView) convertView.findViewById(R.id.Tag2);
                Tag3 = (TextView) convertView.findViewById(R.id.Tag3);
                Tag4 = (TextView) convertView.findViewById(R.id.Tag4);
                CommentNum = (TextView) convertView.findViewById(R.id.CommentNum);
                LikeNum = (TextView) convertView.findViewById(R.id.LikeNum);

                UploadPicture = (ImageView) convertView.findViewById(R.id.UploadPicture);
                UserPicture = (ImageView) convertView.findViewById(R.id.UserPicture);

            }

            ListData mList = arrayList.get(position);

            UserPicture.setImageDrawable(mList.UserPicture);
            UploadPicture.setImageDrawable(mList.UploadPicture);
            Name.setText(mList.Name);
            Minute.setText(mList.Minute);
            information.setText(mList.information);
            Tag1.setText(mList.Tag1);
            Tag2.setText(mList.Tag2);
            Tag3.setText(mList.Tag3);
            Tag4.setText(mList.Tag4);

            CommentNum.setText(mList.CommentNum);
            LikeNum.setText(mList.LikeNum);



            return convertView;

        }

        public void additem(Drawable Userpicture, Drawable UploadPicture,
                            String Name, String Minute, String information, String Tag1, String Tag2, String Tag3, String Tag4,
                            String CommentNum, String LikeNum) {

            ListData info = null;

            info = new ListData();

            info.UserPicture = Userpicture;
            info.UploadPicture = UploadPicture;
            info.Name = Name;
            info.Minute = Minute;
            info.information = information;
            info.Tag1 = Tag1;
            info.Tag2 = Tag2;
            info.Tag3 = Tag3;
            info.Tag4 = Tag4;
            info.CommentNum = CommentNum;
            info.LikeNum = LikeNum;

            arrayList.add(info);

        }
    }

}


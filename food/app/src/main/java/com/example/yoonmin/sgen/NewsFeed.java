package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yoonmin on 2015-11-22.
 */
public class NewsFeed extends Activity{

    TextView Tag1, Tag2, Tag3, Tag4, UserName, How, Where, When, Timeset, Text_Title, Text_information;

    ImageView UserPicture, UploadPicture;

    Button Btn_Overflow, Btn_Like, Btn_Comment, Btn_With;

    Adapter adapter = null;

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        listview = (ListView)findViewById(R.id.list);

        adapter = new Adapter(this);

        listview.setAdapter(adapter);

        // 유저 사진, 업로드 사진 사이즈 변경 필요.

        adapter.additem(getResources().getDrawable(R.drawable.shh_2), getResources().getDrawable(R.drawable.shh_1),
                "전상현", "20", "여주 어느 펜션", "5", "분 전", "1월 2일의 저녁",
                "나는 왜 고기를 구워야 하는가... 내 앞에 있는 최승은 짜증나지만 고기는 맛있다!" , "여주", "어느 펜션", "삽겹살", "연기");
        adapter.additem(getResources().getDrawable(R.drawable.kym), getResources().getDrawable(R.drawable.jajang),
                "Yoonmin Kim", "20", "강남 가로수길", "5", "분 전", "1월 4일의 점심",
                "이 짜장면 집은 면발이 억수로 부드럽고 양념맛이 매우 좋다. 위생도 좋아보인다." , "강남", "가로수길", "짜장면", "성공");
        adapter.additem(getResources().getDrawable(R.drawable.pin_icon), getResources().getDrawable(R.drawable.jajang),
                "전상현", "20", "강남 가로수길", "5", "분 전", "1월 4일의 점심",
                "이 짜장면 집은 면발이 억수로 부드럽고 양념맛이 매우 좋다. 위생도 좋아보인다." , "강남", "가로수길", "짜장면", "성공");


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

                UserPicture = (ImageView) convertView.findViewById(R.id.UserPicture);
                UserName = (TextView) convertView.findViewById(R.id.UserName);
                How = (TextView) convertView.findViewById(R.id.How);
                Where = (TextView) convertView.findViewById(R.id.Where);
                When = (TextView) convertView.findViewById(R.id.When);
                Timeset = (TextView) convertView.findViewById(R.id.Timeset);
                Btn_Overflow = (Button) convertView.findViewById(R.id.Btn_Overflow);
                UploadPicture = (ImageView) convertView.findViewById(R.id.UploadPicture);
                Text_Title = (TextView) convertView.findViewById(R.id.Text_Title);
                Text_information = (TextView) convertView.findViewById(R.id.Text_information);
                Tag1 = (TextView) convertView.findViewById(R.id.Tag1);
                Tag2 = (TextView) convertView.findViewById(R.id.Tag2);
                Tag3 = (TextView) convertView.findViewById(R.id.Tag3);
                Tag4 = (TextView) convertView.findViewById(R.id.Tag4);
                Btn_Like = (Button) convertView.findViewById(R.id.Btn_Like);
                Btn_Comment = (Button) convertView.findViewById(R.id.Btn_Comment);
                Btn_With = (Button) convertView.findViewById(R.id.Btn_With);

            }

            ListData mList = arrayList.get(position);

            UploadPicture.setImageDrawable(mList.UploadPicture);
            UserPicture.setImageDrawable(mList.UserPicture);

            UserName.setText(mList.UserName);
            How.setText(mList.How);
            Where.setText(mList.Where);
            When.setText(mList.When);
            Timeset.setText(mList.Timeset);
            Text_Title.setText(mList.Text_Title);
            Text_information.setText(mList.Text_information);
            Tag1.setText(mList.Tag1);
            Tag2.setText(mList.Tag2);
            Tag3.setText(mList.Tag3);
            Tag4.setText(mList.Tag4);

            return convertView;

        }

        public void additem(Drawable Userpicture, Drawable UploadPicture,
                            String UserName, String How, String Where, String When, String Timeset, String Text_Title,
                            String Text_information, String Tag1, String Tag2, String Tag3, String Tag4) {

            ListData info = null;

            info = new ListData();

            info.UploadPicture = UploadPicture;
            info.UserPicture = Userpicture;
            info.UserName = UserName;
            info.How = How;
            info.Where = Where;
            info.When = When;
            info.Timeset = Timeset;
            info.Text_Title = Text_Title;
            info.Text_information = Text_information;
            info.Tag1 = Tag1;
            info.Tag2 = Tag2;
            info.Tag3 = Tag3;
            info.Tag4 = Tag4;

            arrayList.add(info);

        }
    }

}


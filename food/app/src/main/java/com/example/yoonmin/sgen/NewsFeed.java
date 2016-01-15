package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
public class NewsFeed extends Activity {

    int countDiary = 0;

    TextView Tag1, Tag2, Tag3, Tag4, UserName, How, Where, When, Timeset, Text_Title, Text_information;

    ImageView UserPicture, UploadPicture;

    int lastview = 0;

    Button Btn_Overflow, Btn_Like, Btn_Comment, Btn_With;

    Adapter adapter = null;

    ListView listview;

    private final static int INSERT_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        listview = (ListView) findViewById(R.id.list);

        adapter = new Adapter(this);

        adapter.additem(getResources().getDrawable(R.drawable.shh_2), getResources().getDrawable(R.drawable.shh_1),
                "전상현", "20", "여주 어느 펜션", "" + countDiary++, "분 전", "1월 2일의 저녁",
                "나는 왜 고기를 구워야 하는가... 내 앞에 있는 최승은 짜증나지만 고기는 맛있다!", "여주", "어느 펜션", "삽겹살", "연기");

        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(view.isShown()){
                    // 리스트뷰의 0 번 인덱스 항목이 리스트뷰의 상단에 보이고 있는 경우
                    if(listview.getFirstVisiblePosition() == 0) {
                        // 항목을 추가한다.

                            adapter.additem(getResources().getDrawable(R.drawable.shh_2), getResources().getDrawable(R.drawable.shh_1),
                                    "전상현", "20", "여주 어느 펜션", ""+countDiary++, "분 전", "1월 2일의 저녁",
                                    "나는 왜 고기를 구워야 하는가... 내 앞에 있는 최승은 짜증나지만 고기는 맛있다!" , "여주", "어느 펜션", "삽겹살", "연기");
//                        adapter.additem(UserPicture, UploadPicture, UserName, How, Where, When, Timeset
//                        , Text_Title, Text_information, Tag1, Tag2, Tag3, Tag4);

                        // 0 번 인덱스 항목 위로 INSERT_COUNT 개수의 항목이 추가되었으므로
                        // 기존의 0 번 인덱스 항목은 INSERT_COUNT 번 인덱스가 되었다.
                        // 기존 0번 항목이 보여져서 항목이 추가될때 해당 항목의 모든 영역이
                        // 보이지않았을 수도 있으므로 이미 모든 영역이 노출됐던 INSERT_COUNT + 1
                        // 항목을 보이도록 설정하여 스크롤을 부드럽게 보이도록 한다.
                        view.setSelection(INSERT_COUNT + 1);
                    }
                }
            }
        });
        listview.setAdapter(adapter);
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


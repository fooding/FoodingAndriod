package com.example.yoonm.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by yoonm on 2016-01-15.
 */
public class MoreListThreadExample extends Activity {

    public static final int INC_COUNT = 15; // 추가로 보여줄 item 개수

    MoreItemAdapter adtMore;

    ListView lvMore;

    View footer, header;

    ArrayList<String> datas = new ArrayList<String>();

    boolean justOnce = true, isHeader = false;

    int footerId, headerId;


    @Override

    public void onCreate(Bundle savedInstancestate) {

        super.onCreate(savedInstancestate);

        setContentView(R.layout.ui_listview2);


        System.out.println("Entered onCreate()");


        lvMore = (ListView) findViewById(R.id.lv_morelist);

        footer = getLayoutInflater().inflate(R.layout.ui_sub_footer, null, false);

        header = getLayoutInflater().inflate(R.layout.ui_sub_header, null, false);


        getData();

    }



/* (non-Javadoc)

* @see android.app.Activity#onResume()

*/

    @Override

    protected void onResume() {

        System.out.println("Entered onResume()");


        adtMore = new MoreItemAdapter(this, R.layout.ui_sub_list, datas);

        lvMore.addFooterView(footer);

        lvMore.addHeaderView(header);

        lvMore.setAdapter(adtMore);

        lvMore.setSelection(lvMore.getHeaderViewsCount());

        lvMore.setScrollbarFadingEnabled(true);


        headerId = 0;

        footerId = lvMore.getHeaderViewsCount() + datas.size();


        justOnce = false;

        super.onResume();

    }

}

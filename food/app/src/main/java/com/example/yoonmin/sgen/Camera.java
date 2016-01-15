package com.example.yoonmin.sgen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Camera extends Activity {

    EditText Edit_Title, Edit_Substance;
    int Width, Height;
    Bitmap picture;
    Button Btn_Camera, Btn_Gallery;
    ImageView Img_View;
    int TAKE_CAMERA = 1;
    int TAKE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        setContentView(R.layout.activity_camera);

        Btn_Camera = (Button) findViewById(R.id.Btn_Camera);
        Edit_Title = (EditText) findViewById(R.id.Edit_Title);
        Edit_Substance = (EditText) findViewById(R.id.Edit_Substance);
        Btn_Gallery = (Button) findViewById(R.id.Btn_Gallery);
        Img_View = (ImageView) findViewById(R.id.Img_View);

        Btn_Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_CAMERA);

            }
        });

        Btn_Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/");
                startActivityForResult(intent, TAKE_GALLERY);
            }
        });

    }

    /*public void getDisplaySize(){

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Width = display.getWidth();
        Height = display.getHeight();

        Log.d("Size", "가로 길이는  " + String.valueOf(Width) + " 그리고 높이는 " + String.valueOf(Height));

    }*/

    private Bitmap imgRotate(Bitmap bitmap, int angle){

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.recycle();

        return resizedBitmap;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_CAMERA) {
                if (data != null) {
                    Log.e("Test", "result = " + data);
                    picture = (Bitmap) data.getExtras().get("data");
                    Uri thumbnail = data.getData();
                    if (picture != null) {
                        try{
                            Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), thumbnail);
                            bm = imgRotate(bm , 90);
                            Img_View.setImageBitmap(bm);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            else if(requestCode == TAKE_GALLERY){
                if( data != null )
                {
                    Log.e("Test", "result = " + data);

                    Uri thumbnail = data.getData();
                    if( thumbnail != null )
                    {
                        Img_View.setImageURI(thumbnail);
                    }
                }
            }
        }
    }
}

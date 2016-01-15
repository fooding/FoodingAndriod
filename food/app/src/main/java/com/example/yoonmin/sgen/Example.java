package com.example.yoonmin.sgen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.graphics.Bitmap.Config;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Environment;
import android.widget.Toast;

import com.clarifai.api.ClarifaiClient;
import com.clarifai.api.RecognitionRequest;
import com.clarifai.api.RecognitionResult;
import com.clarifai.api.Tag;
import com.clarifai.api.exception.ClarifaiException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static android.provider.MediaStore.Images.Media;

/**
 * Created by sanghyunjeon on 16. 1. 3..
 */


/**
 * A simple Activity that performs recognition using the Clarifai API.
 */
public class Example extends Activity {
    private static final String TAG = Example.class.getSimpleName();

    // IMPORTANT NOTE: you should replace these keys with your own App ID and secret.
    // These can be obtained at https://developer.clarifai.com/applications
    private static final String APP_ID = "N2gZQW_IqRAoTrQjpIm4oi2d17HGrSgG0xw5E3ok";
    private static final String APP_SECRET = "tklKPgiNudBUiAWhpplUDuwJT_Cy_f2JgBi9fNtv";

    private static final int CODE_PICK = 1;
    private static final int TAKE_PICTURE=2;

    private final ClarifaiClient client = new ClarifaiClient(APP_ID, APP_SECRET);
    private Button selectButton,takeButton,okButton, Btn_Add;
    private ImageView imageView;
    private TableLayout table;
    private CheckBox check;
    private TableRow newRow;
    private int Count = 0;

    private String filePath;
    private EditText Edit_tag_Add;
    private String folderName = "Arcanelux";// 폴더명
    private String fileName = "CameraIntent"; // 파일명

    private ArrayList<CheckBox> taglist = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_clarifai);
        imageView = (ImageView) findViewById(R.id.image_view);
        table = (TableLayout) findViewById(R.id.table);
        takeButton=(Button) findViewById(R.id.take_button);
        selectButton = (Button) findViewById(R.id.select_button);
        Edit_tag_Add = (EditText) findViewById(R.id.Edit_Tag_Add);
        Btn_Add = (Button) findViewById(R.id.Btn_Add);
        okButton=(Button) findViewById(R.id.OK);

        Btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check = new CheckBox(getApplicationContext());
                newRow = new TableRow(getApplicationContext());

                String tag = String.valueOf(Edit_tag_Add.getText());
                check.setText(tag);
                check.setTextColor(getResources().getColor(R.color.colorPrimary));
                check.setId(Count);
                check.setChecked(true);
                newRow.addView(check);
                table.addView(newRow);

                taglist.add(check);

                Count++;
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tags ="";

                for(int i = 0;i<taglist.size();i++)
                {
                    if(taglist.get(i).isChecked())
                        tags += ("#" + taglist.get(i).getText() );

                }

                Log.d("checkedTAG",tags);
/*
                Intent intent = new Intent(getApplicationContext(),Write.class);
                startActivity(intent);
*/
            }
        });
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send an intent to launch the media picker.
                final Intent intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CODE_PICK);
            }
        });

        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();


                Camera camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();
                List<Size> sizeList = parameters.getSupportedPictureSizes();
                // 카메라 SupportedPictureSize목록 출력 로그
                // for(int i=0; i<sizeList.size(); i++){
                // Size size = sizeList.get(i);
                //	Log.d(TAG, "Width : " + size.width + ", Height : " + size.height);
                // }
                // 원하는 최적화 사이즈를 1280x720 으로 설정
                Camera.Size size =  getOptimalPictureSize(parameters.getSupportedPictureSizes(), 1280, 720);
                Log.d(TAG, "Selected Optimal Size : (" + size.width + ", " + size.height + ")");
                parameters.setPreviewSize(size.width,  size.height);
                parameters.setPictureSize(size.width,  size.height);
                camera.setParameters(parameters);
                camera.release();

                // 저장할 파일 설정
                // 외부저장소 경로
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();

                // 폴더명 및 파일명
                String folderPath = path + File.separator + folderName;
                filePath = path + File.separator + folderName + File.separator +  fileName + ".jpg";

                // 저장 폴더 지정 및 폴더 생성
                File fileFolderPath = new File(folderPath);
                fileFolderPath.mkdir();

                // 파일 이름 지정
                File file = new File(filePath);
                Uri outputFileUri = Uri.fromFile(file);


                // 카메라 작동시키는 Action으로 인텐트 설정, OutputFileURI 추가
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
                // requestCode지정해서 인텐트 실행

                startActivityForResult(intent, TAKE_PICTURE);

            }
        });







    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CODE_PICK && resultCode == RESULT_OK) {
            // The user picked an image. Send it to Clarifai for recognition.
            Log.d(TAG, "User picked image: " + intent.getData());
            Bitmap bitmap = loadBitmapFromUri(intent.getData());
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Recognizing...", Toast.LENGTH_SHORT).show();
                selectButton.setEnabled(false);
                // Run recognition on a background thread since it makes a network call.
                new AsyncTask<Bitmap, Void, RecognitionResult>() {
                    @Override protected RecognitionResult doInBackground(Bitmap... bitmaps) {
                        return recognizeBitmap(bitmaps[0]);
                    }
                    @Override protected void onPostExecute(RecognitionResult result) {
                        updateUIForResult(result);
                    }
                }.execute(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "Unable to load selected image.", Toast.LENGTH_SHORT).show();
            }
        }

        else if(requestCode == TAKE_PICTURE && resultCode == RESULT_OK){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Config.RGB_565;
            Bitmap bm = BitmapFactory.decodeFile(filePath, options);
            imageView.setImageBitmap(bm);
            Toast.makeText(getApplicationContext(), "Recognizing...", Toast.LENGTH_SHORT).show();
            selectButton.setEnabled(false);

            new AsyncTask<Bitmap, Void, RecognitionResult>() {
                @Override protected RecognitionResult doInBackground(Bitmap... bitmaps) {
                    return recognizeBitmap(bitmaps[0]);
                }
                @Override protected void onPostExecute(RecognitionResult result) {
                    updateUIForResult(result);
                }
            }.execute(bm);

        }
    }

    /** Loads a Bitmap from a content URI returned by the media picker. */
    private Bitmap loadBitmapFromUri(Uri uri) {
        try {
            // The image may be large. Load an image that is sized for display. This follows best
            // practices from http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);


            int sampleSize = 1;

            while (opts.outWidth / (2 * sampleSize) >= imageView.getWidth() &&
                    opts.outHeight / (2 * sampleSize) >= imageView.getHeight()) {
                sampleSize *= 2;
            }

            opts = new BitmapFactory.Options();
            opts.inSampleSize = sampleSize;


            return BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, opts);
        } catch (IOException e) {
            Log.e(TAG, "Error loading image: " + uri, e);
        }
        return null;
    }

    /** Sends the given bitmap to Clarifai for recognition and returns the result. */
    private RecognitionResult recognizeBitmap(Bitmap bitmap) {
        try {
            // Scale down the image. This step is optional. However, sending large images over the
            // network is slow and  does not significantly improve recognition performance.

            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 320,
                    320 * bitmap.getHeight() / bitmap.getWidth(), true);

            // Compress the image as a JPEG.
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            scaled.compress(Bitmap.CompressFormat.JPEG, 90, out);
            byte[] jpeg = out.toByteArray();

            // Send the JPEG to Clarifai and return the result.
            return client.recognize(new RecognitionRequest(jpeg)).get(0);
        } catch (ClarifaiException e) {
            Log.e(TAG, "Clarifai error", e);
            return null;
        }
    }

    /** Updates the UI by displaying tags for the given result. */
    private void updateUIForResult(RecognitionResult result) {
        if (result != null) {
            if (result.getStatusCode() == RecognitionResult.StatusCode.OK) {
                // Display the list of tags in the UI.
                addRadioitem(result);
            } else {
                Log.e(TAG, "Clarifai: " + result.getStatusMessage());
                Toast.makeText(getApplicationContext(), "Sorry, there was an error recognizing your image.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Sorry, there was an error recognizing your image.", Toast.LENGTH_SHORT).show();
        }
        selectButton.setEnabled(true);
    }

    private Size getOptimalPictureSize(List<Size> sizeList, int width, int height){
        Log.d(TAG, "getOptimalPictureSize, 기준 width,height : (" + width + ", " + height + ")");
        Size prevSize = sizeList.get(0);
        Size optSize = sizeList.get(1);
        for(Size size : sizeList){
            // 현재 사이즈와 원하는 사이즈의 차이
            int diffWidth = Math.abs((size.width - width));
            int diffHeight = Math.abs((size.height - height));

            // 이전 사이즈와 원하는 사이즈의 차이
            int diffWidthPrev = Math.abs((prevSize.width - width));
            int diffHeightPrev = Math.abs((prevSize.height - height));

            // 현재까지 최적화 사이즈와 원하는 사이즈의 차이
            int diffWidthOpt = Math.abs((optSize.width - width));
            int diffHeightOpt = Math.abs((optSize.height - height));

            // 이전 사이즈보다 현재 사이즈의 가로사이즈 차이가 적을 경우 && 현재까지 최적화 된 세로높이 차이보다 현재 세로높이 차이가 적거나 같을 경우에만 적용
            if(diffWidth < diffWidthPrev && diffHeight <= diffHeightOpt){
                optSize = size;
                Log.d(TAG, "가로사이즈 변경 / 기존 가로사이즈 : " + prevSize.width + ", 새 가로사이즈 : " + optSize.width);
            }
            // 이전 사이즈보다 현재 사이즈의 세로사이즈 차이가 적을 경우 && 현재까지 최적화 된 가로길이 차이보다 현재 가로길이 차이가 적거나 같을 경우에만 적용
            if(diffHeight < diffHeightPrev && diffWidth <= diffWidthOpt){
                optSize = size;
                Log.d(TAG, "세로사이즈 변경 / 기존 세로사이즈 : " + prevSize.height + ", 새 세로사이즈 : " + optSize.height);
            }

            // 현재까지 사용한 사이즈를 이전 사이즈로 지정
            prevSize = size;
        }
        Log.d(TAG, "결과 OptimalPictureSize : " + optSize.width + ", " + optSize.height);
        return optSize;
    }

    private void addRadioitem(RecognitionResult result){
        StringBuilder b = new StringBuilder();
        for (Tag tag : result.getTags()) {

            newRow = new TableRow(this);
            check = new CheckBox(this);

            check.setId(Count);
            check.setText(tag.getName());

//            radioButton[Count] = radio;
//            radioButton[Count].setId(Count);
//            radioButton[Count].setText(tag.getName());

            Log.d("id", String.valueOf(check.getId()));
            Log.d("tag", tag.getName());

            newRow.addView(check);
            table.addView(newRow);

            taglist.add(check);
            Count++;
        }

    }
}
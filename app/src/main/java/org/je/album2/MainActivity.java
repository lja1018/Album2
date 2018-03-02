package org.je.album2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.je.album2.NotUsed.ImgAdapter;
import org.je.album2.Util.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int EXTERNAL_STORAGE_PERMISSIONS = 12;

    private List<String> buckets;
    private List<String> bIDs;
    private List<String> uris_String;
    private List<Uri> uris;
    //private int[] count = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (PermissionUtils.isStoragePermissionsGranted(this)) {
            init();
        } else {
            PermissionUtils.requestPermissions(this, EXTERNAL_STORAGE_PERMISSIONS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.my_main_toolbar);
        mainToolbar.setTitle("Simple Album");
        setSupportActionBar(mainToolbar);
        mainToolbar.getBackground().setAlpha(0);
        //setSupportActionBar(mainToolbar);



    }

    public List<Uri> fetchAllImages() {
        // DATA는 이미지 파일의 스트림 데이터 경로를 나타냅니다.
        String tmp = "";
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID
        };

        Cursor imageCursor = getBaseContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA를 출력
                null,       // 모든 개체 출력
                null,
                null);      // 정렬 안 함

        ArrayList<Uri> result = new ArrayList<>(imageCursor.getCount());
        buckets = new ArrayList<>(imageCursor.getCount());
        bIDs = new ArrayList<>(imageCursor.getCount());

        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
        int bnColumnIndex = imageCursor.getColumnIndex(projection[1]);
        int bucketID = imageCursor.getColumnIndex(projection[2]);

        int i = -1;
        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                String bucket = imageCursor.getString(bnColumnIndex);
                String bID = imageCursor.getString(bucketID);

                if (!tmp.equals(bID)) {
                    tmp = bID;
                    i++;
                }
                //count[i]++;

                Uri imageUri = Uri.parse(filePath);
                result.add(imageUri);
                buckets.add(bucket);
                bIDs.add(bID);
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }
        imageCursor.close();

        return result;
    }

    public void init() {

        if (PermissionUtils.isStoragePermissionsGranted(this)) {
            query();

            /**
            ImgAdapter adapter = new ImgAdapter(this, uris);
            GridView gv = (GridView) findViewById(R.id.gridview);
            gv.setAdapter(adapter);
             **/

            List<String> t_bName = new ArrayList<String>();
            List<Uri> t_uris = new ArrayList<Uri>();

            String temp = new String();
            for (int j = 0; j < buckets.size(); j++) {
                if (j == 0) {
                    temp = buckets.get(j);
                    t_bName.add(buckets.get(j));
                    t_uris.add(uris.get(j));
                }

                if (temp.equals(buckets.get(j))) { continue; }
                else {
                    temp = buckets.get(j);
                    t_bName.add(buckets.get(j));
                    t_uris.add(uris.get(j));
                }
            }

            uris_String = new ArrayList<String>();
            for (int i = 0; i < t_uris.size(); i++) {
                uris_String.add(t_uris.get(i).toString());
            }

            Intent intent = new Intent(this, FolderViewActivity.class);
            //intent.putStringArrayListExtra("BucketName", (ArrayList<String>) t_bName);
            //intent.putStringArrayListExtra("ImageURIs", (ArrayList<String>) uris_String);

            this.startActivity(intent);

            //ImageView imgView = (ImageView) findViewById(R.id.imageView3);
            //imgView.setImageURI(Uri.parse(uris.get(0).toString()));
        } else {
            PermissionUtils.requestPermissions(this, EXTERNAL_STORAGE_PERMISSIONS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    public void query() {
        uris = fetchAllImages();
    }
}
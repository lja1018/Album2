package org.je.album2.Util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JE on 2018-02-23(023).
 */

public class FetchImageUtils {
    public static int get_IDcount(Context context, String ID) {
        String[] mClause = { "" };
        mClause[0] = ID;
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_ID
        };

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Images.Media.BUCKET_ID + " = ?",
                mClause,
                null);

        return cursor.getCount();
    }

    public static List<List<String>> get_Buckets (Context context) {
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID
        };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA를 출력
                null,       // 모든 개체 출력
                null,
                MediaStore.Images.Media.BUCKET_ID);      // ID로 정렬

        ArrayList<String> filePaths = new ArrayList<String>(imageCursor.getCount());
        ArrayList<String> buckets = new ArrayList<String>(imageCursor.getCount());
        ArrayList<String> bucket_ID = new ArrayList<String>(imageCursor.getCount());

        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
        int bnColumnIndex = imageCursor.getColumnIndex(projection[1]);
        int bIDColumnIndex = imageCursor.getColumnIndex(projection[2]);

        int i = -1;
        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                String bucket = imageCursor.getString(bnColumnIndex);
                String bID = imageCursor.getString(bIDColumnIndex);

                filePaths.add(filePath);
                buckets.add(bucket);
                bucket_ID.add(bID);
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }
        imageCursor.close();

        ArrayList<List<String>> result = new ArrayList<>(3);
        result.add(filePaths);
        result.add(buckets);
        result.add(bucket_ID);

        return result;
    }

    public static List<List<String>> get_DistinctBuckets (Context context) {
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID
        };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA를 출력
                null,       // 모든 개체 출력
                null,
                MediaStore.Images.Media.BUCKET_ID);      // ID로 정렬

        ArrayList<String> filePaths = new ArrayList<String>(imageCursor.getCount());
        ArrayList<String> buckets = new ArrayList<String>(imageCursor.getCount());
        ArrayList<String> bucketID = new ArrayList<String>(imageCursor.getCount());

        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
        int bnColumnIndex = imageCursor.getColumnIndex(projection[1]);
        int bIDColumnIndex = imageCursor.getColumnIndex(projection[2]);

        int i = -1;
        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                String bucket = imageCursor.getString(bnColumnIndex);
                String bID = imageCursor.getString(bIDColumnIndex);

                filePaths.add(filePath);
                buckets.add(bucket);
                bucketID.add(bID);
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }
        imageCursor.close();

        ArrayList<String> distinct_filePaths = new ArrayList<String>();
        ArrayList<String> distinct_buckets = new ArrayList<String>();
        ArrayList<String> distinct_bucketsID = new ArrayList<String>();

        String temp = new String();
        for (int j = 0; j < bucketID.size(); j++) {
            if (j == 0) {
                temp = bucketID.get(j);
                distinct_filePaths.add(filePaths.get(j));
                distinct_buckets.add(buckets.get(j));
                distinct_bucketsID.add(bucketID.get(j));
            }

            if (temp.equals(bucketID.get(j))) { continue; }
            else {
                temp = bucketID.get(j);
                distinct_filePaths.add(filePaths.get(j));
                distinct_buckets.add(buckets.get(j));
                distinct_bucketsID.add(bucketID.get(j));
            }
        }

        ArrayList<List<String>> result = new ArrayList<>(3);
        result.add(distinct_filePaths);
        result.add(distinct_buckets);
        result.add(distinct_bucketsID);

        return result;
    }

    public static List<Uri> get_Content (Context context, String ID) {
        String[] mClause = { "" };
        mClause[0] = ID;
        String[] projection = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_ID
        };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                MediaStore.Images.Media.BUCKET_ID + " = ?",
                mClause,
                null);

        ArrayList<Uri> result = new ArrayList<Uri>(imageCursor.getCount());
        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);

        int i = -1;
        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                result.add(Uri.parse(filePath));
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }
        imageCursor.close();

        return result;
    }
}

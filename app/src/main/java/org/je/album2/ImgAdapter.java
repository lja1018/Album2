package org.je.album2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by JE on 2018-02-07(007).
 */

public class ImgAdapter extends BaseAdapter {
    Context context = null;
    List<Uri> imgList = null;

    ImgAdapter(Context context, List<Uri> uris) {
        this.context = context;
        this.imgList = uris;
    }

    public int getCount() {
        return imgList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv = null;
        Bitmap bmp = null;
        boolean flag = false;

        if (convertView == null) {
            bmp = BitmapFactory.decodeFile(imgList.get(position).toString());
            int dimension = getSquareCropDimensionForBitmap(bmp);
            bmp = ThumbnailUtils.extractThumbnail(bmp, dimension, dimension);

            iv = new ImageView(context);
            iv.setAdjustViewBounds(true);
            iv.setImageBitmap(bmp);
            iv.setPadding(10, 10, 10, 10);
            flag = true;
        }
        else {
            iv = (ImageView)convertView;
        }

        if (flag == true) {
            iv.setAdjustViewBounds(true);
            iv.setImageBitmap(bmp);
        } else {
            iv.setImageURI(Uri.parse(imgList.get(position).toString()));
        }

        ImageClickListener imageViewClickListener
                = new ImageClickListener(context, imgList.get(position));
        iv.setOnClickListener(imageViewClickListener);

        return iv;
    }

    public int getSquareCropDimensionForBitmap(Bitmap bitmap) {
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }
}
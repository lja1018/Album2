package org.je.album2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.je.album2.Util.FetchImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JE on 2018-02-19(019).
 */

public class FolderRecyclerAdapter extends RecyclerView.Adapter<FolderRecyclerAdapter.ViewHolder> {
    Context context;
    List<String> bucket_Name;
    List<String> bucket_IDs;
    List<Uri> imgList;
    int item_layout;

    FolderRecyclerAdapter(Context context, List<String> bName, List<String> bIDs, List<Uri> uris, int item_layout) {
        this.context = context;
        this.bucket_Name = bName;
        bucket_IDs = bIDs;
        this.imgList = uris;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_cardview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FolderRecyclerAdapter.ViewHolder holder, int position) {
        Bitmap bmp = BitmapFactory.decodeFile(imgList.get(position).toString());
        bmp = ThumbnailUtils.extractThumbnail(bmp, 600, 600);
        int dimension = getSquareCropDimensionForBitmap(bmp);
        Drawable d = new BitmapDrawable(null, bmp);
        //Drawable drawable = Drawable.createFromPath(imgList.get(position).toString());
        holder.image.setImageDrawable(d);
        holder.title.setText(bucket_Name.get(position) + " (" + FetchImageUtils.get_IDcount(context, bucket_IDs.get(position)) + ")");
        ClickListener cl = new ClickListener(context, bucket_IDs.get(position));
        holder.cardView.setOnClickListener(cl);
    }

    @Override
    public int getItemCount() {
        return this.bucket_Name.size();
    }

    public class ClickListener implements View.OnClickListener {
        Context c;
        String bID;

        public ClickListener(Context t, String tbID) {
            c = t;
            bID = tbID;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(c, ContentViewActivity.class);
            intent.putExtra("BucketID", bID);
            c.startActivity(intent);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.folderCover);
            title = (TextView) itemView.findViewById(R.id.folderName);
            cardView = (CardView) itemView.findViewById(R.id.folder_cardView);
        }
    }

    public int getSquareCropDimensionForBitmap(Bitmap bitmap) {
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }
}

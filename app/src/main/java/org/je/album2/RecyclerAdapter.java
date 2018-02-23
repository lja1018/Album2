package org.je.album2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.je.album2.Util.FetchImageUtils;

import java.util.List;

/**
 * Created by JE on 2018-02-19(019).
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<String> bucket_Name;
    List<String> bucket_IDs;
    List<Uri> imgList;
    int item_layout;

    RecyclerAdapter(Context context, List<String> bName, List<String> bIDs, List<Uri> uris, int item_layout) {
        this.context = context;
        this.bucket_Name = bName;
        bucket_IDs =bIDs;
        this.imgList = uris;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_cardview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Drawable drawable = Drawable.createFromPath(imgList.get(position).toString());
        holder.image.setImageDrawable(drawable);
        holder.title.setText(bucket_Name.get(position) + " " + FetchImageUtils.get_IDcount(context, bucket_IDs.get(position)));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, bucket_IDs.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.bucket_Name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.folderCover);
            title = (TextView) itemView.findViewById(R.id.folderName);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }

    public int getSquareCropDimensionForBitmap(Bitmap bitmap) {
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }
}

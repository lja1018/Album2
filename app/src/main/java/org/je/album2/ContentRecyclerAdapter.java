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

import java.util.List;

/**
 * Created by JE on 2018-03-02(002).
 */

public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.ViewHolder> {
    Context context;
    List<Uri> imgList;
    int item_layout;

    ContentRecyclerAdapter(Context context, List<Uri> uris, int item_layout) {
        this.context = context;
        this.imgList = uris;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_cardview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drawable drawable = Drawable.createFromPath(imgList.get(position).toString());
        holder.image.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return this.imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.contentCover);
            cardView = (CardView) itemView.findViewById(R.id.content_cardView);
        }
    }

    public int getSquareCropDimensionForBitmap(Bitmap bitmap) {
        return Math.min(bitmap.getWidth(), bitmap.getHeight());
    }
}
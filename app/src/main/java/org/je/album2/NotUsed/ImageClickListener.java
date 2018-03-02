package org.je.album2.NotUsed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

/**
 * Created by JE on 2018-02-08(008).
 */

public class ImageClickListener implements View.OnClickListener {

    Context context;
    Uri img = null;
    int po = 0;

    ImageClickListener(Context context, Uri uris) {
        this.context = context;
        this.img = uris;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra("image ID", img);
        context.startActivity(intent);
    }
}

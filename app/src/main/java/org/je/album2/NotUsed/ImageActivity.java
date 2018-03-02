package org.je.album2.NotUsed;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.je.album2.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        setImage(imageView);
    }

    private void setImage(ImageView imageView) {
        Intent receivedIntent = getIntent();

        Uri uri = (Uri)receivedIntent.getExtras().get("image ID");
        imageView.setImageURI(uri);
    }
}

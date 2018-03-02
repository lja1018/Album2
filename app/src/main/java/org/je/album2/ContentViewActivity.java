package org.je.album2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.je.album2.Util.FetchImageUtils;

import java.util.List;

public class ContentViewActivity extends AppCompatActivity {
    private String bID;
    private List<Uri> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentview);

        parse_Intent();
        build_Content();
    }

    private void parse_Intent() {
        //bucket_Names = getIntent().getStringArrayListExtra("BucketName");
        //uri_String = getIntent().getStringArrayListExtra("ImageURIs");

        bID = getIntent().getStringExtra("BucketID");

        contents = FetchImageUtils.get_Content(getBaseContext(), bID);
    }

    private void build_Content() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.content_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ContentRecyclerAdapter(getApplicationContext(), contents, R.layout.activity_contentview));
    }

}

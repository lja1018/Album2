package org.je.album2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class FolderViewActivity extends AppCompatActivity {

    RecyclerAdapter rcAdapter;
    GridView gv;

    private List<String> bucket_Names;
    private List<String> uri_Names;
    private List<Uri> uris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folderview);

        parse_intent();
        buildContent();
    }

    private void parse_intent() {
        bucket_Names = getIntent().getStringArrayListExtra("BucketName");
        uri_Names = getIntent().getStringArrayListExtra("ImageUris");
        uris = new ArrayList<Uri>();
        for (int i = 0; i < uri_Names.size(); i++) {
            uris.add(i, Uri.parse(uri_Names.get(i)));
        }

        /**
        for (int i = 0; i < bucket_Names.size(); i++) {
            Log.println(Log.DEBUG, "SHOW_FILES_NAME", "Folder:\"" + bucket_Names.get(i).toString() + "\", Path: \"" + uris.get(i).toString());
        }
         **/
    }

    private void buildContent() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), bucket_Names, uris, R.layout.activity_folderview));
        Log.println(Log.DEBUG, "FolderViewActivity.java", "This is FolderViewActivity.java");
    }
}

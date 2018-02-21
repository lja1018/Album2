package org.je.album2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
    private List<String> uri_String;
    private List<Uri> uris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folderview);

        parse_Intent();
        build_Content();
    }

    private void parse_Intent() {
        bucket_Names = getIntent().getStringArrayListExtra("BucketName");
        uri_String = getIntent().getStringArrayListExtra("ImageURIs");
        uris = new ArrayList<Uri>();
        for (int i = 0; i < uri_String.size(); i++) {
            uris.add(i, Uri.parse(uri_String.get(i)));
        }

        /**
        for (int i = 0; i < bucket_Names.size(); i++) {
            Log.println(Log.DEBUG, "SHOW_FILES_NAME", "Folder:\"" + bucket_Names.get(i).toString() + "\", Path: \"" + uris.get(i).toString());
        }
         **/
    }

    private void build_Content() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), bucket_Names, uris, R.layout.activity_folderview));
        Log.println(Log.DEBUG, "FolderViewActivity.java", "This is FolderViewActivity.java");
    }
}

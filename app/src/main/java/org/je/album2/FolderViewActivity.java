package org.je.album2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.je.album2.Util.FetchImageUtils;

import java.util.ArrayList;
import java.util.List;

public class FolderViewActivity extends AppCompatActivity {
    private List<Uri> uris;
    private List<String> bucket_Names;
    private List<String> bucket_IDs;
    private List<List<String>> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folderview);

        Toolbar folderToolbar = (Toolbar) findViewById(R.id.my_folder_toolbar);
        folderToolbar.setTitle("Simple Album");
        folderToolbar.getBackground().setAlpha(200);
        setSupportActionBar(folderToolbar);

        parse_Intent();
        build_Content();
    }

    private void parse_Intent() {
        //bucket_Names = getIntent().getStringArrayListExtra("BucketName");
        //uri_String = getIntent().getStringArrayListExtra("ImageURIs");

        result = FetchImageUtils.get_DistinctBuckets(getBaseContext());

        List<String> filePaths = result.get(0);
        bucket_Names = result.get(1);
        bucket_IDs = result.get(2);

        uris = new ArrayList<Uri>();
        for (int i = 0; i < filePaths.size(); i++) {
            uris.add(Uri.parse(filePaths.get(i)));
        }
    }

    private void build_Content() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.folder_recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new FolderRecyclerAdapter(getApplicationContext(), bucket_Names, bucket_IDs, uris, R.layout.activity_folderview));
    }
}

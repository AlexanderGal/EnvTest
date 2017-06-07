package com.examples.android.homework.filemanager.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.examples.android.homework.filemanager.R;
import com.examples.android.homework.filemanager.adapter.FileRecyclerViewAdapter;

public class FolderListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FileRecyclerViewAdapter mFileRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.file_recyclerview);
        mFileRecyclerViewAdapter = new FileRecyclerViewAdapter();
        mRecyclerView.setAdapter(mFileRecyclerViewAdapter);

        mFileRecyclerViewAdapter.setFiles(Environment.getRootDirectory().listFiles());
    }
}

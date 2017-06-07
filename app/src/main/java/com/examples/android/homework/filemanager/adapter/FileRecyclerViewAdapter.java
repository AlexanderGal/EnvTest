package com.examples.android.homework.filemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.android.homework.filemanager.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<File> mFiles;

    public FileRecyclerViewAdapter() {
        mFiles = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_file_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final File file = mFiles.get(position);
        ((ViewHolder) holder).fillViewHolder(file.getName(),
                file.isDirectory() ? R.drawable.folder_outline
                        : R.drawable.file_outline);
        ((ViewHolder) holder).mFileIcon.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = ((ViewHolder) holder).mFileIcon.getContext();
                if (file.isDirectory()) {
                    File files = new File(file.getAbsolutePath());
                    setFiles(files.listFiles());
                } else {
                    Toast.makeText(context, context.getString(R.string.cant_open) + " " + file.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setFiles(File[] files) {
        mFiles = new ArrayList<>(Arrays.asList(files));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mFileIcon;
        TextView mFileName;

        ViewHolder(View itemView) {
            super(itemView);
            mFileIcon = (ImageView) itemView.findViewById(R.id.file_imageview);
            mFileName = (TextView) itemView.findViewById(R.id.filename_textview);
        }

        void fillViewHolder(String fileName, int iconId) {
            mFileName.setText(fileName);
            mFileIcon.setImageResource(iconId);
        }
    }
}

package com.example.tutor_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutor_app.R;
import com.jaiselrahman.filepicker.model.MediaFile;

import java.util.ArrayList;

public class MultipleFilesAdapter extends RecyclerView.Adapter<MultipleFilesAdapter.ViewHolder> {
    private ArrayList<MediaFile> mediaFiles;

    public MultipleFilesAdapter(ArrayList<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.file_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaFile mediaFile = mediaFiles.get(position);
        Context context = holder.itemView.getContext();
        holder.filePath.setText(context.getString(R.string.uri, mediaFile.getUri()));
        holder.fileMime.setText(context.getString(R.string.mime, mediaFile.getMimeType()));
        holder.fileSize.setText(context.getString(R.string.size, mediaFile.getSize()));
        holder.fileBucketName.setText(context.getString(R.string.bucketname, mediaFile.getBucketName()));
        holder.fileThumbnail.setImageResource(R.drawable.ic_file);
//        if (mediaFile.getMediaType() == MediaFile.TYPE_FILE
//                || mediaFile.getMediaType() == MediaFile.TYPE_VIDEO) {
//            Glide.with(context)
//                    .load(mediaFile.getUri())
//                    .into(holder.fileThumbnail);
//        } else if (mediaFile.getMediaType() == MediaFile.TYPE_AUDIO) {
//            Glide.with(context)
//                    .load(mediaFile.getThumbnail())
//                    .placeholder(R.drawable.ic_audio)
//                    .into(holder.fileThumbnail);
//        } else {
//            holder.fileThumbnail.setImageResource(R.drawable.ic_file);
//        }
    }

    @Override
    public int getItemCount() {
        return mediaFiles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fileThumbnail;
        private TextView filePath, fileSize, fileMime, fileBucketName;

        ViewHolder(View view) {
            super(view);
            fileThumbnail = view.findViewById(R.id.file_thumbnail);
            filePath = view.findViewById(R.id.file_path);
            fileSize = view.findViewById(R.id.file_size);
            fileMime = view.findViewById(R.id.file_mime);
            fileBucketName = view.findViewById(R.id.file_bucketname);
        }
    }
}
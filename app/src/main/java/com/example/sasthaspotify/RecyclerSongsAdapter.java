package com.example.sasthaspotify;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.IOException;
import java.util.List;

public class RecyclerSongsAdapter extends RecyclerView.Adapter<RecyclerSongsAdapter.ViewHolder> {
    private Context context;
    private List<Result> results;
    private MediaPlayer mediaPlayer;
    public RecyclerSongsAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
        mediaPlayer = new MediaPlayer(); // Initialize MediaPlayer
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songs_row, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.txtTitle.setText(result.getTitle());
        Glide.with(context)
                .load(result.getImage())
                .error(R.drawable.music)
                .into(holder.imgMusic);
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mediaPlayer.reset(); // Reset MediaPlayer before playing new song
                    mediaPlayer.setDataSource(result.getPreviewUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgMusic;
        ImageButton play, pause;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtName);
            imgMusic = itemView.findViewById(R.id.imgSongs);
            play = itemView.findViewById(R.id.play);
            pause = itemView.findViewById(R.id.pause);
        }
    }
}

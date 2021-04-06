package com.example.listatarefas.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listatarefas.R;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsAdapterVH>{

    private List<Albums> albumsList;
    private Context context;
    private AlbumsAdapter.ClickedItem clickedItem;

    public AlbumsAdapter(AlbumsAdapter.ClickedItem clickedItem) {

        this.clickedItem = clickedItem;
    }

    public void setData(List<Albums> albumsList) {
        this.albumsList = albumsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new AlbumsAdapter.AlbumsAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_albums, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsAdapterVH holder, int position) {

        Albums albums = albumsList.get(position);

        int albumsId = albums.getId();
        holder.albumsId.setText(Integer.toString(albumsId));
        holder.imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickedItem.ClickedAlbums(albums);
            }
        });
    }

    public interface ClickedItem{
        public void ClickedAlbums(Albums albums);

    }

    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public class AlbumsAdapterVH extends RecyclerView.ViewHolder {

        TextView albumsId;
        TextView prefix;
        ImageView imageNext;
        public AlbumsAdapterVH(@NonNull View itemView) {
            super(itemView);

            albumsId = itemView.findViewById(R.id.albumsId);
            prefix = itemView.findViewById(R.id.prefix);
            imageNext = itemView.findViewById(R.id.imageNext);
        }
    }


}

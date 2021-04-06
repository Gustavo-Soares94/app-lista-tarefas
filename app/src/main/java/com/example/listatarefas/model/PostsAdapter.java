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

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsAdapterVH> {

    private List<Posts> postsList;
    private Context context;
    private ClickedItem clickedItem;


    public PostsAdapter(ClickedItem clickedItem) {

        this.clickedItem = clickedItem;
    }

    public void setData(List<Posts> postsList) {
        this.postsList = postsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsAdapter.PostsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new PostsAdapter.PostsAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_posts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostsAdapterVH holder, int position) {

        Posts posts = postsList.get(position);

        int postsId = posts.getId();
        holder.postsId.setText(Integer.toString(postsId));
        holder.imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickedItem.ClickedPosts(posts);
            }
        });
    }

    public interface ClickedItem{
        public void ClickedPosts(Posts posts);

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class PostsAdapterVH extends RecyclerView.ViewHolder {

        TextView postsId;
        TextView prefix;
        ImageView imageNext;
        public PostsAdapterVH(@NonNull View itemView) {
            super(itemView);

            postsId = itemView.findViewById(R.id.postsId);
            prefix = itemView.findViewById(R.id.prefix);
            imageNext = itemView.findViewById(R.id.imageNext);
        }
    }

}


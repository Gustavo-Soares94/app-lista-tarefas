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

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsAdapterVH> {

    private List<Comments> commentsList;
    private Context context;
    private ClickedItem clickedItem;

    public CommentsAdapter(ClickedItem clickedItem){

        this.clickedItem = clickedItem;

    }

    public void setData(List<Comments> commentsList){
        this.commentsList = commentsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentsAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CommentsAdapter.CommentsAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_comments,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapterVH holder, int position) {

        Comments comments = commentsList.get(position);

        String commentsEmail = comments.getEmail();
        holder.commentsEmail.setText(commentsEmail);
        holder.imageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickedItem.ClickedComments(comments);
            }
        });


    }

    public interface ClickedItem{
        public void ClickedComments(Comments comments);

    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class CommentsAdapterVH extends RecyclerView.ViewHolder {

        TextView commentsEmail;
        TextView prefix;
        ImageView imageNext;
        public CommentsAdapterVH(@NonNull View itemView) {
            super(itemView);

            commentsEmail = itemView.findViewById(R.id.commentsEmail);
            prefix = itemView.findViewById(R.id.prefix);
            imageNext = itemView.findViewById(R.id.imageNext);
        }
    }
}

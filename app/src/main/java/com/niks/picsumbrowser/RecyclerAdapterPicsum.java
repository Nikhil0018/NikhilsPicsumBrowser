package com.niks.picsumbrowser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapterPicsum extends RecyclerView.Adapter<RecyclerAdapterPicsum.myViewHolder> {
    List<ItemPicsum> itemsList;
    Context context;
    @NonNull
    @Override
    public RecyclerAdapterPicsum.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.picsumitem, parent, false);

        return new RecyclerAdapterPicsum.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterPicsum.myViewHolder holder, int position) {
        final ItemPicsum item = itemsList.get(position);
        holder.authortext.setText(item.getAuthor());
        Glide.with(context).load(item.getImgurl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    RecyclerAdapterPicsum(Context context,List<ItemPicsum> itemsList){
        this.itemsList = itemsList;
        this.context = context;
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView authortext;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgview);
            authortext=itemView.findViewById(R.id.author);

        }
    }

}


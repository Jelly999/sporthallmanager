package com.example.ht1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EnrollAdapter extends RecyclerView.Adapter {
    private ArrayList<item> mlist;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);


        }
    }
    public EnrollAdapter(ArrayList<item> list){
        mlist = list;
}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent, false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        item currentItem = mlist.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.SetText(currentItem.getImageResource());
        holder.mTextView2.SetText(currentItem.getImageResource());

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }
}

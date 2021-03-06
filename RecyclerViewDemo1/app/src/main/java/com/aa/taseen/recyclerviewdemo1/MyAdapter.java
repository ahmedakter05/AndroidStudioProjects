package com.aa.taseen.recyclerviewdemo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private String[] title, desc;
    private int[] images;

    private static ClickListener clickListener;

    public MyAdapter(Context context, String[] title, String[] desc, int[] images) {
        this.context = context;
        this.title = title;
        this.desc = desc;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.simplelayout, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.titleTextView.setText(title[i]);
        myViewHolder.descTextView.setText(desc[i]);
        myViewHolder.flagImageView.setImageResource(images[i]);

    }

    @Override
    public int getItemCount() {
        int len = title.length;
        //Toast.makeText(context, "Total: " + len, Toast.LENGTH_SHORT).show();
        return len;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView titleTextView, descTextView;
        private ImageView flagImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.textViewTitleId);
            descTextView = itemView.findViewById(R.id.textViewDescId);
            flagImageView = itemView.findViewById(R.id.imageViewId);

            //onclicklistener attachment
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return true;
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View view);
        void onItemLongClick(int position, View view);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        MyAdapter.clickListener = clickListener;

    }
}

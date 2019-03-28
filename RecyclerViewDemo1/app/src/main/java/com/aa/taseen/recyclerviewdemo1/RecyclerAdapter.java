
package com.aa.taseen.recyclerviewdemo1;

        import android.content.Context;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.squareup.picasso.Picasso;
        import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Blog> postList;

    private static ClickListener clickListener;

    public RecyclerAdapter(Context context, List<Blog> postList) {
        this.context = context;
        this.postList = postList;

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

        Blog blog = postList.get(i);
        //myViewHolder.titleTextView.setText(title[i]);
        myViewHolder.titleTextView.setText(blog.getTitle());
        myViewHolder.descTextView.setText(blog.getDesc());

        Glide.with(context).load(blog.getImage()).dontAnimate().into(myViewHolder.flagImageView);
        //Picasso.with(context).load(blog.getImageUrl()).into(myViewHolder.flagImageView);
        //myViewHolder.descTextView.setText(desc[i]);
        //myViewHolder.flagImageView.setImageResource(images[i]);

    }

    @Override
    public int getItemCount() {
        int len = postList.size();
        //Toast.makeText(context, "Total: " + len, Toast.LENGTH_SHORT).show();
        return len;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private TextView titleTextView, descTextView, imageViewUrl;
        ImageView flagImageView;

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
        RecyclerAdapter.clickListener = clickListener;

    }
}

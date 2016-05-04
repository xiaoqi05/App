package com.ps.app.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.app.R;
import com.squareup.picasso.Picasso;
import com.zjutkz.powerfulrecyclerview.listener.ItemTouchAdapter;

import java.util.Collections;
import java.util.List;

public class MyAssetRecentSearchRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchAdapter {

    private Context mContext;
    private List<Integer> datas;

    public MyAssetRecentSearchRecAdapter(Context mContext, List<Integer> datas){
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ac_recent_search_list_item,parent,false));

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MyViewHolder){
           // ((MyViewHolder) holder).setImage(datas.get(position));
           //((MyViewHolder) holder).tv_title.setText(datas.get(position));
            
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if(fromPosition < 0 || toPosition >= datas.size()){
            return;
        }
        Collections.swap(datas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onDismiss(int position) {
        if(position < 0 || position >= datas.size()){
            return;
        }
        datas.remove(position);
        notifyItemRemoved(position);
    }
    
   /* public void removeAllDatas(){
        for (int i=0;i<getItemCount();i++){
            datas.remove(i);
            notifyItemRemoved(i);
        }
        
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_icon;
        public TextView tv_title;
        private TextView tv_content;
        private ImageView iv_flag;

        public MyViewHolder(View itemView) {
            super(itemView);
          /*  iv_icon = (ImageView)itemView.findViewById(R.id.iv_message_icon);
            tv_content = (TextView)itemView.findViewById(R.id.tv_message_content);
            tv_title = (TextView)itemView.findViewById(R.id.tv_message_title);
            iv_flag = (ImageView)itemView.findViewById(R.id.iv_read_flag);*/
        }

        public void setImage(int idImage) {
            Picasso.with(iv_icon.getContext()).
                    load(R.drawable.suspect).
                    centerCrop().
                    resize(130,130).
                    into(iv_icon);
        }
    }
    
}
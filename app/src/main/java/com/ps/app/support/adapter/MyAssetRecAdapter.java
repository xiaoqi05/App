package com.ps.app.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.app.R;
import com.ps.app.support.Bean.AssetListBean.DataBean.ListBean;
import com.squareup.picasso.Picasso;
import com.zjutkz.powerfulrecyclerview.listener.ItemTouchAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MyAssetRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchAdapter {

    private Context mContext;
    private List<ListBean> datas;

    public MyAssetRecAdapter(Context mContext, List<ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fr_asset_list_item, parent, false));


        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            //((MyViewHolder) holder).setImage(datas.get(position).getSn());
            ((MyViewHolder) holder).tv_name.setText(datas.get(position).getMemberTo().getDisplayName());
           // String time = String.valueOf(datas.get(position).getEndTime());
            // try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(datas.get(position).getEndTime());
            Date date = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = simpleDateFormat.format(date);
            //Date dates = simpleDateFormat.parse(time);
            ((MyViewHolder) holder).tv_time.setText(time);
            //  } catch (ParseException e) {
            //      e.printStackTrace();
            //}
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < 0 || toPosition >= datas.size()) {
            return;
        }
        Collections.swap(datas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onDismiss(int position) {
        if (position < 0 || position >= datas.size()) {
            return;
        }
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv;
        public TextView tv_name;
        public TextView tv_time;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_message_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_item_time);
        }

        public void setImage(int idImage) {
            Picasso.with(iv.getContext()).
                    load(R.drawable.suspect).
                    centerCrop().
                    resize(130, 130).
                    into(iv);
        }
    }

}
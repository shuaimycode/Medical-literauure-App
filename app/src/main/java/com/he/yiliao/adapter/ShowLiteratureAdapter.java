package com.he.yiliao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.he.yiliao.LiteratureSQLite;
import com.he.yiliao.R;
import com.he.yiliao.bean.LiteratureObj;

import java.util.List;

/**
 * Created by JiaHe on 2023/3/1.
 */

public class ShowLiteratureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<LiteratureObj> mLiteratureList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private LiteratureSQLite sQlite;
    class ViewHolder extends RecyclerView.ViewHolder{
        ViewGroup literatureView;
        TextView topicView;
        TextView timeView;
        TextView authorView;
        public ViewHolder(@NonNull View view){
            super(view); //????
            this.literatureView = (ViewGroup) view.findViewById(R.id.rl_literature_container);
            this.topicView = (TextView) view.findViewById(R.id.tv_topic);
            this.timeView = (TextView) view.findViewById(R.id.tv_time);
            this.authorView = (TextView)view.findViewById(R.id.tv_author);
        }
    }
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
//    }
    public ShowLiteratureAdapter(Context context, List<LiteratureObj> EventList){
        this.mContext = context;
        this.mLiteratureList = EventList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
//给manager页面生成ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.literature_layout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        bindMyViewHolder((ViewHolder)holder, position);
    }

    private void bindMyViewHolder(ViewHolder holder, final int position) {
        final LiteratureObj literatureObj = mLiteratureList.get(position);
        sQlite = new LiteratureSQLite(mContext);
        holder.topicView.setText(literatureObj.getTopic());
        holder.authorView.setText(literatureObj.getAuthor());
        holder.timeView.setText(literatureObj.getTime());
    }

    public void removeData(int pos) {
        mLiteratureList.remove(pos);
        notifyItemRemoved(pos);
    }


    @Override
    public int getItemCount() {
        return mLiteratureList.size();
    }
}

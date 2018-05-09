package com.ptit.tranhoangminh.newsharefood.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ptit.tranhoangminh.newsharefood.R;
import com.ptit.tranhoangminh.newsharefood.models.CommentModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterComment  extends RecyclerView.Adapter<AdapterComment.ViewHolder>  {
    int layout;
    Context context;
    List<CommentModel> commentModelList;
    public AdapterComment(Context context,int layout, List<CommentModel> commentModelList)
    {
        this.context=context;
        this.layout=layout;
        this.commentModelList=commentModelList;
    }
    @NonNull
    @Override
    public AdapterComment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComment.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(commentModelList.size()>0 &&commentModelList.size()>2)
        {
            return 2;
        }else{
            return commentModelList.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView txtTieuDe,txtNoidung,txtDiem;
        public ViewHolder(View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.imgUser);
            txtTieuDe=itemView.findViewById(R.id.txtTieude);
            txtNoidung=itemView.findViewById(R.id.txtNoidung);
            txtDiem=itemView.findViewById(R.id.txtDiem1);

        }
    }
}

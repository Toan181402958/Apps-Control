package com.firebase.appesp32example1.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.appesp32example1.R;

import java.util.List;

public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListAdapter.ViewHolder>{
    private List<ApplicationList> mListApp;
    private Context context;

    public ApplicationListAdapter(List<ApplicationList> mListApp, Context context) {
        this.mListApp = mListApp;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApplicationList applicationList = mListApp.get(position);
        holder.tvName.setText(applicationList.getName());
        holder.tvStatus.setText(applicationList.getStatus());
        holder.tvTime.setText(applicationList.getTime());
        holder.imgApplication.setImageResource(applicationList.getImage());

    }

    @Override
    public int getItemCount() {
        return mListApp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView rcvApplication;
        ImageView imgApplication;
        TextView tvName, tvStatus, tvTime;
        SwitchCompat switchCompat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvApplication = itemView.findViewById(R.id.layout_item);
            imgApplication = itemView.findViewById(R.id.img_item_application);
            tvName = itemView.findViewById(R.id.tv_name_application_item);
            tvStatus = itemView.findViewById(R.id.tv_status_item);
            tvTime = itemView.findViewById(R.id.tv_time_item);
            switchCompat = itemView.findViewById(R.id.switch_compat_item);
        }
    }
}

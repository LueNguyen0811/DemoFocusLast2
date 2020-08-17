package com.example.demofocuslast.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demofocuslast.Interface.ItemOnClickListener;
import com.example.demofocuslast.Model.SettingModel;
import com.example.demofocuslast.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecycleViewSettingAdapter extends RecyclerView.Adapter<RecycleViewSettingAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SettingModel> settingModels;


    public RecycleViewSettingAdapter(Context context, ArrayList<SettingModel> settingModels) {
        this.context = context;
        this.settingModels = settingModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View settingView = layoutInflater.inflate(R.layout.custom_layout_setting_recycle,parent,false);
        ViewHolder viewHolder = new ViewHolder(settingView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingModel settingModel = settingModels.get(position);

        holder.txtContent.setText(settingModel.getContent());
        holder.txtDecs.setText(settingModel.getDescription());

        holder.setItemOnClickListener(new ItemOnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context,"is click" + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingModels.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public ItemOnClickListener setItemOnClickListener;
        private ItemOnClickListener itemOnClickListener;

        public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
            this.itemOnClickListener = itemOnClickListener;
        }
        private TextView txtContent;
        private TextView txtDecs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtDecs = itemView.findViewById(R.id.txtDecs);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemOnClickListener.onClick(view,getAdapterPosition());
        }
    }

}

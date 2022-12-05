package com.example.infotech.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infotech.ModelClass.ArticlesData;
import com.example.infotech.Interfaces.RecyclerInterface;
import com.example.infotech.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder>{
    private Context context;
    private final RecyclerInterface recyclerInterface;
    private List<ArticlesData> dataList;

    public AdapterRV(Context context, List<ArticlesData> dataList, RecyclerInterface recyclerInterface){
        this.context = context;
        this.dataList = dataList;
        this.recyclerInterface = recyclerInterface;
    }


    @NonNull
    @Override
    public AdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new AdapterRV.ViewHolder(inflate, recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRV.ViewHolder holder, int position) {
        ArticlesData data = dataList.get(position);
        holder.title.setText(data.getTitle());
        Picasso.get().load(data.getUrlToImage()).into(holder.imgId);
        holder.description.setText(data.getDescription());
        };

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView imgId;
        public ViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            imgId = itemView.findViewById(R.id.imgId);
            description = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}







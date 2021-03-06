package com.goldshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldshop.R;
import com.goldshop.model.CategoryInfo;
import com.goldshop.model.GalleryModel;

import java.util.List;

/**
 * Created by bhanwar on 18/06/2017.
 */

public class CategoryInfoAdapter extends RecyclerView.Adapter<CategoryInfoAdapter.MyViewHolder> {

    List<CategoryInfo> mListData;
    Context ctx;
    public CategoryInfoAdapter(Context ctx, List<CategoryInfo> mListData) {
        this.mListData = mListData;
        this.ctx=ctx;
    }

    @Override
    public CategoryInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_info_list_item,
                    viewGroup, false);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Uri gmmIntentUri = Uri.parse("google.navigation:q="+mListData.get(i).getLatitude()+","+mListData.get(i).getLongitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                ctx.startActivity(mapIntent);*/
            }
        });
        return new CategoryInfoAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryInfoAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(mListData.get(i).getPostTitle());
    }

    public void removeItem(int position) {
        mListData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListData.size());
    }
    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView catImage;
        TextView title,weight,addToBag;

        public MyViewHolder(View itemView) {
            super(itemView);

            catImage = (ImageView) itemView.findViewById(R.id.catImageView);
            title= (TextView) itemView.findViewById(R.id.catTitle);
            weight= (TextView) itemView.findViewById(R.id.catWeightText);
            addToBag= (TextView) itemView.findViewById(R.id.addToBagButton);
        }
    }

}
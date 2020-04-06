package com.interviewproject.deals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.interviewproject.deals.Model.Deals;
import java.util.List;

/**
 * Standard Android RecycleView adapter formatted to display the deals list as shown in deals_list_item.xml
 * Loads images and string data from a list of Deals data model, and attaches an OnClickListener to each item View
 * Called from MainActivity, to launch corresponding detail view Activity
 */
public class DealListAdapter extends RecyclerView.Adapter<DealListAdapter.ViewHolder> {

    private List<Deals> mDeals;
    private Context mContext;
    private OnDealClickListener mOnDealClickListener;

    public DealListAdapter(Context context, List<Deals> deals, OnDealClickListener onDealClickListener) {
        mDeals = deals;
        mContext = context;
        this.mOnDealClickListener = onDealClickListener;
    }
    


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deals_list_item, parent, false);
        return new ViewHolder(view, mOnDealClickListener);
    }
                                                                      
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mDeals.get(position).getImage())
                .into(holder.itemImage);
        holder.itemTitle.setText(mDeals.get(position).getTitle());
        holder.itemPrice.setText(mDeals.get(position).getSalePrice());
        holder.itemAisle.setText(mDeals.get(position).getAisle());
        if(mDeals.get(position).getSalePrice() == null) {
            holder.itemPrice.setText(mDeals.get(position).getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return mDeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{

        ImageView itemImage;
        TextView itemTitle;
        TextView itemPrice;
        TextView itemAisle;
        ConstraintLayout containerLayout;

        OnDealClickListener onDealClickListener;

        public ViewHolder(View itemView, OnDealClickListener onDealClickListener) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.list_image);
            itemTitle = itemView.findViewById(R.id.list_title);
            itemPrice = itemView.findViewById(R.id.list_price);
            itemAisle = itemView.findViewById(R.id.list_aisle);
            containerLayout = itemView.findViewById(R.id.layout_container);
            this.onDealClickListener = onDealClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDealClickListener.onDealClick(getAdapterPosition());
        }
    }

    public interface  OnDealClickListener {
        void onDealClick(int position);
    }
}

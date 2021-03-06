package com.bitshares.bitshareswallet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bitshares.bitshareswallet.market.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionSellBuyRecyclerViewAdapter extends RecyclerView.Adapter<TransactionSellBuyRecyclerViewAdapter.ViewHolder> {
    public static final int ORDERITEMHEIGHTDP = 18;
    private List<Order> list;

    private OnItemClickListener listener;

    public TransactionSellBuyRecyclerViewAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<Order> list){
        /*if(getItemCount()>0){
            notifyItemRangeRemoved(0,getItemCount());
        }
        this.list = list;
        notifyItemRangeInserted(0,getItemCount());*/
        this.list = new ArrayList<>(list);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.update(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list==null ? 0: list.size();
    }

    public void clear() {
        list = new ArrayList<>();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Order item;
        public TextView priceTextView;
        public TextView btsTextView;

        public ViewHolder(View view) {
            super(view);
            priceTextView = view.findViewById(R.id.price_text);
            btsTextView = view.findViewById(R.id.bts_text);
        }

        public void update(Order order){
            priceTextView.setText(String .format("%.8f",order.price));
            priceTextView.setSelected(true);
            btsTextView.setText(String .format("%.4f",order.quote));

            itemView.setOnClickListener(v -> listener.onItemClick(String .format("%.8f",order.price)));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String data);
    }
}

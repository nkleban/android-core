package net.intellectsoft.core.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, H extends BaseRecyclerHolder> extends RecyclerView.Adapter<H> {

    protected final LayoutInflater mInflater;
    protected final Context mContext;
    protected final List<T> mItems = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<T> items) {
        mItems.clear();
        if (items != null && items.size() > 0) {
            mItems.addAll(items);
        }
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return (position < mItems.size()) ? mItems.get(position) : null;
    }

}
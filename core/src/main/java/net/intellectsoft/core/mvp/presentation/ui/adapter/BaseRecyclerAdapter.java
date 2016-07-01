package net.intellectsoft.core.mvp.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, H extends BaseRecyclerHolder> extends RecyclerView.Adapter<H> {

    protected final LayoutInflater inflater;
    protected final Context context;
    protected final List<T> items = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<T> items) {
        this.items.clear();
        if (items != null && items.size() > 0) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return (position < items.size()) ? items.get(position) : null;
    }

}
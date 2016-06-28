package net.intellectsoft.sample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import net.intellectsoft.core.ui.adapter.BaseRecyclerAdapter;


public class RecyclerViewAdapter extends BaseRecyclerAdapter<MockDataObject, ViewHolder> {

    public RecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MockDataObject item = getItem(position);
        holder.textView.setText(item.name);
    }
}

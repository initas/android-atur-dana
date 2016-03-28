package id.situs.aturdana.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.models.Source;

/**
 * Created by MF on 3/20/16.
 */
public class SourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<Source> sourceList;

    public static class SourceViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView totalAmount;
        private TextView totalTransaction;
        private TextView totalUser;
        private TextView iconClass;
        private View sourceItem;

        public SourceViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            totalAmount = (TextView) v.findViewById(R.id.total_amount);
            totalTransaction = (TextView) v.findViewById(R.id.total_transaction);
            totalUser = (TextView) v.findViewById(R.id.total_user);
            iconClass = (TextView) v.findViewById(R.id.icon_class);
            sourceItem = v.findViewById(R.id.source_item);
        }
    }

    public SourceAdapter(List<Source> sourceList) {
        this.sourceList = sourceList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.source_item, viewGroup, false);

        return new SourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        SourceViewHolder sourceViewHolder = (SourceViewHolder) holder;
        Source si = sourceList.get((i));
        sourceViewHolder.name.setText(si.getName());
        sourceViewHolder.totalAmount.setText(si.getTotalAmount());
        sourceViewHolder.totalTransaction.setText(si.getTotalTransaction());
        sourceViewHolder.totalUser.setText(si.getTotalUser());
        sourceViewHolder.iconClass.setText(si.getIconClass());
        sourceViewHolder.sourceItem.setBackgroundColor(Color.parseColor(si.getHexColor()));
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}

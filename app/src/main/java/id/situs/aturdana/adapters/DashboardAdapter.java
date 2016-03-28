package id.situs.aturdana.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.models.Transaction;
import id.situs.aturdana.data.Dummy;

/**
 * Created by MF on 3/20/16.
 */
public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private List<Transaction> transactionList;

    public static class TransactionHeaderViewHolder extends RecyclerView.ViewHolder {
        protected RecyclerView source_list;
        protected TextView total_amount;

        public TransactionHeaderViewHolder(View v) {
            super(v);
            source_list = (RecyclerView) v.findViewById(R.id.source_list);
            total_amount = (TextView) v.findViewById(R.id.total_amount);
        }
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected ImageView vImage;
        protected TextView vTitle;
        protected TextView vAmount;
        protected TextView vCategoryName;
        protected TextView vCategoryIconClass;
        protected RelativeLayout vInfoContainer;
        protected TextView vTimestamp;

        public TransactionViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.name);
            vImage = (ImageView) v.findViewById(R.id.image);
            vTitle = (TextView) v.findViewById(R.id.title);
            vAmount = (TextView) v.findViewById(R.id.amount);
            vTimestamp = (TextView) v.findViewById(R.id.timestamp);
            vCategoryName = (TextView) v.findViewById(R.id.category_name);
            vCategoryIconClass = (TextView) v.findViewById(R.id.category_icon_class);
            vInfoContainer = (RelativeLayout) v.findViewById(R.id.info_container);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    public DashboardAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.transaction_item, viewGroup, false);

            return new TransactionViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.transaction_header, viewGroup, false);

            return new TransactionHeaderViewHolder(itemView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof TransactionViewHolder) {
            TransactionViewHolder transactionViewHolder = (TransactionViewHolder) holder;
            Transaction ci = transactionList.get((i - 1));
            transactionViewHolder.vName.setText(ci.getName());
            transactionViewHolder.vTitle.setText(ci.getTitle());
            transactionViewHolder.vAmount.setText(ci.getAmount());
            transactionViewHolder.vCategoryName.setText(ci.getCategoryName());
            transactionViewHolder.vCategoryIconClass.setText(ci.getCategoryIconClass());
            transactionViewHolder.vInfoContainer.setBackgroundColor(Color.parseColor(ci.getCategoryHexColor()));
            transactionViewHolder.vTimestamp.setText(ci.getTimestamp());

            Context context = transactionViewHolder.vImage.getContext();
            Picasso.with(context).load(Uri.parse(ci.getImageUrl())).into(transactionViewHolder.vImage);
        } else if (holder instanceof TransactionHeaderViewHolder) {
            TransactionHeaderViewHolder transactionHeaderViewHolder = (TransactionHeaderViewHolder) holder;
            transactionHeaderViewHolder.total_amount.setText("300,000 IDR");

            //source list
            Context context = transactionHeaderViewHolder.source_list.getContext();
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            transactionHeaderViewHolder.source_list.setLayoutManager(layoutManager);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            SourceAdapter sourceAdapter = new SourceAdapter(Dummy.createSourceList());
            transactionHeaderViewHolder.source_list.setAdapter(sourceAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return transactionList.size() + 1;
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


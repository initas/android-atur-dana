package id.situs.aturdana.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.models.Dashboard;
import id.situs.aturdana.models.Source;
import id.situs.aturdana.models.Transaction;

/**
 * Created by MF on 3/20/16.
 */
public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private Dashboard dashboard;
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
        protected ImageView vPhoto;
        protected TextView vTitle;
        protected TextView vDescription;
        protected TextView vAmount;
        protected TextView vCategoryName;
        protected TextView vCategoryIconClass;
        protected RelativeLayout vInfoContainer;
        protected TextView vTimestamp;

        public TransactionViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.name);
            vImage = (ImageView) v.findViewById(R.id.image);
            vPhoto = (ImageView) v.findViewById(R.id.photo);
            vTitle = (TextView) v.findViewById(R.id.title);
            vDescription = (TextView) v.findViewById(R.id.description);
            vAmount = (TextView) v.findViewById(R.id.amount);
            vTimestamp = (TextView) v.findViewById(R.id.timestamp);
            vCategoryName = (TextView) v.findViewById(R.id.category_name);
            vCategoryIconClass = (TextView) v.findViewById(R.id.category_icon_class);
            vInfoContainer = (RelativeLayout) v.findViewById(R.id.info_container);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    public DashboardAdapter(Dashboard dashboard) {
        this.dashboard = dashboard;
        this.transactionList = dashboard.getTransactions();
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

        throw new RuntimeException(viewType + " type is undefined");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof TransactionViewHolder) {
            TransactionViewHolder transactionViewHolder = (TransactionViewHolder) holder;
            Transaction transaction = transactionList.get(i - 1);

            transactionViewHolder.vName.setText(transaction.getUser().getFullName());
            transactionViewHolder.vTitle.setText("Memakai Dana " + transaction.getSource().getName());

            if(transaction.getToSource() != null){
                transactionViewHolder.vTitle.setText("Transfer Dana " + transaction.getSource().getName() + " ke " +transaction.getToSource().getName());
            }

            transactionViewHolder.vCategoryName.setText(transaction.getCategory().getName());
            transactionViewHolder.vInfoContainer.setBackgroundColor(Color.parseColor(transaction.getCategory().getHexColor()));
            transactionViewHolder.vCategoryIconClass.setText(transaction.getCategory().getIconClass());

            String time = (String) DateUtils.getRelativeTimeSpanString(transaction.getUpdatedAt());
            transactionViewHolder.vTimestamp.setText(time);

            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String totalAmount = formatter.format(transaction.getAmount());
            transactionViewHolder.vAmount.setText(totalAmount + " IDR");

            String description = transaction.getDescription();

            if (description != null) {
                transactionViewHolder.vDescription.setText(description);
                transactionViewHolder.vDescription.setVisibility(View.VISIBLE);
            }


            String photo = transaction.getImage().getOriginal();

            if (photo != null) {
                photo = "http://media2.popsugar-assets.com/files/2015/12/18/904/n/1922195/2540e9bd_edit_img_image_17359796_1450469569.xxlarge/i/Mini-Cheesy-Potato-Gratins.jpg";
                Context context = transactionViewHolder.vPhoto.getContext();
                Picasso.with(context).load(Uri.parse(photo)).into(transactionViewHolder.vPhoto);
            }

            Context context = transactionViewHolder.vImage.getContext();
            Picasso.with(context).load(Uri.parse(transaction.getUser().getImage().getOriginal())).into(transactionViewHolder.vImage);
            //Picasso.with(context).load(Uri.parse("http://www.freeapplewallpapers.com/wp-content/uploads/2014/03/Lovely-Asian-Girl-In-The-Sun-150x150.jpg")).into(transactionViewHolder.vImage);

        } else if (holder instanceof TransactionHeaderViewHolder) {
            TransactionHeaderViewHolder transactionHeaderViewHolder = (TransactionHeaderViewHolder) holder;

            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String totalAmount = formatter.format(dashboard.getTotalAmount());
            transactionHeaderViewHolder.total_amount.setText(totalAmount + " IDR");

            List<Source> Sources = dashboard.getSources();

            //source list
            transactionHeaderViewHolder.source_list.bringToFront();
            Context context = transactionHeaderViewHolder.source_list.getContext();
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            transactionHeaderViewHolder.source_list.setLayoutManager(layoutManager);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            SourceAdapter sourceAdapter = new SourceAdapter(Sources);
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


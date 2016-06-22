package id.situs.aturdana.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.models.TransactionComment;

/**
 * Created by MF on 3/20/16.
 */
public class TransactionCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TransactionComment> transactionCommentList;

    public static class TransactionCommentViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private ImageView photo;

        public TransactionCommentViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            description = (TextView) v.findViewById(R.id.description);
            photo = (ImageView) v.findViewById(R.id.photo); 
        }
    }

    public TransactionCommentAdapter(List<TransactionComment> transactionCommentList) {
        this.transactionCommentList = transactionCommentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.transaction_comment_item, viewGroup, false);

        return new TransactionCommentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        TransactionCommentViewHolder transactionCommentViewHolder = (TransactionCommentViewHolder) holder;
        TransactionComment si = transactionCommentList.get(i);

        transactionCommentViewHolder.name.setText(si.getUser().getFullName());
        transactionCommentViewHolder.description.setText(si.getDescription());

        String imageUrl = si.getUser().getImage().getOriginal();
        Context context = transactionCommentViewHolder.photo.getContext();
        Picasso.with(context).load(Uri.parse(imageUrl)).into(transactionCommentViewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return transactionCommentList.size();
    }
}

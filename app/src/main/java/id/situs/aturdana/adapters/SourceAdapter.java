package id.situs.aturdana.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.models.Source;
import id.situs.aturdana.models.User;

/**
 * Created by MF on 3/20/16.
 */
public class SourceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Source> sourceList;

    public static class SourceViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView totalAmount;
        private TextView transactionCount;
        private TextView collaboratorCount;
        private TextView iconClass;
        private View sourceItem;
        private RecyclerView collaboratorList;

        public SourceViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            totalAmount = (TextView) v.findViewById(R.id.total_amount);
            transactionCount = (TextView) v.findViewById(R.id.transaction_count);
            collaboratorCount = (TextView) v.findViewById(R.id.collaborator_count);
            iconClass = (TextView) v.findViewById(R.id.icon_class);
            sourceItem = v.findViewById(R.id.source_item);
            collaboratorList = (RecyclerView) v.findViewById(R.id.collaborator_list);
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
        Source si = sourceList.get(i);
        String color = si.getHexColor();

        sourceViewHolder.name.setText(si.getName());
        sourceViewHolder.totalAmount.setText(si.getAmount() + "");
        sourceViewHolder.transactionCount.setText(si.getTransactionCount() + "");
        sourceViewHolder.collaboratorCount.setText(si.getCollaboratorCount() + "");
        sourceViewHolder.iconClass.setText(si.getIconClass());

        GradientDrawable drawable = (GradientDrawable) sourceViewHolder.sourceItem.getBackground();
        drawable.setColor(Color.parseColor(color));

        List<User> collaborators = si.getCollaborators();

        //source list
        Context context = sourceViewHolder.collaboratorList.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        sourceViewHolder.collaboratorList.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        CollaboratorAdapter sourceAdapter = new CollaboratorAdapter(collaborators, color);
        sourceViewHolder.collaboratorList.setAdapter(sourceAdapter);
    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }
}

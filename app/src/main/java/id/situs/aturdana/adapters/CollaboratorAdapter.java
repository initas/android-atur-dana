package id.situs.aturdana.adapters;

import android.content.Context;
import android.graphics.Color;
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
import id.situs.aturdana.models.User;

/**
 * Created by MF on 3/20/16.
 */
public class CollaboratorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 3;
    private List<User> collaboratorList;
    private String color;

    public static class CollaboratorViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;

        public CollaboratorViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }

    public static class CollaboratorFooterViewHolder extends RecyclerView.ViewHolder {
        protected TextView addCollaborator;

        public CollaboratorFooterViewHolder(View v) {
            super(v);
            addCollaborator = (TextView) v.findViewById(R.id.add_collaborator);
        }
    }

    public CollaboratorAdapter(List<User> collaboratorList, String color) {
        this.collaboratorList = collaboratorList;
        this.color = color;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.collaborator_item, viewGroup, false);

            return new CollaboratorViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.collaborator_footer, viewGroup, false);

            return new CollaboratorFooterViewHolder(itemView);
        }

        throw new RuntimeException(viewType + " type is undefined");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof CollaboratorViewHolder) {
            CollaboratorViewHolder userViewHolder = (CollaboratorViewHolder) holder;
            User user = collaboratorList.get(i);

            Context context = userViewHolder.image.getContext();
            Picasso.with(context).load(Uri.parse("http://www.freeapplewallpapers.com/wp-content/uploads/2014/03/Lovely-Asian-Girl-In-The-Sun-150x150.jpg")).into(userViewHolder.image);
            //Picasso.with(context).load(Uri.parse(ci.getImageUrl())).into(userViewHolder.vImage);

        } else if (holder instanceof CollaboratorFooterViewHolder) {
            CollaboratorFooterViewHolder sourceViewFooterHolder = (CollaboratorFooterViewHolder) holder;
            sourceViewFooterHolder.addCollaborator.setTextColor(Color.parseColor(color));
        }
    }

    @Override
    public int getItemCount() {
        return collaboratorList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position))
            return TYPE_FOOTER;

        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == collaboratorList.size();
    }
}


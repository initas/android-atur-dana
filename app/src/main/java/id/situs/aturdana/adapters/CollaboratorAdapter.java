package id.situs.aturdana.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.activities.MainActivity;
import id.situs.aturdana.activities.ProfileActivity;
import id.situs.aturdana.activities.TransactionDetailActivity;
import id.situs.aturdana.models.User;

/**
 * Created by MF on 3/20/16.
 */
public class CollaboratorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_FOOTER = 3;
    private List<User> collaboratorList;
    private String color;
    private User userDetail;
    private static Context context;

    public static class CollaboratorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;
        Integer userId;

        public CollaboratorViewHolder(View v) {
            super(v);
            context = v.getContext();
            image = (ImageView) v.findViewById(R.id.image);
            image.setOnClickListener(this);
        }

        public void setItem(Integer getUserId) {
            userId = getUserId;
        }

        @Override
        public void onClick(View v) {
            Intent intent =  new Intent(context, ProfileActivity.class);
            intent.putExtra("userId", userId);
            context.startActivity(intent);
        }

    }

    public static class CollaboratorHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView image;
        Integer userId;

        public CollaboratorHeaderViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            image.setOnClickListener(this);
        }

        public void setItem(Integer getUserId) {
            userId = getUserId;
        }

        @Override
        public void onClick(View v) {
            Intent intent =  new Intent(context, ProfileActivity.class);
            intent.putExtra("userId", userId);
            context.startActivity(intent);
        }
    }

    public static class CollaboratorFooterViewHolder extends RecyclerView.ViewHolder {
        protected TextView addCollaborator;

        public CollaboratorFooterViewHolder(View v) {
            super(v);
            addCollaborator = (TextView) v.findViewById(R.id.add_collaborator);
        }
    }

    public CollaboratorAdapter(List<User> collaboratorList, String color, User user) {
        this.collaboratorList = collaboratorList;
        this.color = color;
        this.userDetail = user;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.collaborator_item, viewGroup, false);

            return new CollaboratorViewHolder(itemView);
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.collaborator_item, viewGroup, false);

            return new CollaboratorHeaderViewHolder(itemView);
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
            User user = collaboratorList.get(i - 1);

            ((CollaboratorViewHolder) holder).setItem(user.getId());

            String imageUrl = user.getImage().getOriginal();
            //String imageUrl = "http://www.freeapplewallpapers.com/wp-content/uploads/2014/03/Lovely-Asian-Girl-In-The-Sun-150x150.jpg";

            Context context = userViewHolder.image.getContext();
            Picasso.with(context).load(Uri.parse(imageUrl)).into(userViewHolder.image);

        } else if (holder instanceof CollaboratorHeaderViewHolder) {
            CollaboratorHeaderViewHolder ownerViewHolder = (CollaboratorHeaderViewHolder) holder;

            ((CollaboratorHeaderViewHolder) holder).setItem(userDetail.getId());

            String imageUrl = userDetail.getImage().getOriginal();
            //String imageUrl = "http://www.freeapplewallpapers.com/wp-content/uploads/2014/03/Lovely-Asian-Girl-In-The-Sun-150x150.jpg";

            Context context = ownerViewHolder.image.getContext();
            Picasso.with(context).load(Uri.parse(imageUrl)).into(ownerViewHolder.image);

        } else if (holder instanceof CollaboratorFooterViewHolder) {
            CollaboratorFooterViewHolder sourceViewFooterHolder = (CollaboratorFooterViewHolder) holder;
            sourceViewFooterHolder.addCollaborator.setTextColor(Color.parseColor(color));
        }
    }

    @Override
    public int getItemCount() {
        return collaboratorList.size() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position))
            return TYPE_FOOTER;

        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionFooter(int position) {
        return position == collaboratorList.size()+1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}


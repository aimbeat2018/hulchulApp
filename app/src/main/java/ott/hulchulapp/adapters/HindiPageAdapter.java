package ott.hulchulapp.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.clevertap.android.sdk.CleverTapAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ott.hulchulapp.DetailsActivity;
import ott.hulchulapp.R;
import ott.hulchulapp.models.CommonModels;
import ott.hulchulapp.utils.ItemAnimation;

public class HindiPageAdapter extends RecyclerView.Adapter<HindiPageAdapter.OriginalViewHolder> {

    private List<CommonModels> items = new ArrayList<>();
    private Context ctx;

    private int lastPosition = -1;
    private boolean on_attach = true;
    private int animation_type = 2;

    CleverTapAPI clevertapscreenviewd;


    public HindiPageAdapter(Context context, List<CommonModels> items) {
        this.items = items;
        ctx = context;
    }


    @Override
    public HindiPageAdapter.OriginalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HindiPageAdapter.OriginalViewHolder vh;


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hindi_card_home_view, parent, false);
        vh = new HindiPageAdapter.OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final HindiPageAdapter.OriginalViewHolder holder, final int position) {

        final CommonModels obj = items.get(position);
        holder.name.setText(obj.getTitle());
        Picasso.get().load(obj.getImageUrl()).resize(1024, 390).placeholder(R.drawable.poster_placeholder).into(holder.image);

        setFadeAnimation(holder.itemView);

        holder.qualityTv.setText(obj.getQuality());
        holder.releaseDateTv.setText(obj.getReleaseDate());

        clevertapscreenviewd= CleverTapAPI.getDefaultInstance(ctx.getApplicationContext());

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (PreferenceUtils.isMandatoryLogin(ctx)){
                    if (PreferenceUtils.isLoggedIn(ctx)){
                        Intent intent=new Intent(ctx, DetailsActivity.class);
                        intent.putExtra("vType",obj.getVideoType());
                        intent.putExtra("id",obj.getId());

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        ctx.startActivity(intent);
                    }else {
                        ctx.startActivity(new Intent(ctx, LoginActivity.class));
                    }
                }else {*/
                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra("vType", obj.getVideoType());
                intent.putExtra("id", obj.getId());

                HashMap<String, Object> screenViewedAction = new HashMap<String, Object>();
                screenViewedAction.put("Screen Name", "DetailsActivity");
                clevertapscreenviewd.pushEvent("Screen Viewed", screenViewedAction);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                ctx.startActivity(intent);
//                }

            }
        });

        setAnimation(holder.itemView, position);

    }

    private void setFadeAnimation(View  view) {

        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name, qualityTv, releaseDateTv;
        public MaterialRippleLayout lyt_parent;
CardView hindicardview;

        public OriginalViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            name = v.findViewById(R.id.name);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            qualityTv = v.findViewById(R.id.quality_tv);
            releaseDateTv = v.findViewById(R.id.release_date_tv);
            hindicardview = v.findViewById(R.id.hindicardview);





        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }

        });


        super.onAttachedToRecyclerView(recyclerView);
    }

   /* private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }*/



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(ctx, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}

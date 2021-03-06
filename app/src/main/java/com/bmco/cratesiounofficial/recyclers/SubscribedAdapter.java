package com.bmco.cratesiounofficial.recyclers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmco.cratesiounofficial.CrateActivity;
import com.bmco.cratesiounofficial.R;
import com.bmco.cratesiounofficial.Utility;
import com.bmco.cratesiounofficial.models.Alert;
import com.bmco.cratesiounofficial.models.Crate;

import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

/**
 * Created by Bertus on 25-5-2017.
 */

public class SubscribedAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Alert> alerts;

    public SubscribedAdapter(Context context, List<Alert> alerts) {
        this.context = context;
        this.alerts = alerts;
    }

    @Override
    @SuppressLint("InflateParams")
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subscribed_recycler_item, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Alert alert = alerts.get(position);
        final Crate crate = alert.getCrate();

        FloatingTextButton fab = (FloatingTextButton) holder.itemView.findViewById(R.id.delete_button);
        TextView title = (TextView) holder.itemView.findViewById(R.id.crate_title);

        title.setText(crate.getName());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerts.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                Utility.saveData("alerts", alerts);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CrateActivity.class);
                intent.putExtra("crate", crate);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

    private static class CustomViewHolder extends RecyclerView.ViewHolder {
        CustomViewHolder(View itemView) {
            super(itemView);
        }
    }
}

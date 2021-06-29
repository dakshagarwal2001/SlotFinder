package com.example.slottracker;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<CenterInfo> al;

    public MyAdapter(Context context, ArrayList<CenterInfo> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.ViewHolder holder, int position) {
            CenterInfo obj = al.get(position);
            holder.textView.setText(obj.name);
            holder.pincode.setText("PIN : " + String.valueOf(obj.pin));
            holder.ageLimit.setText("AGE LIMIT : " + String.valueOf(obj.min_age_limit));
            holder.slots.setText("AVAILABLE SLOTS : " + String.valueOf(obj.slots_available));
            holder.vaccine.setText("Vaccine : " + obj.vaccine);
    }

    @Override
    public int getItemCount() {
        return al.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        TextView pincode;
        TextView slots;
        TextView ageLimit;
        TextView vaccine;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.textView3);
            pincode = itemView.findViewById(R.id.pin);
            ageLimit = itemView.findViewById(R.id.age_limit);
            slots = itemView.findViewById(R.id.slots);
            vaccine = itemView.findViewById(R.id.vaccine);

        }

        @Override
        public void onClick(View v) {
            String url = "https://www.cowin.gov.in/home";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
        }
    }
}

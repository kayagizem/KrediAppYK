package com.hakansahin.kredibasvurusuapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";
    List<String> list;
    String dataKey;
    AppCompatActivity from;
    Class to;

    Intent intent;

    public RecyclerAdapter(List<String> list, String dataKey, Intent intent, AppCompatActivity from, Class to) {
        this.list = list;
        this.dataKey = dataKey;
        this.intent = intent;
        this.from = from;
        this.to = to;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, intent);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        Intent intent;

        public ViewHolder(@NonNull View itemView,Intent intent) {
            super(itemView);
            this.intent = intent;
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            intent.putExtra(dataKey, list.get(getAdapterPosition()));

            Intent intent = new Intent(from, to);
            intent.putExtras(this.intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            from.startActivity(intent);
        }
    }

    public void filterList(ArrayList<String> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}

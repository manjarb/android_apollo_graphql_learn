package com.amusexd.apollo_graphql_learn.ui;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amusexd.apollo_graphql_learn.R;
import com.amusexd.apollo_graphql_learn.graphql.GetCategoryDetailsQuery;

import java.util.Collections;
import java.util.List;


public class DetsRecyclerViewAdapter
        extends RecyclerView.Adapter<DetsRecyclerViewAdapter.DetsViewHolder> {

    private Context context;
    private List<GetCategoryDetailsQuery.Food> categoryFoods = Collections.emptyList();

    public DetsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategoryData(List<GetCategoryDetailsQuery.Food> categoryFoods) {
        this.categoryFoods = categoryFoods;

        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetsRecyclerViewAdapter.DetsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater
                .from(viewGroup.getContext());
        final View viewItem = layoutInflater.inflate(
                R.layout.row,
                viewGroup,
                false
        );

        return new DetsViewHolder(viewItem, context);
    }

    @Override
    public void onBindViewHolder(@NonNull DetsRecyclerViewAdapter.DetsViewHolder detsViewHolder, int position) {
        final GetCategoryDetailsQuery.Food categoryFood = this.categoryFoods.get(position);
        detsViewHolder.setFoods(categoryFood);
    }

    @Override
    public int getItemCount() {
        return categoryFoods.size();
    }

    public class DetsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        Context context;

        public DetsViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            title = itemView.findViewById(R.id.dets_row_title);
            description = itemView.findViewById(R.id.dets_row_description);
            this.context = context;
        }

        public void setFoods(GetCategoryDetailsQuery.Food food) {
            title.setText(food.name());
            description.setText(food.description());
        }
    }
}

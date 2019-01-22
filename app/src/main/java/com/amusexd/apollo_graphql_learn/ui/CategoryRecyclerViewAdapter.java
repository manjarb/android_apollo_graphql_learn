package com.amusexd.apollo_graphql_learn.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amusexd.apollo_graphql_learn.DetailsActivity;
import com.amusexd.apollo_graphql_learn.R;
import com.amusexd.apollo_graphql_learn.graphql.CategoriesQuery;
import com.amusexd.apollo_graphql_learn.util.AplClient;

import java.util.Collections;
import java.util.List;

public class CategoryRecyclerViewAdapter
        extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoriesQuery.Category> categories = Collections.emptyList();

    public CategoryRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setCategories(List<CategoriesQuery.Category> categories) {
        this.categories = categories;
        this.notifyDataSetChanged();
        Log.d(AplClient.TAG, "Update Categories in adapter" + categories.size());
    }

    @NonNull
    @Override
    public CategoryRecyclerViewAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater
                .from(parent.getContext());

        final View itemView = layoutInflater.inflate(R.layout.category_row,
                parent, false);
        return new CategoryViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewAdapter.CategoryViewHolder categoryViewHolder, int position) {
        final CategoriesQuery.Category category = this.categories.get(position);
        categoryViewHolder.setCategory(category);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;
        TextView ageTextView;
        TextView professionTextViev;
        CardView container;
        String categoryId;
        Context context;

        public CategoryViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            categoryTextView = itemView.findViewById(R.id.userNameId);
            ageTextView = itemView.findViewById(R.id.ageId);
            professionTextViev = itemView.findViewById(R.id.professionID);
            container = itemView.findViewById(R.id.cardViewId);

            this.context = context;
        }

        void setCategory(final CategoriesQuery.Category category) {
            categoryTextView.setText(category.name());
            ageTextView.setText(category.id());
            professionTextViev.setText(category.description());

            categoryId = category.id();
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(AplClient.TAG, categoryId);

                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("categoryId", categoryId);
                    intent.putExtra("name", category.name());
                    intent.putExtra("description", category.description());
                    intent.putExtra("age", category.name());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }
    }
}

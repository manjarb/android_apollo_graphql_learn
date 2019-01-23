package com.amusexd.apollo_graphql_learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amusexd.apollo_graphql_learn.graphql.CategoriesQuery;
import com.amusexd.apollo_graphql_learn.graphql.GetCategoryDetailsQuery;
import com.amusexd.apollo_graphql_learn.ui.DetsRecyclerViewAdapter;
import com.amusexd.apollo_graphql_learn.util.AplClient;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    private String categoryId;
    private DetsRecyclerViewAdapter detsRecyclerViewAdapter;
    private RecyclerView detsRecyclerView;

    private List<GetCategoryDetailsQuery.Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView nameTextView = findViewById(R.id.dets_name);
        TextView ageTextViev = findViewById(R.id.dets_age);
        TextView professionTextview = findViewById(R.id.dets_profession);
        Button hobbiesButton = findViewById(R.id.dets_hobbies_btn);

        foodList = Collections.emptyList();

        Intent intent = this.getIntent();

        if (intent != null) {
            categoryId = intent.getStringExtra("categoryId");
            String name = intent.getStringExtra("name");
            String description = intent.getStringExtra("description");
            String age = intent.getStringExtra("age");

            nameTextView.setText(name);
            ageTextViev.setText("Age: " + age);
            professionTextview.setText("profession: " + description);

            // RecyclerView Setup
            detsRecyclerView = findViewById(R.id.dets_rv);
            detsRecyclerViewAdapter = new DetsRecyclerViewAdapter(this.getApplicationContext());
            detsRecyclerView.setAdapter(detsRecyclerViewAdapter);
            detsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            // detsRecyclerView.setVisibility(View.INVISIBLE);
            detsRecyclerView.removeAllViews();

            getCategory();
        }
    }

    private void getCategory() {
        AplClient.getmApolloClient()
                .query(GetCategoryDetailsQuery.builder()
                        .categoryId(categoryId)
                        .build())
                .enqueue(new ApolloCall.Callback<GetCategoryDetailsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull final Response<GetCategoryDetailsQuery.Data> response) {
                        // Log.d("GetCategory Details: ", response.data().toString());

                        List<GetCategoryDetailsQuery.Food> foods = response.data().category().foods();

                        for (int i = 0; i < foods.size(); i++) {
                            Log.d("ALL Food ==>", foods.get(i).name());
                        }

                        DetailsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                foodList = Objects.requireNonNull(response.data().category()).foods();
                                detsRecyclerViewAdapter.setCategoryData(foodList);
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e(AplClient.TAG, e.getStackTrace().toString());

                    }
                });
    }


}

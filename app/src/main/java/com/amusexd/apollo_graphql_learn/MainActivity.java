package com.amusexd.apollo_graphql_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amusexd.apollo_graphql_learn.graphql.CategoriesQuery;
import com.amusexd.apollo_graphql_learn.ui.CategoryRecyclerViewAdapter;
import com.amusexd.apollo_graphql_learn.util.AplClient;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;
    private ViewGroup content;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = findViewById(R.id.content_holderId);
        progressBar = findViewById(R.id.progressBarId);

        RecyclerView categoryRecyclerView = findViewById(R.id.rv_users_listId);
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(this.getApplicationContext());
        categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getCategories();
    }

    /*
        Apollo Client Stuff
     */

    private void getCategories() {
        AplClient.getmApolloClient()
                .query(CategoriesQuery.builder().build())
                .enqueue(new ApolloCall.Callback<CategoriesQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull final Response<CategoriesQuery.Data> response) {
//                        final String name = response.data().categories().get(0).name();
//                        Log.d("QLSUCCESS DATA ==>", name);
//
//                        for (int i = 0; i < response.data().categories().size(); i++) {
//                            Log.d("ALL Categories", response.data().categories().get(i).name());
//                        }

                        progressBar.setVisibility(View.VISIBLE);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                categoryRecyclerViewAdapter.setCategories(
                                        response.data().categories()
                                );

                                progressBar.setVisibility(View.GONE);
                                content.setVisibility(View.VISIBLE);
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

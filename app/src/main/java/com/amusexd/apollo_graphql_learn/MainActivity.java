package com.amusexd.apollo_graphql_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amusexd.apollo_graphql_learn.graphql.CategoriesQuery;
import com.amusexd.apollo_graphql_learn.util.AplClient;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textID);

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
                    public void onResponse(@NotNull Response<CategoriesQuery.Data> response) {
                        String name = response.data().categories().get(0).name();
                        Log.d("QLSUCCESS DATA ==>", name);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("QLERROR :", e.toString());
                    }
                });
    }
}

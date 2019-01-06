package com.amusexd.apollo_graphql_learn.util;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class AplClient {
    public static final String BASE_URL = "http://localhost:8801/graphql";
    private static ApolloClient mApolloClient;

    public static ApolloClient getmApolloClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        mApolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();

        return mApolloClient;
    }
}

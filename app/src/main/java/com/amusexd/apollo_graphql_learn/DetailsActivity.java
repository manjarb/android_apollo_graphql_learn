package com.amusexd.apollo_graphql_learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private String categoryId;
    private String name;
    private String description;
    private String age;
    private TextView nameTextView;
    private TextView ageTextViev;
    private TextView professionTextview;

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

        nameTextView = findViewById(R.id.dets_name);
        ageTextViev = findViewById(R.id.dets_age);
        professionTextview = findViewById(R.id.dets_profession);


        Intent intent = this.getIntent();

        if (intent != null) {
            categoryId = intent.getStringExtra("categoryId");
            name = intent.getStringExtra("name");
            description = intent.getStringExtra("description");
            age = intent.getStringExtra("age");

            nameTextView.setText(name);
            ageTextViev.setText("Age: " + age);
            professionTextview.setText("profession: " + description);
        }
    }

}

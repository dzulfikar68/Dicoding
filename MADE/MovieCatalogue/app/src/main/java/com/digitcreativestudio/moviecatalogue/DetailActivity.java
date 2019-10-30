package com.digitcreativestudio.moviecatalogue;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT = "item";
    ImageView image;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        title = findViewById(R.id.title_detail);
        description = findViewById(R.id.description_detail);
        image = findViewById(R.id.image_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_INTENT);
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        image.setImageResource(movie.getPoster());
    }
}

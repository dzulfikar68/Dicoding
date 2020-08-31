package com.digitcreativestudio.moviecatalogueuiux;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_INTENT = "item";
    ImageView image;
    TextView title, description, release, genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Movie");
            // set menu back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        title = findViewById(R.id.text_title);
        description = findViewById(R.id.text_description);
        release = findViewById(R.id.text_release);
        genre = findViewById(R.id.text_genre);
        image = findViewById(R.id.image_movie);

        Movie movie = getIntent().getParcelableExtra(EXTRA_INTENT);
        title.setText(movie.getTitle());
        description.setText(description.getText().toString().trim() + ":\n" + movie.getDescription());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            description.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
        release.setText(release.getText().toString().trim() + ": " + movie.getRelease());
        genre.setText(movie.getGenre());
        Glide.with(getApplicationContext()).load(movie.getPoster()).into(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.digitcreativestudio.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] dataTitle;
    private String[] dataDescription;
    private TypedArray dataImage;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        adapter = new MovieAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveWithObjectIntent = new Intent(MainActivity.this, DetailActivity.class);
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_INTENT, movies.get(position));
                startActivity(moveWithObjectIntent);
            }
        });

        prepare();
        addItem();
    }

    private void prepare(){
        dataTitle = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataImage = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem(){
        movies = new ArrayList<>();

        for(int i=0; i<dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setImage(dataImage.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setDescription(dataDescription[i]);

            movies.add(movie);
        }

        adapter.setMovies(movies);
    }
}

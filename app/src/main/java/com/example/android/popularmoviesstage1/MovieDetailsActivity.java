package com.example.android.popularmoviesstage1;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle data = getIntent().getExtras();
        Movie currentMovie = data.getParcelable(EXTRA_POSITION);

        ImageView poster_path = findViewById(R.id.poster_thumbnail);
        TextView original_title = findViewById(R.id.original_title_content);
        TextView user_rating = findViewById(R.id.user_rating_content);
        TextView release_date = findViewById(R.id.release_date_content);
        TextView overview = findViewById(R.id.overview_content);

        original_title.setText(currentMovie.getOriginal_title());
        user_rating.setText(currentMovie.getVote_average().toString());
        release_date.setText(currentMovie.getRelease_date());
        overview.setText(currentMovie.getOverview());

        Picasso.with(this).load("http://image.tmdb.org/t/p/w185/" + currentMovie.getPoster_path()).into(poster_path);

    }
}

package com.example.android.popularmoviesstage1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>> {

    public final static String BASE_URL= "https://api.themoviedb.org/3/movie";
    public final static String API_KEY = "";

    GridView gridView;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);

        movieAdapter = new MovieAdapter(this, new ArrayList<Movie>());

        gridView.setAdapter(movieAdapter);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            getLoaderManager().initLoader(0, null, this);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie currentMovie = (Movie) gridView.getAdapter().getItem(i);
                launchDetailActivity(currentMovie);
            }
        });

    }

    public Loader<List<Movie>> onCreateLoader (int i, Bundle bundle){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String displayBy = sharedPreferences.getString("pref_sort_movies","top_rated");

        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendPath(displayBy).appendQueryParameter("api_key",API_KEY).appendQueryParameter("language","en-US").appendQueryParameter("page","2");

        return new MovieLoader(this,uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        movieAdapter.clear();
        if (movies !=null && !movies.isEmpty()){
            movieAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }

    public void launchDetailActivity(Movie currentMovie){
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_POSITION, currentMovie);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

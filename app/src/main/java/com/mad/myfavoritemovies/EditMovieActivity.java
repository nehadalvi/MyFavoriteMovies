package com.mad.myfavoritemovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by neha5 on 28-01-2017.
 */

public class EditMovieActivity extends AppCompatActivity{
    EditText editText;
    TextView textViewRating;
    Spinner genre;
    SeekBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Movie movie = (Movie) getIntent().getExtras().getSerializable(MainActivity.MOVIE_KEY);

        editText = (EditText) findViewById(R.id.editTextName);
        editText.setText(movie.getName());
        editText = (EditText) findViewById(R.id.editTextDescription);
        editText.setText(movie.getDescription());
        editText = (EditText) findViewById(R.id.editTextYear);
        editText.setText(String.valueOf(movie.getYear()));
        editText = (EditText) findViewById(R.id.editTextIMDBLink);
        editText.setText(movie.getImdbLink());
        genre = (Spinner) findViewById(R.id.spinnerGenre);
        String movieGenre = movie.getGenre();
        switch(movieGenre){
            case "Action": genre.setSelection(0);
                break;
            case "Animation": genre.setSelection(1);
                break;
            case "Comedy": genre.setSelection(2);
                break;
            case "Documentary": genre.setSelection(3);
                break;
            case "Family": genre.setSelection(4);
                break;
            case "Horror": genre.setSelection(5);
                break;
            case "Crime": genre.setSelection(6);
                break;
            case "Others": genre.setSelection(7);
                break;
        }

        rating = (SeekBar) findViewById(R.id.seekBarRating);
        rating.setProgress(movie.getRating());
        textViewRating = (TextView) findViewById(R.id.textViewRatingOutput);
        textViewRating.setText(String.valueOf(movie.getRating()));

        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                textViewRating = (TextView) findViewById(R.id.textViewRatingOutput);
                textViewRating.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        findViewById(R.id.buttonMovieSaveChanges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                editText = (EditText) findViewById(R.id.editTextName);
                movie.setName(editText.getText().toString());

                editText = (EditText) findViewById(R.id.editTextDescription);
                movie.setDescription(editText.getText().toString());

                editText = (EditText) findViewById(R.id.editTextName);
                movie.setName(editText.getText().toString());

                editText = (EditText) findViewById(R.id.editTextYear);
                movie.setYear(Integer.parseInt(editText.getText().toString()));

                rating = (SeekBar) findViewById(R.id.seekBarRating);
                movie.setRating(rating.getProgress());

                editText = (EditText) findViewById(R.id.editTextIMDBLink);
                movie.setImdbLink(editText.getText().toString());

                movie.setGenre(genre.getSelectedItem().toString());

                if(movie.getName().equals("")){
                    Toast.makeText(EditMovieActivity.this,getResources().getString(R.string.error_name),Toast.LENGTH_SHORT).show();
                }else if(movie.getName().length()>50) {
                    Toast.makeText(EditMovieActivity.this,getResources().getString(R.string.error_movie_length),Toast.LENGTH_SHORT).show();
                }else if(movie.getDescription().equals("")){
                    Toast.makeText(EditMovieActivity.this,getResources().getString(R.string.error_description),Toast.LENGTH_SHORT).show();
                }else if (movie.getDescription().length()>1000) {
                    Toast.makeText(EditMovieActivity.this,getResources().getString(R.string.error_description_length),Toast.LENGTH_SHORT).show();
                }else if(movie.getYear()<1800 || movie.getYear()>2030){
                    Toast.makeText(EditMovieActivity.this,getResources().getString(R.string.error_year),Toast.LENGTH_SHORT).show();
                }else if(movie.getImdbLink().equals("")){
                    Toast.makeText(EditMovieActivity.this,getResources().getString(R.string.error_imdblink),Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.EDIT_KEY,movie);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

    }
}

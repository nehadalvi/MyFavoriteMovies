package com.mad.myfavoritemovies;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class AddMovieActivity extends AppCompatActivity {
    //private Movie movie;
    EditText et;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        Log.d("demo","In add movie activity ");
        SeekBar sb = (SeekBar) findViewById(R.id.seekBarRating);
        tv = (TextView)findViewById(R.id.textViewRatingOutput);
        tv.setText(sb.getProgress()+"");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tv = (TextView) findViewById(R.id.textViewRatingOutput);
                tv.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        findViewById(R.id.buttonMovieAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie= new Movie();

                et = (EditText) findViewById(R.id.editTextName);
                movie.setName(et.getText().toString().trim());

                et = (EditText) findViewById(R.id.editTextDescription);
                movie.setDescription(et.getText().toString().trim());

                et = (EditText) findViewById(R.id.editTextYear);
                String temp= et.getText().toString().trim();
                if(temp.equals("")||temp==null){
                    movie.setYear(0);
                }else{
                    movie.setYear(Integer.parseInt(temp));

                }

                et = (EditText) findViewById(R.id.editTextIMDBLink);
                movie.setImdbLink(et.getText().toString().trim());

                tv = (TextView) findViewById(R.id.textViewRatingOutput);
                temp= tv.getText().toString().trim();
                movie.setRating(Integer.parseInt(temp));

                Spinner mySpinner=(Spinner) findViewById(R.id.spinnerGenre);
                temp= mySpinner.getSelectedItem().toString();
                movie.setGenre(temp);

                if(movie.getName().equals("") || movie.getName().length()>50){
                    Toast.makeText(AddMovieActivity.this,getResources().getString(R.string.error_name),Toast.LENGTH_SHORT).show();
                }else if(movie.getDescription().equals("") || movie.getDescription().length()>1000){
                    Toast.makeText(AddMovieActivity.this,getResources().getString(R.string.error_description),Toast.LENGTH_SHORT).show();
                }else if(movie.getYear()<1800 || movie.getYear()>2030){
                    Toast.makeText(AddMovieActivity.this,getResources().getString(R.string.error_year),Toast.LENGTH_SHORT).show();
                }else if(movie.getImdbLink().equals("")){
                    Toast.makeText(AddMovieActivity.this,getResources().getString(R.string.error_imdblink),Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent =new Intent();
                    intent.putExtra(MainActivity.VALUE_KEY,(Serializable)movie);
                    setResult(RESULT_OK,intent);

                    finish();
                }
            }
        });

    }


}

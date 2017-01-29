package com.mad.myfavoritemovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MovieByRatingActivity extends AppCompatActivity {
    ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
    int i=0,size=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_by_rating);
        //movieArrayList = (ArrayList<Movie>)getIntent().getSerializableExtra(MainActivity.SORT_KEY_YEAR);
        //Collections.sort(movieArrayList, Movie.MovieRating);
        final TextView tv =(TextView)findViewById(R.id.textViewIMDBOutput);
        ImageButton btn_first=(ImageButton)findViewById(R.id.imageButtonFirst);
        ImageButton btn_previous=(ImageButton)findViewById(R.id.imageButtonPrevious);
        ImageButton btn_next=(ImageButton)findViewById(R.id.imageButtonNext);
        ImageButton btn_last=(ImageButton)findViewById(R.id.imageButtonLast);
        Button btn_finish = (Button) findViewById(R.id.buttonFinishRating);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                //call function for display
                tv.setText(i+"");
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    Toast.makeText(MovieByRatingActivity.this, "No previous entry", Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    tv.setText(i+"");
                    //call function for display
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==size){
                    Toast.makeText(MovieByRatingActivity.this, "Last entry. No mor entry", Toast.LENGTH_SHORT).show();
                }else{
                    i++;
                    //call function for display
                    tv.setText(i+"");

                }
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=size;
                //call function for display
                tv.setText(i+"");
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    void display(int i){
        TextView tv;
        tv = (TextView)findViewById(R.id.textViewTitleOutput);
        //tv.setText();

    }
}

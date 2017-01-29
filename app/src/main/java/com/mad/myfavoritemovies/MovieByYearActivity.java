package com.mad.myfavoritemovies;


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.Collections;

public class MovieByYearActivity extends AppCompatActivity {
    ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

    int i=0,size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_by_year);

        movieArrayList = (ArrayList<Movie>) getIntent().getExtras().getSerializable(MainActivity.SORT_KEY_YEAR);
        Collections.sort(movieArrayList, Movie.MovieYear);
        size=movieArrayList.size()-1;
        displayYear(movieArrayList,i);

        Log.d("demo",i+"  size = "+size);
        ImageButton btn_first=(ImageButton)findViewById(R.id.imageButtonFirst);
        ImageButton btn_previous=(ImageButton)findViewById(R.id.imageButtonPrevious);
        ImageButton btn_next=(ImageButton)findViewById(R.id.imageButtonNext);
        ImageButton btn_last=(ImageButton)findViewById(R.id.imageButtonLast);
        Button btn_finish = (Button) findViewById(R.id.buttonFinishYear);
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    Toast.makeText(MovieByYearActivity.this, "First entry already displayed", Toast.LENGTH_SHORT).show();
                } else {

                    i = 0;
                    //call function for display
                    //tv.setText(i+"");
                    displayYear(movieArrayList, i);
                }
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    Toast.makeText(MovieByYearActivity.this, "No previous entry", Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    //tv.setText(i+"");
                    displayYear(movieArrayList,i);
                    //call function for display
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==size){
                    Toast.makeText(MovieByYearActivity.this, "Last entry. No more entry", Toast.LENGTH_SHORT).show();
                }else{
                    i++;
                    //call function for display
                    displayYear(movieArrayList,i);
                    //tv.setText(i+"");

                }
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == size) {
                    Toast.makeText(MovieByYearActivity.this, "Last entry already displayed", Toast.LENGTH_SHORT).show();
                } else {

                    i = size;
                    //call function for display
                    //tv.setText(i+"");
                    displayYear(movieArrayList, i);
                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void displayYear(ArrayList<Movie> mov, int i){
        //display text on the screen

        TextView tv;
        tv = (TextView)findViewById(R.id.textViewTitleOutput1);
        tv.setText(mov.get(i).getName());
        tv = (TextView)findViewById(R.id.textViewDescriptionOutput1);
        tv.setText(mov.get(i).getDescription());
        tv = (TextView)findViewById(R.id.textViewGenreOutput1);
        tv.setText(mov.get(i).getGenre());
        tv = (TextView)findViewById(R.id.textViewRatingOutput1);
        tv.setText(String.valueOf(mov.get(i).getRating())+"/5");
        tv = (TextView)findViewById(R.id.textViewYearOutput1);
        tv.setText(String.valueOf(mov.get(i).getYear()));
        tv = (TextView)findViewById(R.id.textViewIMDBOutput1);
        tv.setText(mov.get(i).getImdbLink());
    }
}

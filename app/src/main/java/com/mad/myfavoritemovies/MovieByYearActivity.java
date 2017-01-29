package com.mad.myfavoritemovies;

        import android.sax.TextElementListener;
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

    int i=0,size=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_by_year);
        //movieArrayList = (ArrayList<Movie>)getIntent().getSerializableExtra(MainActivity.SORT_KEY_YEAR);
        //Collections.sort(movieArrayList, Movie.MovieYear);

        ImageButton btn_first=(ImageButton)findViewById(R.id.imageButtonFirst);
        ImageButton btn_previous=(ImageButton)findViewById(R.id.imageButtonPrevious);
        ImageButton btn_next=(ImageButton)findViewById(R.id.imageButtonNext);
        ImageButton btn_last=(ImageButton)findViewById(R.id.imageButtonLast);
        Button btn_finish = (Button) findViewById(R.id.buttonFinishYear);
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                //call function for display
                //tv.setText(i+"");
                TextView tv;
                Log.d("demo","first clicked");
                tv = (TextView)findViewById(R.id.textViewTitleOutput1);
                tv.setText("name ");
                tv = (TextView)findViewById(R.id.textViewDescriptionOutput1);
                tv.setText("description"+i);
                tv = (TextView)findViewById(R.id.textViewGenreOutput1);
                tv.setText("Action");
                tv = (TextView)findViewById(R.id.textViewRatingOutput1);
                tv.setText(1+i+"/5");
                tv = (TextView)findViewById(R.id.textViewYearOutput1);
                tv.setText(2000+i);
                tv = (TextView)findViewById(R.id.textViewIMDBOutput1);
                tv.setText("link"+i);
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
                    displayYear(i);
                    //call function for display
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==size){
                    Toast.makeText(MovieByYearActivity.this, "Last entry. No mor entry", Toast.LENGTH_SHORT).show();
                }else{
                    i++;
                    //call function for display
                    displayYear(i);
                    //tv.setText(i+"");

                }
            }
        });

        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=size;
                //call function for display
                //tv.setText(i+"");
                displayYear(i);
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void displayYear(int i){
        //display text on the screen
        //Movie movie = new Movie("name"+i,"description"+i,"link"+i,"Action",2000+i,1+i);
        TextView tv;
        tv = (TextView)findViewById(R.id.textViewTitleOutput);
        tv.setText("name"+i);
        tv = (TextView)findViewById(R.id.textViewDescriptionOutput);
        tv.setText("description"+i);
        tv = (TextView)findViewById(R.id.textViewGenreOutput);
        tv.setText("Action");
        tv = (TextView)findViewById(R.id.textViewRatingOutput);
        tv.setText(1+i+"/5");
        tv = (TextView)findViewById(R.id.textViewYearOutput);
        tv.setText(2000+i);
        tv = (TextView)findViewById(R.id.textViewIMDBOutput);
        tv.setText("link"+i);
    }
}

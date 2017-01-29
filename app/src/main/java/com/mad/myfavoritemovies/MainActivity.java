/* Assignment: HW 02
   File Name: HW2_Group3.zip
   Names: Chinmay Rawool (800963582)
          Neha Kishor Dalvi (800970459)
*/

package com.mad.myfavoritemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE_ADD = 100;
    public static final int REQ_CODE_EDIT = 101;
    public static final String VALUE_KEY = "value";
    public static final String MOVIE_KEY = "movie object";
    public static final String EDIT_KEY ="Edited Movie";
    public static final String SORT_KEY_YEAR = "sortYear";
    public static final String SORT_KEY_RATING = "sortRating";
    ArrayList<Movie> movies=new ArrayList<Movie>();
    String[] movieNames;
    int movieIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<=2;i++){
            Movie m =new Movie("name"+i,"description"+i,"link"+i,"Action",2000+i,1+i);
            movies.add(m);
            movieNames = new String[movies.size()];
        }
        findViewById(R.id.buttonAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intent,REQ_CODE_ADD);
            }
        });

        findViewById(R.id.buttonEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(movies.size()!=0) {
                    movieNames = getMovieNames();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a movie")
                            .setItems(movieNames, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    movieIndex = which;
                                    Movie movie = movies.get(which);
                                    Intent intent = new Intent(MainActivity.this,EditMovieActivity.class);
                                    intent.putExtra(MOVIE_KEY,movie);
                                    startActivityForResult(intent,REQ_CODE_EDIT);
                                }
                            });

                    AlertDialog deleteAlert = builder.create();
                    deleteAlert.show();
                }
                else{
                    Toast.makeText(MainActivity.this,"No movies found!",Toast.LENGTH_LONG).show();
                }

            }
        });


        findViewById(R.id.buttonDeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                movieNames = getMovieNames();
                if(movies.size()!=0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a movie")
                            .setItems(movieNames, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String name=movies.get(which).getName();
                                    movies.remove(which);
                                    Toast.makeText(MainActivity.this, "Movie " + name + " was deleted", Toast.LENGTH_LONG).show();
                                    movieNames = new String[movies.size()];
                                }
                            });

                    AlertDialog deleteAlert = builder.create();
                    deleteAlert.show();
                }
                else{
                    Toast.makeText(MainActivity.this,"No movies found!",Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.buttonByYear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.mad.myfavoritemovies.intent.action.VIEW1");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(SORT_KEY_YEAR,movies);
                startActivity(intent);

            }
        });

        findViewById(R.id.buttonByRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.mad.myfavoritemovies.intent.action.VIEW2");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(SORT_KEY_RATING, movies);
                startActivity(intent);
            }
        });


    }

    public String[] getMovieNames(){
        for (int i = 0; i < movies.size(); i++) {
            movieNames[i] = movies.get(i).getName();
        }
        return movieNames;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE_ADD){
            if(resultCode==RESULT_OK){
                Movie mov= (Movie) data.getExtras().getSerializable(VALUE_KEY);
                if (mov != null) {
                    
                    movies.add(mov);
                    movieNames= new String[movies.size()];
                    Toast.makeText(this,"Movie added in Main Activity"+mov.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }else if(requestCode==REQ_CODE_EDIT){
            if(resultCode==RESULT_OK) {
                Movie mov = (Movie) data.getExtras().getSerializable(EDIT_KEY);
                movies.remove(movieIndex);
                movies.add(mov);
                movieNames= new String[movies.size()];
                Toast.makeText(MainActivity.this,"Changes saved!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

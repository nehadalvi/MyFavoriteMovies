package com.mad.myfavoritemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE_ADD = 100;
    public static final String VALUE_KEY = "value";
    public static final String SORT_KEY_YEAR = "sortYear";
    ArrayList<Movie> movies=new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<=2;i++){
            Movie m =new Movie("name"+i,"description"+i,"link"+i,"Action",2000+i,1+i);
            movies.add(m);
        }
        findViewById(R.id.buttonAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivityForResult(intent,REQ_CODE_ADD);
            }
        });

        findViewById(R.id.buttonDeleteMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] movieNames = new String[movies.size()];

                for (int i = 0; i < movies.size(); i++) {
                    movieNames[i] = movies.get(i).getName();
                }
                if(movies.size()!=0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a movie")
                            .setItems(movieNames, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    movies.remove(which);
                                    Toast.makeText(MainActivity.this, "Movie " + movies.get(which).getName() + " was deleted", Toast.LENGTH_LONG).show();
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
                //intent.putExtra(SORT_KEY_YEAR, ArrayList<Movie>movies);
                startActivity(intent);

            }
        });

        findViewById(R.id.buttonByRating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.mad.myfavoritemovies.intent.action.VIEW2");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                //intent.putExtra(SORT_KEY_YEAR, ArrayList<Movie>movies);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_CODE_ADD){
            if(resultCode==RESULT_OK){
                Movie mov= (Movie) data.getExtras().getSerializable(VALUE_KEY);
                if (mov != null) {
                    movies.add(mov);
                    Toast.makeText(this,"Movie added in Main Activity"+mov.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

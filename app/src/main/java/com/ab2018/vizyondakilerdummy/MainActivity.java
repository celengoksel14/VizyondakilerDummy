package com.ab2018.vizyondakilerdummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies ;
    ListView movieLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Log.d("Oncreate","list initialization");
            movieLv = findViewById(R.id.movie_listView);

            movies = new ArrayList<>();
            MovieAdapter adapter = new MovieAdapter(this,R.layout.movie_item,movies);
            movieLv.setAdapter(adapter);

            movieLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

            movieLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return false;
                }
            });


        }

        /*ArrayList<Movie> createDummyData()
        {
            movies = new ArrayList<Movie>();
            movies.add(0,new Movie(1,"Dunkirk","abc","en",R.drawable.dunkirk_px,8.8));
            movies.add(1,new Movie(2,"IT","abc","en",R.drawable.it,7.0));

            return movies;
        }*/

}

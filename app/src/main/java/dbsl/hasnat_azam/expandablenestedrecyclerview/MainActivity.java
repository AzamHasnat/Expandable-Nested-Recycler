package dbsl.hasnat_azam.expandablenestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, List<Movie>> categoryMap;
    private List<Movie> movieList;
    private ExpandablePlaceHolderView expandablePlaceHolderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        categoryMap = new HashMap<>();
        expandablePlaceHolderView = (ExpandablePlaceHolderView) findViewById(R.id.expandablePlaceHolder);

        loadData();

        expandablePlaceHolderView.setOnClickListener(view ->
                Toast.makeText(getApplicationContext(),"Clixcked", view.getId()).show());
    }


    private void loadData(){
        movieList.add(new Movie("List1","List1","","List1 List1"));
        movieList.add(new Movie("List2","List2","","List2 List2"));
        movieList.add(new Movie("List3","List3","","List3 List3"));
        getHeaderAndChild(movieList);

    }

    private void getHeaderAndChild(List<Movie> movieList){

        for (Movie movie : movieList ){
            List<Movie> movieList1 = categoryMap.get(movie.getCategoty());
            if(movieList1 == null){
                movieList1 = new ArrayList<>();
            }
            movieList1.add(movie);
            //movieList.add()
            categoryMap.put(movie.getName(),movieList1);
        }

        Log.d("Map",categoryMap.toString());
        Iterator it = categoryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Log.d("Key", pair.getKey().toString());
            expandablePlaceHolderView.addView(new HeaderView(this, pair.getKey().toString()));
            List<Movie> movieList1 = (List<Movie>) pair.getValue();
            for (Movie movie : movieList1){
                expandablePlaceHolderView.addView(new ChildView(this, movie));
            }
            it.remove();
        }
    }

}
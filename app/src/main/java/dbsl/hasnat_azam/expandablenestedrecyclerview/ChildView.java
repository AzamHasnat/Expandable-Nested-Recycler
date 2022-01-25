package dbsl.hasnat_azam.expandablenestedrecyclerview;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.child_layout)
public class ChildView {
    private static String TAG ="ChildView";

    @View(R.id.child_name)
    TextView textViewChild;


    @View(R.id.child_image)
    ImageView childImage;

    private Context mContext;
    private Movie movie;

    public ChildView(Context mContext, Movie movie) {
        this.mContext = mContext;
        this.movie = movie;
    }

    @Resolve
    private void onResolve(){
        textViewChild.setText(movie.getName());
        Glide.with(mContext).load(movie.getImageUrl()).into(childImage);
        textViewChild.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(mContext, movie.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
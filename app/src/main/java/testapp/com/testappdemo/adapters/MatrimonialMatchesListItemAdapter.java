package testapp.com.testappdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import testapp.com.testappdemo.R;
import testapp.com.testappdemo.models.Result;


public class MatrimonialMatchesListItemAdapter extends RecyclerView.Adapter<MatrimonialMatchesListItemAdapter.MatrimonialHolder> {

    List<Result> matrimonialMatchesList;
    Context context;
    private String TAG=MatrimonialMatchesListItemAdapter.class.getName();

    public MatrimonialMatchesListItemAdapter(List<Result> matrimonialMatchesList, Context context) {
        this.matrimonialMatchesList = matrimonialMatchesList;
        this.context = context;
    }

    @Override
    public MatrimonialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_matrimonial_matches_list_item,parent,false);
        MatrimonialHolder mh = new MatrimonialHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MatrimonialHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: "+matrimonialMatchesList.get(position).getName().getTitle());
        holder.tvTitle.setText(matrimonialMatchesList.get(position).getName().getTitle()+". "+matrimonialMatchesList.get(position).getName().getFirst()+" "+matrimonialMatchesList.get(position).getName().getLast());
        holder.tvOverview.setText(matrimonialMatchesList.get(position).getEmail());
        holder.tvReleaseDate.setText(matrimonialMatchesList.get(position).getPhone());
        Glide.with(context).load(matrimonialMatchesList.get(position).getPicture().getMedium()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return matrimonialMatchesList.size();
    }

    public class MatrimonialHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvOverview,tvReleaseDate;
        ImageView ivMovie;

        public MatrimonialHolder(View v) {
            super(v);
            tvTitle =  v.findViewById(R.id.tvTitle);
            tvOverview = v.findViewById(R.id.tvOverView);
            tvReleaseDate = v.findViewById(R.id.tvReleaseDate);
            ivMovie = v.findViewById(R.id.ivMovie);
        }
    }
}

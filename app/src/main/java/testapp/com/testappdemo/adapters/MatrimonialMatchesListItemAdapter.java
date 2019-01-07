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

import butterknife.BindView;
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
    public void onBindViewHolder(MatrimonialHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: "+matrimonialMatchesList.get(position).getName().getTitle());
        holder.tvName.setText(matrimonialMatchesList.get(position).getName().getTitle()+". "+matrimonialMatchesList.get(position).getName().getFirst()+" "+matrimonialMatchesList.get(position).getName().getLast());
        holder.emailID.setText(matrimonialMatchesList.get(position).getEmail());
        holder.tvPhoneNo.setText(matrimonialMatchesList.get(position).getPhone());
        Glide.with(context).load(matrimonialMatchesList.get(position).getPicture().getMedium()).into(holder.ivPicture);

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                matrimonialMatchesList.remove(position);  // remove the item from list
               //
                // notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return matrimonialMatchesList.size();
    }

    public class MatrimonialHolder extends RecyclerView.ViewHolder {

       // @BindView(R.id.tvName)
        TextView tvName;

       // @BindView(R.id.emailID)
        TextView emailID;

        //@BindView(R.id.tvPhoneNo)
        TextView tvPhoneNo;

      //  @BindView(R.id.ivPicture)
        ImageView ivPicture;

        //  @BindView(R.id.ivPicture)
        ImageView ivDelete;



        public MatrimonialHolder(View v) {
            super(v);
            tvName =  v.findViewById(R.id.tvName);
            emailID = v.findViewById(R.id.emailID);
            tvPhoneNo = v.findViewById(R.id.tvPhoneNo);
             ivPicture = v.findViewById(R.id.ivPicture);
            ivDelete = v.findViewById(R.id.ivDelete);
        }
    }
}

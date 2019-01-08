package testapp.com.testappdemo.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import butterknife.BindView;
import testapp.com.testappdemo.R;
import testapp.com.testappdemo.models.MaterimonialDetailModel;
import testapp.com.testappdemo.models.Result;


public class MatrimonialMatchesListItemAdapter extends RecyclerView.Adapter<MatrimonialMatchesListItemAdapter.MatrimonialHolder> {

    List<MaterimonialDetailModel> matrimonialMatchesList;
    Context context;
    private String TAG=MatrimonialMatchesListItemAdapter.class.getName();
    private static int currentPosition = 0;

    public MatrimonialMatchesListItemAdapter(List<MaterimonialDetailModel> matrimonialMatchesList, Context context) {
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
    public void onBindViewHolder(final MatrimonialHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: "+matrimonialMatchesList.get(position).getEmail());
        holder.tvName.setText(matrimonialMatchesList.get(position).getFirstname()+". "+matrimonialMatchesList.get(position).getMiddlename()+" "+matrimonialMatchesList.get(position).getLastname());
        holder.emailID.setText(matrimonialMatchesList.get(position).getEmail());
        holder.tvPhoneNo.setText(matrimonialMatchesList.get(position).getPhoneno());
        holder.tvState.setText(matrimonialMatchesList.get(position).getState());
        holder.tvStreet.setText(matrimonialMatchesList.get(position).getStreet());
        holder.tvCity.setText(matrimonialMatchesList.get(position).getCity());
        holder.tvCellNo.setText(matrimonialMatchesList.get(position).getCellno());
        holder.tvAge.setText(matrimonialMatchesList.get(position).getAge());
        holder.tvGender.setText(matrimonialMatchesList.get(position).getGender());
        Glide.with(context).load(matrimonialMatchesList.get(position).getPicture()).into(holder.ivPicture);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.anim_slide_down);

            //toggling visibility
            holder.llExpandView.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.llExpandView.startAnimation(slideDown);

            holder.ivExpandLessMore.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
        else
        {
            //toggling visibility
            holder.llExpandView.setVisibility(View.GONE);
            holder.ivExpandLessMore.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                matrimonialMatchesList.remove(position);  // remove the item from list

                Animation rotation = AnimationUtils.loadAnimation(context, R.anim.anim_slide_out_right);
                rotation.setFillAfter(true);
                rotation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        notifyDataSetChanged();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                holder.cvItemView.startAnimation(rotation);
            }
        });

        //
        holder.ivExpandLessMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return matrimonialMatchesList.size();
    }

    public class MatrimonialHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvStreet;
        TextView tvCity;
        TextView tvState;
        TextView tvAge;
        TextView tvCellNo;
        TextView tvGender;
        TextView emailID;
        TextView tvPhoneNo;
        ImageView ivPicture;
        ImageView ivDelete;
        ImageView ivExpandLessMore;
        CardView cvItemView;
        LinearLayout llExpandView;

        public MatrimonialHolder(View v) {
            super(v);
            tvName           =  v.findViewById(R.id.tvName);
            tvStreet         =  v.findViewById(R.id.tvStreet);
            tvCity           =  v.findViewById(R.id.tvCity);
            tvState          =  v.findViewById(R.id.tvState);
            tvAge            =  v.findViewById(R.id.tvAge);
            tvCellNo         =  v.findViewById(R.id.tvCellNo);
            tvGender         =  v.findViewById(R.id.tvGender);
            emailID          = v.findViewById(R.id.emailID);
            tvPhoneNo        = v.findViewById(R.id.tvPhoneNo);
            ivPicture        = v.findViewById(R.id.ivPicture);
            ivDelete         = v.findViewById(R.id.ivDelete);
            cvItemView       = v.findViewById(R.id.cvItemView);
            ivExpandLessMore = v.findViewById(R.id.ivExpandLessMore);
            llExpandView     = v.findViewById(R.id.llExpandView);
        }
    }
}

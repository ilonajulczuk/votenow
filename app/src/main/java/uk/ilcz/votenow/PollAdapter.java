package uk.ilcz.votenow;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.PollViewHolder> {

    private List<Poll> pollList;
    private static final String TAG = "PollAdapter";


    public PollAdapter(List<Poll> polls) {
        Log.v(TAG, "In poll adapter, created");
        pollList = polls;
        Log.v(TAG, "In poll adapter, poll list size: " + Integer.toString(pollList.size()));
    }


//    @Override
//    public void onBindViewHolder(PollViewHolder pollViewHolder, int i) {
//
//        Log.v(TAG, "In poll adapter, bind");
//        Poll ci = pollList.get(i);
//        pollViewHolder.vTitle.setText(ci.title);
//        pollViewHolder.vState.setText(ci.state);
//        pollViewHolder.vCreator.setText(ci.creator);
//    }

    @Override
    public void onBindViewHolder(PollViewHolder pollViewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        Log.v(TAG, "In poll adapter, bind");
        Poll ci = pollList.get(position);
        pollViewHolder.vTitle.setText(ci.title);
        pollViewHolder.vState.setText(ci.state);
        pollViewHolder.vCreator.setText(ci.creator);
    }

    @Override
    public PollViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        Log.v(TAG, "In poll adapter, create view holder");
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new PollViewHolder(itemView);
    }

    public static class PollViewHolder extends RecyclerView.ViewHolder {

        protected TextView vState;
        protected TextView vCreator;
        protected TextView vTitle;

        public PollViewHolder(View v) {
            super(v);
            Log.v(TAG, "in poll view holder");
            vState = (TextView)  v.findViewById(R.id.state);
            vCreator = (TextView)  v.findViewById(R.id.creator);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }

    @Override
    public int getItemCount() {
        Log.v(TAG, "Count: " + Integer.toString(pollList.size()));
        return pollList.size();
    }
}

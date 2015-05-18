package uk.ilcz.votenow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.PollViewHolder> {

    private List<Poll> contactList;
    private Activity activity;

    public PollAdapter(List<Poll> contactList, Activity activity) {
        this.contactList = contactList;
        this.activity = activity;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(PollViewHolder pollViewHolder, int i) {
        Poll ci = contactList.get(i);
        pollViewHolder.vTitle.setText(ci.title);
        pollViewHolder.vState.setText(ci.state);
        pollViewHolder.vCreator.setText(ci.creator);

        pollViewHolder.setClickListener(new PollViewHolder.ClickListener() {
            @Override
            public void onClick(View v, int pos, boolean isLongClick) {
                if (isLongClick) {
                    // View v at position pos is long-clicked.
                } else {
                    // View v at position pos is clicked.
                }

                Poll ci = contactList.get(pos);


                Intent detailActivityIntent = new Intent(activity, PollDetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, ci.id);

                activity.startActivity(detailActivityIntent);
            }
        });

    }

    @Override
    public PollViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new PollViewHolder(itemView);
    }

    public static class PollViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        protected TextView vState;
        protected TextView vCreator;
        protected TextView vTitle;


        private ClickListener clickListener;

        public PollViewHolder(View v) {
            super(v);
            vState = (TextView)  v.findViewById(R.id.state);
            vCreator = (TextView)  v.findViewById(R.id.creator);
            vTitle = (TextView) v.findViewById(R.id.title);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        /* Interface for handling clicks - both normal and long ones. */
        public interface ClickListener {

            /**
             * Called when the view is clicked.
             *
             * @param v view that is clicked
             * @param position of the clicked item
             * @param isLongClick true if long click, false otherwise
             */
            public void onClick(View v, int position, boolean isLongClick);

        }

        /* Setter for listener. */
        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {

            // If not long clicked, pass last variable as false.
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {

            // If long clicked, passed last variable as true.
            clickListener.onClick(v, getPosition(), true);
            return true;
        }

    }
}

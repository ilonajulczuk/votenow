package uk.ilcz.votenow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PollAdapter extends RecyclerView.Adapter<PollAdapter.PollViewHolder> {

    private List<Poll> contactList;

    public PollAdapter(List<Poll> contactList) {
        this.contactList = contactList;
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
    }

    @Override
    public PollViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
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
            vState = (TextView)  v.findViewById(R.id.state);
            vCreator = (TextView)  v.findViewById(R.id.creator);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}

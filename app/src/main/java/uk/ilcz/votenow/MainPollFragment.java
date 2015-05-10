package uk.ilcz.votenow;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;


public class MainPollFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";

    protected RecyclerView mRecyclerView;
    protected PollAdapter mAdapter;
    protected LinearLayoutManager mLayoutManager;
    protected List<Poll> mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Inside create");
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "Inside create view");
        View rootView = inflater.inflate(R.layout.fragment_poll, container, false);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PollAdapter(mDataset);
        Log.v(TAG, "setting adapter");
        mRecyclerView.setAdapter(mAdapter);

        Log.v(TAG, "adapter set");
        // END_INCLUDE(initializeRecyclerView)
        return rootView;
    }


    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = createList(20);
        Log.v(TAG, "created dataset" + Integer.toString(mDataset.size()));
    }

    private List<Poll> createList(int size) {

        List<Poll> result = new ArrayList<Poll>();
        for (int i=1; i <= size; i++) {
            Poll poll = new Poll();
            poll.title = Poll.TITLE_PREFIX + i;
            poll.closesAt = "Friday";
            poll.createdAt = "Today";
            poll.creator = "Justyna";
            poll.state = "Open";
            poll.updatedAt = "Today";

            result.add(poll);
        }

        return result;
    }
}


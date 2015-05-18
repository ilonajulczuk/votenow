package uk.ilcz.votenow;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.syncano.library.Syncano;
import com.syncano.library.annotation.SyncanoClass;
import com.syncano.library.annotation.SyncanoField;
import com.syncano.library.api.Response;
import com.syncano.library.callbacks.SyncanoCallback;
import com.syncano.library.data.SyncanoObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    private Syncano syncano;

    @SyncanoClass(name = "book")
    public class Book extends SyncanoObject {

        public static final String FIELD_TITLE = "title";
        public static final String FIELD_SUBTITLE = "subtitle";

        @SyncanoField(name = FIELD_TITLE)
        public String title;

        @SyncanoField(name = FIELD_SUBTITLE)
        public String subtitle;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        syncano = new Syncano("90c7acac80305381e25a4b635c9211d38fd42918", "superduperinstance");
        SyncanoCallback<List<Book>> callback = new SyncanoCallback<List<Book>>() {
            @Override
            public void success(Response<List<Book>> response, List<Book> result) {
                // Request succeed.
                Log.v(LOG_TAG, "Success in syncano callback!");
                Log.v(LOG_TAG, Integer.toString(result.size()));
            }

            @Override
            public void failure(Response<List<Book>> response) {
                // Something went wrong. Check error codes in response object.
                Log.v(LOG_TAG, "Failure in syncano callback!");
            }
        };

        syncano.getObjects(Book.class).sendAsync(callback);

        setContentView(R.layout.activity_main);
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        PollAdapter pa = new PollAdapter(createList(20), this);
        recList.setAdapter(pa);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
            poll.id = Integer.toString(i);

            result.add(poll);
        }

        return result;
    }
}
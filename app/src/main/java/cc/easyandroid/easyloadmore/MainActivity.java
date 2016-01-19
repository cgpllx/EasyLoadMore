package cc.easyandroid.easyloadmore;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import cc.easyandroid.easyloadmore.core.SimpleAdapter;
import cc.easyandroid.easyloadmore.widget.LoadMoreContainer;
import cc.easyandroid.easyloadmore.widget.LoadMoreHandler;
import cc.easyandroid.easyloadmore.widget.LoadMoreListViewContainer;
import cc.easyandroid.easyloadmore.widget.LoadMoreUIHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final  SimpleAdapter simpleAdapter = new SimpleAdapter(this);
         ListView listview = (ListView) findViewById(R.id.listview);
        simpleAdapter.addAll("","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","");
        LoadMoreListViewContainer loadMoreListViewContainer= (LoadMoreListViewContainer) findViewById(R.id.loadMoreListViewContainer);
        loadMoreListViewContainer.useDefaultFooter();
        listview.setAdapter(simpleAdapter);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(final LoadMoreContainer loadMoreContainer) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                simpleAdapter.addAll("","","","","","","","","","");
                                loadMoreContainer.loadMoreFinish(false,true);
                            }
                        });
                    }
                }.start();


            }
        });


        loadMoreListViewContainer.setAutoLoadMore(true);
        loadMoreListViewContainer.setShowLoadingForFirstPage(true);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

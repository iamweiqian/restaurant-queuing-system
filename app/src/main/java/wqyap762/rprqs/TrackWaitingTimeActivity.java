package wqyap762.rprqs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;


public class TrackWaitingTimeActivity extends AppCompatActivity {

    ListView timeListView;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_waiting_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        if (spinner != null) {
            spinner.setVisibility(View.VISIBLE);
        }

        timeListView = (ListView) findViewById(R.id.timeListView);

        getOrderList();

        swipeToRefresh();
    }

    public void getOrderList() {
        final String hpno = SaveSharedPreferences.getPrefHpno(TrackWaitingTimeActivity.this);
        final TimeAdapter timeAdapter = new TimeAdapter(this, R.layout.view_row_layout);
        timeListView.setAdapter(timeAdapter);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray orderList = jsonResponse.getJSONArray("OrderList");

                    for (int i = 0; i < orderList.length(); i++) {
                        JSONObject object = orderList.getJSONObject(i);
                        final String order_id = object.getString("order_id");
                        String food_name = object.getString("food_name");
                        String payment_status = object.getString("payment_status");
                        String ordered_on = object.getString("ordered_on");
                        StringTokenizer tokenizer = new StringTokenizer(ordered_on);
                        String ordered_date = tokenizer.nextToken();
                        String ready_on = object.getString("ready_on");
                        StringTokenizer tokenizer1 = new StringTokenizer(ready_on);
                        String ready_date = tokenizer1.nextToken();
                        String ready_time = tokenizer1.nextToken();

                        Time time = new Time(order_id, food_name, payment_status, ordered_date, ready_time);
                        timeAdapter.add(time);

                        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ViewGroup vg = (ViewGroup) view;
                                TextView textView = (TextView) vg.findViewById(R.id.orderIdText);
                                Intent intent = new Intent(TrackWaitingTimeActivity.this, OrderInfomationActivity.class);
                                intent.putExtra("order_id", textView.getText().toString());
                                startActivity(intent);
                            }
                        });
                    }
                    timeAdapter.notifyDataSetChanged();
                    spinner.setVisibility(View.GONE);
                    swipeContainer.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        TrackWaitingTimeRequest trackWaitingTimeRequest = new TrackWaitingTimeRequest(hpno, responseListener);
        RequestQueue queue = Volley.newRequestQueue(TrackWaitingTimeActivity.this);
        queue.add(trackWaitingTimeRequest);
    }

    public void swipeToRefresh() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrderList();
            }
        });
        swipeContainer.setColorSchemeColors(Color.GRAY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track_waiting_time, menu);
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

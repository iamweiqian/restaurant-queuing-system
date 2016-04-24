package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


public class ViewOrderActivity extends ActionBarActivity {

    ListView orderListView;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        orderListView = (ListView) findViewById(R.id.orderListView);

        getOrderList();

        swipeToRefresh();
    }

    public void getOrderList() {
        final String hpno = SaveSharedPreferences.getPrefHpno(ViewOrderActivity.this);
        final OrderAdapter orderAdapter = new OrderAdapter(this, R.layout.row_layout);
        orderListView.setAdapter(orderAdapter);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray orderList = jsonResponse.getJSONArray("OrderList");

                    if (orderList.length() != 0) {
                        for (int i = 0; i < orderList.length(); i++) {
                            JSONObject object = orderList.getJSONObject(i);
                            final String order_id = object.getString("order_id");
                            String food_name = object.getString("food_name");
                            String payment_status = object.getString("payment_status");
                            String ordered_on = object.getString("ordered_on");
                            StringTokenizer tokenizer = new StringTokenizer(ordered_on);
                            String ordered_date = tokenizer.nextToken();

                            Order order = new Order(order_id, food_name, payment_status, ordered_date);
                            orderAdapter.add(order);

                            orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ViewGroup vg = (ViewGroup) view;
                                    TextView textView = (TextView) vg.findViewById(R.id.orderIdText);
                                    Intent intent = new Intent(ViewOrderActivity.this, OrderInfomationActivity.class);
                                    intent.putExtra("order_id", textView.getText().toString());
                                    startActivity(intent);
                                }
                            });

                        }
                        orderAdapter.notifyDataSetChanged();
                        spinner.setVisibility(View.GONE);
                        swipeContainer.setRefreshing(false);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewOrderActivity.this);
                        builder.setMessage("Order(s) Failed to be retrieved")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ViewOrderRequest viewOrderRequest = new ViewOrderRequest(hpno, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ViewOrderActivity.this);
        queue.add(viewOrderRequest);
    }

    public void swipeToRefresh() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrderList();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_green_dark);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_order, menu);
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

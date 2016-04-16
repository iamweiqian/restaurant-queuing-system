package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class OrderInfomationActivity extends ActionBarActivity {

    public static final String DEFAULT = "N/A";
    TextView orderIdText, nameText, hpnoText, foodNameText, quantityText, totalPriceText, paymentStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_infomation);

        orderIdText = (TextView) findViewById(R.id.orderIdText);
        nameText = (TextView) findViewById(R.id.nameText);
        hpnoText = (TextView) findViewById(R.id.hpnoText);
        foodNameText = (TextView) findViewById(R.id.foodNameText);
        quantityText = (TextView) findViewById(R.id.quantityText);
        totalPriceText = (TextView) findViewById(R.id.totalPriceText);
        paymentStatusText = (TextView) findViewById(R.id.paymentStatusText);

        getOrderInformation();

        // customer order quit button
        Button orderDone = (Button) findViewById(R.id.orderDoneButton);
        orderDone.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToViewOrderActivity();
                    }
                }
        );
    }

    public void getOrderInformation() {
        Intent intent = getIntent();
        final String order_id = intent.getStringExtra("order_id");
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", DEFAULT);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String name = jsonResponse.getString("name");
                        String hpno = jsonResponse.getString("hpno");
                        String quantity = jsonResponse.getString("quantity");
                        String total_price = jsonResponse.getString("total_price");
                        String payment_status = jsonResponse.getString("payment_status");
                        String food_name = jsonResponse.getString("food_name");

                        orderIdText.setText(order_id);
                        nameText.setText(name);
                        hpnoText.setText(hpno);
                        quantityText.setText(quantity);
                        totalPriceText.setText(total_price);
                        paymentStatusText.setText(payment_status);
                        foodNameText.setText(food_name);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(OrderInfomationActivity.this);
                        builder.setMessage("Item Retrieve Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        OrderInformationRequest orderInformationRequest = new OrderInformationRequest(order_id, username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(OrderInfomationActivity.this);
        queue.add(orderInformationRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_infomation, menu);
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

    public void goToViewOrderActivity() {
        Intent intent = new Intent(this, ViewOrderActivity.class);
        startActivity(intent);
    }
}

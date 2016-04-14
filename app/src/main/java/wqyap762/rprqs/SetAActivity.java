package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class SetAActivity extends ActionBarActivity {

    TextView basicPriceText, totalPriceText;
    EditText setAQuantityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_a);

        basicPriceText = (TextView) findViewById(R.id.basicPriceText);
        totalPriceText = (TextView) findViewById(R.id.totalPriceText);
        setAQuantityText = (EditText) findViewById(R.id.setAQuantityText);

        totalPriceCalculated();

        // customer order
        Button setAOrderButton = (Button) findViewById(R.id.setAOrderButton);
        setAOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(final View v) {
                        if (TextUtils.isEmpty(setAQuantityText.getText().toString())) {
                            setAQuantityText.setError("Please enter quantity.");
                            return;
                        } else {
                            final AlertDialog.Builder orderConfirm = new AlertDialog.Builder(SetAActivity.this);

                            // setting dialog title
                            orderConfirm.setTitle("Confirm Order");

                            // setting dialog message
                            orderConfirm.setMessage("Do you want to continue?");

                            // setting icon to dialog
                            //orderConfirm.setIcon(R.drawable.save);

                            // setting positive "Proceed" button
                            orderConfirm.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // user press Proceed button. Write logic here
                                    setAButtonClicked(v);

                                    AlertDialog.Builder orderDone = new AlertDialog.Builder(SetAActivity.this);

                                    // setting dialog title
                                    orderDone.setTitle("Order Successfully!");

                                    // setting dialog message
                                    orderDone.setMessage("Your order has been placed successfully.");

                                    // setting icon to dialog
                                    //orderConfirm.setIcon(R.drawable.save);

                                    // setting positive "Okay" button
                                    orderDone.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // user press Proceed button. Write logic here
                                            goToOrderInfoCustomerActivity();
                                        }
                                    });
                                    orderDone.show();
                                }
                            });

                            // setting neutral "Cancel" button
                            orderConfirm.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // user press Cancel button. Write login here
                                    dialog.dismiss();
                                }
                            });

                            orderConfirm.show();
                        }
                    }
                }
        );
    }

    public void totalPriceCalculated() {
        setAQuantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Double basicPrice = Double.parseDouble(basicPriceText.getText().toString());
                Double quantity = Double.parseDouble(setAQuantityText.getText().toString());
                totalPriceText.setText("RM " + basicPrice * quantity);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setAButtonClicked (View v) {
        Intent intent = getIntent();
        final int total_price = Integer.parseInt(totalPriceText.getText().toString());
        final int quantity = Integer.parseInt(setAQuantityText.getText().toString());
        final String payment_status = "Unpaid";
        final String username = intent.getStringExtra("username");
        final String menu_id = "S001";

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        goToOrderInfoCustomerActivity();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SetAActivity.this);
                        builder.setMessage("Order Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        OrderRequest orderRequest = new OrderRequest(total_price, quantity, payment_status, username, menu_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(SetAActivity.this);
        queue.add(orderRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_a, menu);
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

    public void goToOrderInfoCustomerActivity() {
        Intent intent = new Intent(this, OrderInfoCustomerActivity.class);
        startActivity(intent);
    }
}

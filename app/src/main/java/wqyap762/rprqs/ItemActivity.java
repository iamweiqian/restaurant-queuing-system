package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class ItemActivity extends ActionBarActivity {

    public static final String DEFAULT = "N/A";
    TextView foodNameText, descriptionText, basicPriceText, totalPriceText;
    EditText setAQuantityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        foodNameText = (TextView) findViewById(R.id.foodNameText);
        descriptionText = (TextView) findViewById(R.id.descriptionText);
        basicPriceText = (TextView) findViewById(R.id.basicPriceText);
        totalPriceText = (TextView) findViewById(R.id.totalPriceText);
        setAQuantityText = (EditText) findViewById(R.id.setAQuantityText);

        getMenu();
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
                            final AlertDialog.Builder orderConfirm = new AlertDialog.Builder(ItemActivity.this);

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

    public void getMenu() {
        Intent intent = getIntent();
        String menu_id = intent.getStringExtra("menu_id");

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String food_name = jsonResponse.getString("food_name");
                        String description = jsonResponse.getString("description");
                        String basic_price = jsonResponse.getString("basic_price");

                        foodNameText.setText(food_name);
                        descriptionText.setText(description);
                        basicPriceText.setText(basic_price);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);
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

        MenuRequest menuRequest = new MenuRequest(menu_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ItemActivity.this);
        queue.add(menuRequest);
    }

    public void totalPriceCalculated() {
        setAQuantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(setAQuantityText.getText().toString())) {
                    Double basicPrice = Double.parseDouble(basicPriceText.getText().toString());
                    Double quantity = Double.parseDouble(setAQuantityText.getText().toString());
                    totalPriceText.setText(String.valueOf(basicPrice * quantity));
                } else {
                    setAQuantityText.setError("Please enter quantity.");
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setAButtonClicked (View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        final Double total_price = Double.parseDouble(totalPriceText.getText().toString());
        final int quantity = Integer.parseInt(setAQuantityText.getText().toString());
        final String payment_status = "Unpaid";
        final String username = sharedPreferences.getString("username", DEFAULT);
        final String menu_id = intent.getStringExtra("menu_id");

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        orderDone();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);
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
        RequestQueue queue = Volley.newRequestQueue(ItemActivity.this);
        queue.add(orderRequest);
    }

    public void orderDone() {
        AlertDialog.Builder orderDone = new AlertDialog.Builder(ItemActivity.this);

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
                goToViewOrderActivity();
            }
        });
        orderDone.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
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

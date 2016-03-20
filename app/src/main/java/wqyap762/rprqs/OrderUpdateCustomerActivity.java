package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class OrderUpdateCustomerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update_customer);

        // collection time empty
        final EditText collectionTimeText = (EditText) findViewById(R.id.collectionTimeText);
        collectionTimeText.setOnClickListener(
                new EditText.OnClickListener() {
                    public void onClick(View v) {
                        collectionTimeText.setText("");
                    }
                }
        );

        // customer order
        Button updateCustomerButton = (Button) findViewById(R.id.updateCustomerButton);
        updateCustomerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder orderConfirm = new AlertDialog.Builder(OrderUpdateCustomerActivity.this);

                        // setting dialog title
                        orderConfirm.setTitle("Confirm Update");

                        // setting dialog message
                        orderConfirm.setMessage("Do you want to continue?");

                        // setting icon to dialog
                        //orderConfirm.setIcon(R.drawable.save);

                        // setting positive "Proceed" button
                        orderConfirm.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // user press Proceed button. Write logic here
                                AlertDialog.Builder updateDone = new AlertDialog.Builder(OrderUpdateCustomerActivity.this);

                                // setting dialog title
                                updateDone.setTitle("Update Successfully!");

                                // setting dialog message
                                updateDone.setMessage("Your order has been updated successfully.");

                                // setting icon to dialog
                                //orderConfirm.setIcon(R.drawable.save);

                                // setting positive "Okay" button
                                updateDone.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // user press Proceed button. Write logic here
                                        goToOrderInfoCustomerActivity();
                                    }
                                });
                                updateDone.show();
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
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_update_customer, menu);
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
        finish();
        startActivity(intent);
    }
}

package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SetAActivity extends ActionBarActivity {
    String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_a);

        // customer order
        Button customerOrderButton = (Button) findViewById(R.id.customerOrderButton);
        customerOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EditText quantityText = (EditText) findViewById(R.id.quantityText);
                        if (TextUtils.isEmpty(quantityText.getText().toString())) {
                            quantityText.setError("Please enter quantity.");
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

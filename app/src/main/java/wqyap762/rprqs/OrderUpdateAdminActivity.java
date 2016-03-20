package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class OrderUpdateAdminActivity extends ActionBarActivity {
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update_admin);

        // radio group change
        /*RadioGroup paymentStatusUpdate = (RadioGroup) findViewById(R.id.paymentStatusGroup);
        paymentStatusUpdate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.paidButton:
                        status = "Paid";
                        break;
                    case R.id.unpaidButton:
                        status = "Unpaid";
                        break;
                }
            }
        });*/

        // admin order update button
        Button updateAdminButton = (Button) findViewById(R.id.updateAdminButton);
        updateAdminButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder orderConfirm = new AlertDialog.Builder(OrderUpdateAdminActivity.this);

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
                                //final TextView paymentStatus = (TextView) findViewById(R.id.paymentStatus);
                                //paymentStatus.setText(status);
                                AlertDialog.Builder updateDone = new AlertDialog.Builder(OrderUpdateAdminActivity.this);

                                // setting dialog title
                                updateDone.setTitle("Update Successfully!");

                                // setting dialog message
                                updateDone.setMessage("Order has been updated successfully.");

                                // setting icon to dialog
                                //orderConfirm.setIcon(R.drawable.save);

                                // setting positive "Okay" button
                                updateDone.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // user press Proceed button. Write logic here
                                        goToOrderInfoAdminActivity();
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
        getMenuInflater().inflate(R.menu.menu_order_update_admin, menu);
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

    public void goToOrderInfoAdminActivity() {
        Intent intent = new Intent(this, OrderInfoAdminActivity.class);
        finish();
        startActivity(intent);
    }
}

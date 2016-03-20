package wqyap762.rprqs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AdminMainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        // manage order
        Button manageOrderButton = (Button) findViewById(R.id.manageOrderButton);
        manageOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToManageOrderAdminActivity();
                    }
                }
        );

        // track waiting time
        Button trackWaitingTimeAdminButton = (Button) findViewById(R.id.trackWaitingTimeAdminButton);
        trackWaitingTimeAdminButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToTrackWaitingTimeAdminActivity();
                    }
                }
        );

        // view report
        Button viewReportButton = (Button) findViewById(R.id.viewReportButton);
        viewReportButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToViewReportActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_main, menu);
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

        if (id == R.id.action_logout) {
            AlertDialog.Builder logoutButton = new AlertDialog.Builder(AdminMainActivity.this);

            // setting dialog title
            logoutButton.setTitle("Log out");

            // setting dialog message
            logoutButton.setMessage("Are you sure to log out?");

            // setting icon to dialog
            //orderConfirm.setIcon(R.drawable.save);

            // setting positive "Yes" button
            logoutButton.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user press Proceed button. Write logic here
                    goToLoginActivity();
                }
            });

            // setting positive "No" button
            logoutButton.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user press Proceed button. Write logic here
                    dialog.dismiss();
                }
            });

            logoutButton.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder closeButton = new AlertDialog.Builder(AdminMainActivity.this);

            // setting dialog title
            closeButton.setTitle("Quit");

            // setting dialog message
            closeButton.setMessage("Are you sure to quit?");

            // setting icon to dialog
            //orderConfirm.setIcon(R.drawable.save);

            // setting positive "Yes" button
            closeButton.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user press Proceed button. Write logic here
                    moveTaskToBack(true);
                }
            });

            // setting positive "No" button
            closeButton.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // user press Proceed button. Write logic here
                    dialog.dismiss();
                }
            });

            closeButton.show();
        }
        return super.onKeyDown(keycode, event);
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    public void goToManageOrderAdminActivity() {
        Intent intent = new Intent(this, ManageOrderAdminActivity.class);
        startActivity(intent);
    }

    public void goToTrackWaitingTimeAdminActivity() {
        Intent intent = new Intent(this, TrackWaitingTimeAdminActivity.class);
        startActivity(intent);
    }

    public void goToViewReportActivity() {
        Intent intent = new Intent(this, ViewReportActivity.class);
        startActivity(intent);
    }
}

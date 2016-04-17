package wqyap762.rprqs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ViewOrderActivity extends ActionBarActivity {

    public static final String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        // listed order update
        Button listUpdateCustomerButton = (Button) findViewById(R.id.listUpdateCustomerButton);
        listUpdateCustomerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToOrderInformationActivity();
                    }
                }
        );
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

    public void goToOrderInformationActivity() {
        String order_id = "18";
        Intent intent = new Intent(this, OrderInfomationActivity.class);
        intent.putExtra("order_id", order_id);
        startActivity(intent);
    }
}

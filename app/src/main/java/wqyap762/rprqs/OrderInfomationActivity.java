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
import android.widget.TextView;


public class OrderInfomationActivity extends ActionBarActivity {

    public static final String DEFAULT = "N/A";
    TextView nameText, hpnoText, foodNameText, quantityText, totalPriceText, paymentStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_infomation);

        nameText = (TextView) findViewById(R.id.nameText);
        hpnoText = (TextView) findViewById(R.id.hpnoText);
        foodNameText = (TextView) findViewById(R.id.foodNameText);
        quantityText = (TextView) findViewById(R.id.quantityText);
        totalPriceText = (TextView) findViewById(R.id.totalPriceText);

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
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("UserData", DEFAULT);
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

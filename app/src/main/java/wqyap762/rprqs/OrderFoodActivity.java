package wqyap762.rprqs;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class OrderFoodActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set lunch / set dinner
        Button setOrderButton = (Button) findViewById(R.id.setOrderButton);
        setOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToSetOrderActivity();
                    }
                }
        );

        // rice
        Button riceOrderButton = (Button) findViewById(R.id.riceOrderButton);
        riceOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToRiceOrderActivity();
                    }
                }
        );

        // noodles
        Button noodlesOrderButton = (Button) findViewById(R.id.noodlesOrderButton);
        noodlesOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToNoodlesOrderActivity();
                    }
                }
        );

        // beverages
        Button beveragesOrderButton = (Button) findViewById(R.id.beveragesOrderButton);
        beveragesOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToBeveragesOrderActivity();
                    }
                }
        );

        // homemade desserts
        Button fruitJuiceOrderButton = (Button) findViewById(R.id.fruitJuiceOrderButton);
        fruitJuiceOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToFruitJuiceOrderActivity();
                    }
                }
        );

        // homemade beverages
        Button dessertsOrderButton = (Button) findViewById(R.id.dessertsOrderButton);
        dessertsOrderButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        goToDessertsOrderActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_food, menu);
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

    public void goToSetOrderActivity() {
        Intent intent = new Intent(this, SetOrderActivity.class);
        startActivity(intent);
    }

    public void goToRiceOrderActivity() {
        Intent intent = new Intent(this, RiceOrderActivity.class);
        startActivity(intent);
    }

    public void goToNoodlesOrderActivity() {
        Intent intent = new Intent(this, NoodlesOrderActivity.class);
        startActivity(intent);
    }

    public void goToBeveragesOrderActivity() {
        Intent intent = new Intent(this, BeveragesOrderActivity.class);
        startActivity(intent);
    }

    public void goToFruitJuiceOrderActivity() {
        Intent intent = new Intent(this, FruitJuiceOrderActivity.class);
        startActivity(intent);
    }

    public void goToDessertsOrderActivity() {
        Intent intent = new Intent(this, DessertsOrderActivity.class);
        startActivity(intent);
    }
}

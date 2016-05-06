package wqyap762.rprqs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FruitJuiceOrderActivity extends AppCompatActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_juice_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Apple Bittergourd Juice
        Button applebittergourdJuiceButton = (Button) findViewById(R.id.applebittergourdJuiceButton);
        assert applebittergourdJuiceButton != null;
        applebittergourdJuiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "J001";
                        goToItemActivity();
                    }
                }
        );

        // Apple Carrot Juice
        Button appleCarrotJuiceButton = (Button) findViewById(R.id.appleCarrotJuiceButton);
        assert appleCarrotJuiceButton != null;
        appleCarrotJuiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "J002";
                        goToItemActivity();
                    }
                }
        );

        // Green Apple Celery Juice
        Button greenAppleCeleryJuiceButton = (Button) findViewById(R.id.greenAppleCeleryJuiceButton);
        assert greenAppleCeleryJuiceButton != null;
        greenAppleCeleryJuiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "J003";
                        goToItemActivity();
                    }
                }
        );

        // Guava and Sour Plum Juice
        Button guavaSourPlumJuice = (Button) findViewById(R.id.guavaSourPlumJuice);
        assert guavaSourPlumJuice != null;
        guavaSourPlumJuice.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "J004";
                        goToItemActivity();
                    }
                }
        );

        // Watermelon Orange Juice
        Button watermelonOrangeJuiceButton = (Button) findViewById(R.id.watermelonOrangeJuiceButton);
        assert watermelonOrangeJuiceButton != null;
        watermelonOrangeJuiceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "J005";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fruit_juice_order, menu);
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

    public void goToItemActivity() {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("menu_id", menu_id);
        startActivity(intent);
    }
}

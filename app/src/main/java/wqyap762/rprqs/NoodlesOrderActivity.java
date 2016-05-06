package wqyap762.rprqs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class NoodlesOrderActivity extends AppCompatActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noodles_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Dumpling Noodles
        Button dumplingNoodlesButton = (Button) findViewById(R.id.dumplingNoodlesButton);
        assert dumplingNoodlesButton != null;
        dumplingNoodlesButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N001";
                        goToItemActivity();
                    }
                }
        );

        // Wanton Noodles
        Button wanTonNoodlesButton = (Button) findViewById(R.id.wanTonNoodlesButton);
        assert wanTonNoodlesButton != null;
        wanTonNoodlesButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N002";
                        goToItemActivity();
                    }
                }
        );

        // Fried Fish Bee Hoon Soup
        Button friedFishBeeHoonSoupButton = (Button) findViewById(R.id.friedFishBeeHoonSoupButton);
        assert friedFishBeeHoonSoupButton != null;
        friedFishBeeHoonSoupButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N003";
                        goToItemActivity();
                    }
                }
        );

        // Sliced Fish Bee Hoon Soup
        Button slicedFishBeeHoonSoupButton = (Button) findViewById(R.id.slicedFishBeeHoonSoupButton);
        assert slicedFishBeeHoonSoupButton != null;
        slicedFishBeeHoonSoupButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N004";
                        goToItemActivity();
                    }
                }
        );

        // Sin Chew Bee Hoon
        Button sinChewBeeHoonButton = (Button) findViewById(R.id.sinChewBeeHoonButton);
        assert sinChewBeeHoonButton != null;
        sinChewBeeHoonButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N005";
                        goToItemActivity();
                    }
                }
        );

        // Ee Mee
        Button eeMeeButton = (Button) findViewById(R.id.eeMeeButton);
        assert eeMeeButton != null;
        eeMeeButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N006";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_noodles_order, menu);
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

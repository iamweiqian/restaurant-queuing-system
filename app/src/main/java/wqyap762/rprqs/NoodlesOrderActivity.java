package wqyap762.rprqs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class NoodlesOrderActivity extends ActionBarActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noodles_order);

        // wa tan ho
        Button waTanHoButton = (Button) findViewById(R.id.waTanHoButton);
        waTanHoButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N001";
                        goToItemActivity();
                    }
                }
        );

        // singapore fried bee hoon
        Button singaporeFriedBeeHoonButton = (Button) findViewById(R.id.singaporeFriedBeeHoonButton);
        singaporeFriedBeeHoonButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N002";
                        goToItemActivity();
                    }
                }
        );

        // hokkien mee
        Button hokkienMeeButton = (Button) findViewById(R.id.hokkienMeeButton);
        hokkienMeeButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N003";
                        goToItemActivity();
                    }
                }
        );

        // singapore fried hokkien mee
        Button singaporeFriedHokkienMeeButton = (Button) findViewById(R.id.singaporeFriedHokkienMeeButton);
        singaporeFriedHokkienMeeButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N004";
                        goToItemActivity();
                    }
                }
        );

        // yee mee
        Button claypotYeeMeeButton = (Button) findViewById(R.id.claypotYeeMeeButton);
        claypotYeeMeeButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "N005";
                        goToItemActivity();
                    }
                }
        );

        // tomato mee
        Button tomatoMeeButton = (Button) findViewById(R.id.tomatoMeeButton);
        tomatoMeeButton.setOnClickListener(
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

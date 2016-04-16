package wqyap762.rprqs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomemadeDessertsOrderActivity extends ActionBarActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemade_desserts_order);

        // herbal jelly
        Button herbalJellyButton = (Button) findViewById(R.id.herbalJellyButton);
        herbalJellyButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HD001";
                        goToItemActivity();
                    }
                }
        );

        // red bean soup
        Button redBeanSoupButton = (Button) findViewById(R.id.redBeanSoupButton);
        redBeanSoupButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HD002";
                        goToItemActivity();
                    }
                }
        );

        // longan milk ice
        Button longanMilkIceButton = (Button) findViewById(R.id.longanMilkIceButton);
        longanMilkIceButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HD003";
                        goToItemActivity();
                    }
                }
        );

        // barley beancurd
        Button barleyBeancurdGinkgoButton = (Button) findViewById(R.id.barleyBeancurdGinkgoButton);
        barleyBeancurdGinkgoButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "HD004";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homemade_desserts_order, menu);
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

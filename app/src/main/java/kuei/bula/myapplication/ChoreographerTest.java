package kuei.bula.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ChoreographerTest extends Activity {

    public static ChoreographerTest SELF;
    TextView fpsField;
    TextView dropField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choreographer_test);
        SELF = this;
        NewFrameCallback callback = new NewFrameCallback();
        fpsField = (TextView) findViewById(R.id.FpsField);
        dropField = (TextView) findViewById(R.id.dropField);

    }

    public TextView getFpsField() {
        return fpsField;
    }

    public TextView getDroppingField() {
        return dropField;
    }

    public TextView getFrameDroppingField() {
        return (TextView) findViewById(R.id.dropField);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choreographer_test, menu);
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
}

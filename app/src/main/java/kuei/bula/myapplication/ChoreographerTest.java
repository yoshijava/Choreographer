package kuei.bula.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ChoreographerTest extends Activity implements Runnable {

    public static Object lock = new Object();
    public static ChoreographerTest SELF;
    private TextView fpsField;
    private TextView dropField;
    private Handler mHandler;
    private NewFrameCallback callback;
    private int frameDropped;
    private String fps = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choreographer_test);
        SELF = this;
        callback = new NewFrameCallback();
        fpsField = (TextView) findViewById(R.id.FpsField);
        dropField = (TextView) findViewById(R.id.dropField);
        mHandler = new Handler();
        mHandler.post(this);
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

    @Override
    public void run() {
        String fps = callback.getFPS();
        int frameDropped = callback.getDroppedFrame();
        if(!this.fps.equals(fps)) {
            dropField.setText(frameDropped + " drops");
        }
        if(! (this.frameDropped == frameDropped)) {
            fpsField.setText("FPS = " + fps);
        }
        mHandler.postDelayed(this, NewFrameCallback.JANK_LIMIT_IN_NANO /1000000);
    }

}

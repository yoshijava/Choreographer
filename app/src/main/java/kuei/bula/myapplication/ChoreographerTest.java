package kuei.bula.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;


public class ChoreographerTest extends Activity implements Runnable {

    public static Object lock = new Object();
    public static ChoreographerTest SELF;
    private TextView fpsField;
    private TextView dropField;
    private Handler mHandler;
    private NewFrameCallback callback;
    private double frameDropped = 0;
    private double fps = 0;
    private DecimalFormat dec = new DecimalFormat("#.00");


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
        double fps = callback.getFPS();
        double frameDropped = callback.getDroppedFrame();
        if( this.frameDropped == frameDropped) {
            dropField.setText(frameDropped + " drops");
            this.frameDropped = frameDropped;
        }
        if(this.fps != fps) {
            fpsField.setText("FPS = " + dec.format(fps));
            this.fps = fps;
        }
        mHandler.postDelayed(this, NewFrameCallback.jankLimitInNano / 1000000);
    }

}

/**
 * Created by yoshi on 2015/7/28.
 * A compiled sample code for demonstrating how to use Choreographer to detect frame dropping.
 */
package kuei.bula.myapplication;
import android.util.Log;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.widget.Chronometer;
import android.widget.TextView;

public class NewFrameCallback implements FrameCallback {
    private long lastUpdate = 0;
    private final long jankLimitInNano = 16666666; // 16.67 ms for 60 fps
    private String TAG;
    private Choreographer choreographer;
    private int droppedFrame = 0;
    public NewFrameCallback() {
        TAG = NewFrameCallback.class.getName();
        choreographer = Choreographer.getInstance();
        choreographer.postFrameCallback(NewFrameCallback.this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        long diff = (frameTimeNanos - lastUpdate);
        if(diff > jankLimitInNano) {
//            Log.d(TAG, "lastUpdate = " + lastUpdate);
//            Log.d(TAG, "frameTimeNanos = " + frameTimeNanos);
//            Log.d(TAG, "Diff = " + diff);
            Log.d(TAG, "Frame drops");
            ChoreographerTest.SELF.getFrameDroppingField().setText("" + droppedFrame + " drops");
            droppedFrame++;
        }
        else {
            Log.d(TAG, "Frame goes well");
        }
        String fps = "FPS = " + String.valueOf(1000000000.0d/diff).substring(0, 6);
        Log.d(TAG, fps);
        ChoreographerTest.SELF.getFpsField().setText(fps);
        lastUpdate = frameTimeNanos;
        choreographer.postFrameCallback(NewFrameCallback.this);
    }

}

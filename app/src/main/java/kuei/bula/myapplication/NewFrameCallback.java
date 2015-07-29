/**
 * Created by yoshi on 2015/7/28.
 * A compiled sample code for demonstrating how to use Choreographer to detect frame dropping.
 */
package kuei.bula.myapplication;
import android.util.Log;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

import java.text.DecimalFormat;

public class NewFrameCallback implements FrameCallback {
    private long lastUpdate = 0;
    public static final long JANK_LIMIT_IN_NANO = 16666666; // 16.67 ms for 60 fps
    private String TAG;
    private Choreographer choreographer;
    private int droppedFrame = 0;

    private double fps;

    public NewFrameCallback() {
        TAG = NewFrameCallback.class.getName();
        choreographer = Choreographer.getInstance();
        choreographer.postFrameCallback(this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        long diff = (frameTimeNanos - lastUpdate);
        Log.d(TAG, "lastUpdate = " + lastUpdate);
        Log.d(TAG, "frameTimeNanos = " + frameTimeNanos);
        Log.d(TAG, "Diff = " + diff);
        if(diff > JANK_LIMIT_IN_NANO) {
            Log.d(TAG, "Frame drops");
            droppedFrame++;
        }
        else {
            Log.d(TAG, "Frame goes well");
        }
        fps = 1000000000.0d/diff;
        lastUpdate = frameTimeNanos;
        choreographer.postFrameCallback(this);
    }

    public double getFPS() {
        return fps;
    }

    public int getDroppedFrame() {
        return droppedFrame;
    }
}

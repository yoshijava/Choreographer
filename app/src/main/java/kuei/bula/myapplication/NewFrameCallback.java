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
    public static final long jankLimitInNano = 16666667; // 16.67 ms for 60 fps
    private String TAG;
    private Choreographer choreographer;
    //    private double droppedFrame = 0;
    private long refreshRate = 60;
    private double droppedFrame = 0;
    private double fps = 0;

    public NewFrameCallback() {
        TAG = NewFrameCallback.class.getName();
        choreographer = Choreographer.getInstance();
        choreographer.postFrameCallback(NewFrameCallback.this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        final long startNanos = System.nanoTime();
        final double jitterNanos = (startNanos - frameTimeNanos);
        Log.d(TAG, "jitterNanos = " + jitterNanos);
        if (jitterNanos >= jankLimitInNano) {
            droppedFrame = jitterNanos / jankLimitInNano;
            Log.d(TAG, "Dropped frame: " + droppedFrame);
        }
        else {
            Log.d(TAG, "Frame goes well");
            droppedFrame = 0;
        }
        fps = 60 - droppedFrame;
        Log.d(TAG, "FPS = " + fps);
//        ChoreographerTest.SELF.getFpsField().setText(fps);
        choreographer.postFrameCallback(NewFrameCallback.this);
    }

    public double getDroppedFrame() {
        return droppedFrame;
    }

    public double getFPS() {
        return fps;
    }

}

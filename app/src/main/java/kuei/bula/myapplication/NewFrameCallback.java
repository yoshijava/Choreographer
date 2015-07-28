/**
 * Created by yoshi on 2015/7/28.
 * A compiled sample code for demonstrating how to use Choreographer to detect frame dropping.
 */
package kuei.bula.myapplication;
import android.util.Log;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

public class NewFrameCallback implements FrameCallback {
    private long lastUpdate = 0;
    private final long jankLimitInNano = 16666666; // 16.67 ms for 60 fps
    private String TAG;
    private Choreographer choreographer;

    public NewFrameCallback() {
        TAG = NewFrameCallback.class.getName();
        choreographer = Choreographer.getInstance();
        choreographer.postFrameCallback(NewFrameCallback.this);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        long diff = (frameTimeNanos - lastUpdate);
        Log.d(TAG, "lastUpdate = " + lastUpdate);
        Log.d(TAG, "frameTimeNanos = " + frameTimeNanos);
        Log.d(TAG, "Diff = " + diff);
        if( diff > jankLimitInNano ) {
            Log.d(TAG, "Frame drops");
        }
        else {
            Log.d(TAG, "Frame goes well");
        }
        lastUpdate = frameTimeNanos;
        choreographer.postFrameCallback(NewFrameCallback.this);
    }

}

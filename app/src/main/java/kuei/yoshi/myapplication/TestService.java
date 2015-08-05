/**
 * Created by yoshi on 2015/7/28.
 * A compiled sample code for demonstrating how to use Choreographer to detect frame dropping.
 */
package kuei.yoshi.myapplication;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    String TAG = "TestService";

    private final ITestService.Stub service = new ITestService.Stub() {
        public String getString() {
            return "WOW";
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        Log.d(TAG, "onBind");
        return service;
    }
}

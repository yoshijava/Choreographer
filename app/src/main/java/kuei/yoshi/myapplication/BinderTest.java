package kuei.yoshi.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BinderTest extends Activity implements Runnable {

    private static final String TAG = BinderTest.class.getName();
    public static BinderTest SELF;
    private TextView wowField;
    private Handler mHandler;

    boolean bound = false;
    ITestService mIRemoteService;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = ITestService.Stub.asInterface(service);
            bound = true;
            mHandler.post(BinderTest.this);
            Log.d(TAG, "Service bound");
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "Service has unexpectedly disconnected");
            bound = false;
            mIRemoteService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choreographer_test);
        wowField = (TextView) findViewById(R.id.binderField);
        SELF = this;
        Intent intent = new Intent(this, ITestService.class);
        intent.setPackage("kuei.yoshi.myapplication");

        if(mConnection == null) {
            Log.d(TAG, "mConnection == null");
        }
        else {
            boolean success = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            if(success) {
                Log.d(TAG, "bindService succeeded");
            }
            else {
                wowField.setText("bindService fails");
                Log.e(TAG, "bindService fails");
            }
        }


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
        try {
            if(mIRemoteService!=null) {
                wowField.setText(mIRemoteService.getString());
            }
            else {
                Log.d(TAG, "mIRemoteService == null");
            }
        } catch (RemoteException e) {
            Log.d(TAG, "Choreographer.run", e);
        }
//        mHandler.post(this);
    }
}

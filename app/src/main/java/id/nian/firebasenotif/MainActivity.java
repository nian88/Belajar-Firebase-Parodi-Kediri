package id.nian.firebasenotif;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private Button mButtonSubscribe;
    private Button mButtonLogToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isConnectingToInternet()==false){
            Toast.makeText(this, "Koneksi Mati Gan", Toast.LENGTH_SHORT).show();
        }
        if(getIntent().getExtras()!=null){
            for(String key : getIntent().getExtras().keySet()){
                String value = getIntent().getExtras().getString(key);
                Log.d("TAG", "KEY : " + key + "Value : " + value);
            }
        }

        mButtonSubscribe = (Button) findViewById(R.id.subscribeButton);
        mButtonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                Log.d("TAG", "Subscribe to news topic");
            }
        });
        mButtonLogToken = (Button) findViewById(R.id.logTokenButton);
        mButtonLogToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "InstanceID Token : " + FirebaseInstanceId.getInstance().getToken());
            }
        });
    }
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null)
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }

        }
        return false;
    }
}

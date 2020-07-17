package id.co.myproject.angkutapps_penumpang.view.tracking.fitur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

import id.co.myproject.angkutapps_penumpang.R;

public class CallActivity extends AppCompatActivity {

    private static final String APP_KEY = "7b78da15-db97-453f-a7f2-87f1b17dd367";
    private static final String APP_SECRET = "A9vwFs8ay0Sp0+wX0Fmssw==";
    private static final String ENVIRONMENT = "clientapi.sinch.com";

    private SinchClient sinchClient;
    SwipeButton swipeButton;
    private Call call;

    String noHpDriver;
    String noHpUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        swipeButton = findViewById(R.id.swipeButton);

        Bundle bundle = getIntent().getExtras();
        noHpDriver = bundle.getString("DRIVER");
        noHpUser = bundle.getString("USER");
        Log.i("NOHPUSER", noHpUser);
        Log.i("NOHPUSER", noHpDriver);

        if (ContextCompat.checkSelfPermission(CallActivity.this,
                android.Manifest.permission.RECORD_AUDIO) !=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission
                (CallActivity.this, android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(CallActivity.this,
                    new String[]{android.Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE},
                    1);
        }

        sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
                .userId(noHpUser)
                .applicationKey(APP_KEY)
                .applicationSecret(APP_SECRET)
                .environmentHost(ENVIRONMENT)
                .build();

        sinchClient.setSupportCalling(true);
        sinchClient.startListeningOnActiveConnection();
        sinchClient.start();

        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Log.i("Hasil", ""+call);
                if (call == null) {
                    Toast.makeText(CallActivity.this, "Aplikasi Berjalan", Toast.LENGTH_SHORT).show();
                    call = sinchClient.getCallClient().callUser(noHpDriver);
//                    Log.i("Hasill",""+sinchClient);
//                    Log.i("Hasill",""+sinchClient.getCallClient());
//                    Log.i("Hasill",""+sinchClient.getCallClient().callUser(noHpDriver));
                    call.addCallListener(new SinchCallListener());
//                    button.setText("Hang Up");
                } else {
                    call.hangup();
                }
            }
        });

    }

    private class SinchCallListener implements CallListener {
        @Override
        public void onCallEnded(Call endedCall) {
            call = null;
            SinchError a = endedCall.getDetails().getError();
//            button.setText("Call");
//            callState.setText("");
            Log.i("Hasill","onCallEnded");
            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
        }

        @Override
        public void onCallEstablished(Call establishedCall) {
//            callState.setText("connected");
            Log.i("Hasill", "onCallEstablished");
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        }

        @Override
        public void onCallProgressing(Call progressingCall) {
//            callState.setText("ringing");
            Log.i("Hasill", "onCallProgressing");
//            Toast.makeText(Call2.this, "Ringing.......", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
            Log.i("Hasill","onShouldSendPushNotification");
        }
    }

    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
//            call = incomingCall;
//            Toast.makeText(Call2.this, "incoming call", Toast.LENGTH_SHORT).show();
//            call.answer();
//            call.addCallListener(new SinchCallListener());
//            button.setText("Hang Up");
//            Toast.makeText(Call2.this, "Ada Panggilan Masuk", Toast.LENGTH_SHORT).show();
            AlertDialog alertDialog = new AlertDialog.Builder(CallActivity.this).create();
            alertDialog.setTitle("Calling");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reject", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    call.hangup();
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Pick", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    call = incomingCall;
                    call.answer();
                    call.addCallListener(new SinchCallListener());
//                    button.setText("Hang Up");
                    Toast.makeText(CallActivity.this, "Call is Started", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.show();

        }
    }

}
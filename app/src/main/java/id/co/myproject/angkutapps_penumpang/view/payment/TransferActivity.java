package id.co.myproject.angkutapps_penumpang.view.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import id.co.myproject.angkutapps_penumpang.R;

public class TransferActivity extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvKeNomorHp, cvKeRekeningBank, cvQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        cvKeRekeningBank = findViewById(R.id.cvKeRekeningBank);
        cvKeNomorHp = findViewById(R.id.cvKeNomorHp);
        cvQrCode = findViewById(R.id.cvQrCode);
        appbar_button_back = findViewById(R.id.appbar_button_back);
        appbar_button_back.setOnClickListener(clickListener);
        cvKeRekeningBank.setOnClickListener(clickListener);
        cvKeNomorHp.setOnClickListener(clickListener);
        cvQrCode.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.appbar_button_back :
                    finish();
                    break;
                case R.id.cvQrCode :
                    break;
                case R.id.cvKeNomorHp :
                    break;
                case R.id.cvKeRekeningBank :
                    break;
            }
        }
    };
}

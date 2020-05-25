package id.co.myproject.angkutapps_penumpang.view.menu_payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import id.co.myproject.angkutapps_penumpang.R;

public class TopupActivity extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvKartuDebit, cvAtm, cvIndomaretGroup, cvAlfamartGroup, cvMobileBanking, cvScanQRcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        cvKartuDebit = findViewById(R.id.cvKartuDebit);
        cvAtm = findViewById(R.id.cvATM);
        cvIndomaretGroup = findViewById(R.id.cvIndomaretGroup);
        cvAlfamartGroup = findViewById(R.id.cvAlfamartGroup);
        cvMobileBanking = findViewById(R.id.cvMobileBanking);
        cvScanQRcode = findViewById(R.id.cvScanQRCode);
        appbar_button_back = findViewById(R.id.appbar_button_back);

        appbar_button_back.setOnClickListener(clickListener);
        cvKartuDebit.setOnClickListener(clickListener);
        cvAtm.setOnClickListener(clickListener);
        cvIndomaretGroup.setOnClickListener(clickListener);
        cvAlfamartGroup.setOnClickListener(clickListener);
        cvMobileBanking.setOnClickListener(clickListener);
        cvScanQRcode.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.appbar_button_back :
                    finish();
                    break;
                case R.id.cvKartuDebit :
                    Toast.makeText(TopupActivity.this, "Kartu Debit", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cvATM :
                    Toast.makeText(TopupActivity.this, "ATM", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cvIndomaretGroup :
                    Toast.makeText(TopupActivity.this, "Indomaret Group", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cvAlfamartGroup :
                    Toast.makeText(TopupActivity.this, "Alfamart Group", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cvMobileBanking :
                    Toast.makeText(TopupActivity.this, "Mobile Banking", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.cvScanQRCode :
                    Toast.makeText(TopupActivity.this, "Scan QR Code", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}

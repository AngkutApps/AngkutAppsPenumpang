package id.co.myproject.angkutapps_penumpang.view.menu_payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down.DDATM;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down.DDAlfamartGroup;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down.DDClear;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down.DDIndomaretGroup;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down.DDKartuDebit;
import id.co.myproject.angkutapps_penumpang.view.menu_payment.drop_down.DDMobileBanking;

public class TopupActivity extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvKartuDebit, cvAtm, cvIndomaretGroup, cvAlfamartGroup, cvMobileBanking, cvScanQRcode;

    ImageView iconNext1, iconNext2, iconNext3, iconNext4, iconNext5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        iconNext1 = findViewById(R.id.iconNext1);
        iconNext2 = findViewById(R.id.iconNext2);
        iconNext3 = findViewById(R.id.iconNext3);
        iconNext4 = findViewById(R.id.iconNext4);
        iconNext5 = findViewById(R.id.iconNext5);
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

    int KartuDebit=0, ATM=0, IndomaretGroup=0, AlfamartGroup=0, MobileBanking=0;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.appbar_button_back :
                    finish();
                    break;
                case R.id.cvKartuDebit :
                    if (KartuDebit==0){
                        setFragment(new DDKartuDebit(), R.id.frameDownKartuDebit);
                        iconNext1.setRotation(90);
                        KartuDebit+=1;
                    }else {
                        setFragment(new DDClear(), R.id.frameDownKartuDebit);
                        iconNext1.setRotation(0);
                        KartuDebit=0;
                    }
                    break;
                case R.id.cvATM :
                    if (ATM==0){
                        setFragment(new DDATM(), R.id.frameDownATM);
                        iconNext2.setRotation(90);
                        ATM+=1;
                    }else {
                        setFragment(new DDClear(), R.id.frameDownATM);
                        iconNext2.setRotation(0);
                        ATM=0;
                    }
                    break;
                case R.id.cvIndomaretGroup :
                    if (IndomaretGroup==0){
                        iconNext3.setRotation(90);
                        setFragment(new DDIndomaretGroup(), R.id.frameDownIndomaretGroup);
                        IndomaretGroup+=1;
                    }else {
                        setFragment(new DDClear(), R.id.frameDownIndomaretGroup);
                        iconNext3.setRotation(0);
                        IndomaretGroup=0;
                    }
                    break;
                case R.id.cvAlfamartGroup :
                    if (AlfamartGroup==0){
                        iconNext4.setRotation(90);
                        setFragment(new DDAlfamartGroup(), R.id.frameDownAlfamartGroup);
                        AlfamartGroup+=1;
                    }else {
                        setFragment(new DDClear(), R.id.frameDownAlfamartGroup);
                        iconNext4.setRotation(0);
                        AlfamartGroup=0;
                    }
                    break;
                case R.id.cvMobileBanking :
                    if (MobileBanking==0){
                        iconNext5.setRotation(90);
                        setFragment(new DDMobileBanking(), R.id.frameDownMobileBanking);
                        MobileBanking+=1;
                    }else {
                        setFragment(new DDClear(), R.id.frameDownMobileBanking);
                        iconNext5.setRotation(0);
                        MobileBanking=0;
                    }
                    break;
                case R.id.cvScanQRCode :
                    Toast.makeText(TopupActivity.this, "Scan QR Code", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void setFragment(Fragment fragment, int frame){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frame, fragment);
        transaction.commit();
    }

}

package id.co.myproject.angkutapps_penumpang.view.menu_akun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.co.myproject.angkutapps_penumpang.MainActivity;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.adapter.*;
import id.co.myproject.angkutapps_penumpang.model.LoadKontakDarurat;
import id.co.myproject.angkutapps_penumpang.view.dialogFragment.DetailRiwayatFragment;
import id.co.myproject.angkutapps_penumpang.view.dialogFragment.TambahKontakDarurat;

public class KontakDarurat extends AppCompatActivity {

    ImageButton appbar_button_back;
    CardView cvTambahKontakDarurat;
    RecyclerView rvKontakDarurat;
    rvKontakDarurat kontakDaruratAdapter;
    ArrayList<LoadKontakDarurat> arrayList =new ArrayList<>();;

    String[] nama = {"Andi Jayapura Mallengkeri","Andi Jingga Baso","Irwan Ardyansah"};
    String[] nomor = {"+6285299935661","+6287324560926","+6289277308256"};
    String[] hubungan = {"Orang Tua","Teman","Sepupu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_darurat);

        appbar_button_back = findViewById(R.id.appbar_button_back);
        cvTambahKontakDarurat = findViewById(R.id.cvTambahKontakDarurat);
        rvKontakDarurat = findViewById(R.id.rvKontakDarurat);

        for (int i=0 ; i< nama.length;i++){
            arrayList.add(new LoadKontakDarurat(nama[i],hubungan[i],nomor[i]));
        }

        kontakDaruratAdapter = new rvKontakDarurat(getApplicationContext(), arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvKontakDarurat.setLayoutManager(layoutManager);
        rvKontakDarurat.setHasFixedSize(true);
        rvKontakDarurat.setAdapter(kontakDaruratAdapter);

        appbar_button_back.setOnClickListener(clickListener);
        cvTambahKontakDarurat.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.appbar_button_back :
                    finish();
                    break;
                case R.id.cvTambahKontakDarurat:
                    setFragment(new TambahKontakDarurat());
                    break;
            }
        }
    };

    private void setFragment(DialogFragment fragment){
        FragmentManager fragmentManager = KontakDarurat.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev !=null){
            fragmentTransaction.remove(prev);
        }
        fragment.show(fragmentTransaction, "dialog");
    }
}

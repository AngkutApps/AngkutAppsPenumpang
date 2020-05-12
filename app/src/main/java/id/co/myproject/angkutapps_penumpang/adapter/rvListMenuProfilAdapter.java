package id.co.myproject.angkutapps_penumpang.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.view.menu_akun.KontakDarurat;
import id.co.myproject.angkutapps_penumpang.view.menu_akun.LokasiDitandai;
import id.co.myproject.angkutapps_penumpang.view.menu_akun.PemilihanBahasa;
import id.co.myproject.angkutapps_penumpang.view.menu_akun.Pengaturan;
import id.co.myproject.angkutapps_penumpang.view.menu_akun.Penjadwalan;

public class rvListMenuProfilAdapter extends RecyclerView.Adapter<rvListMenuProfilAdapter.ViewHolder> {

    private Context context;

    String[] menu = {"Bahasa","Dijadwalkan","Lokasi Ditandai","Pusat Bantuan","Kontak Darurat","Pengaturan","Bagikan Feedback", "Beri Masukan","FAQ","Log Out"};

    public rvListMenuProfilAdapter(Context context) {
        this.context = context;
    }

    @Override
    public rvListMenuProfilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_list_menu_profil, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(rvListMenuProfilAdapter.ViewHolder holder, int position) {
        holder.tvListMenuProfil.setText(menu[position]);
        holder.cvMenuProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilih_menu_akun(position);
//                Toast.makeText(context, "Event : "+menu[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cvMenuProfil;
        TextView tvListMenuProfil;

        public ViewHolder(View itemView) {
            super(itemView);

            cvMenuProfil = itemView.findViewById(R.id.cvMenuProfil);
            tvListMenuProfil = itemView.findViewById(R.id.tvListMenuProfil);

        }
    }

    private void pilih_menu_akun(int position){
        switch (position){
            case 0 :
                context.startActivity(new Intent(context, PemilihanBahasa.class));
                break;
            case 1 :
                context.startActivity(new Intent(context, Penjadwalan.class));
                break;
            case 2 :
                context.startActivity(new Intent(context, LokasiDitandai.class));
                break;
//            case 3 :
//                context.startActivity(new Intent(context, PemilihanBahasa.class));
//                break;
            case 4 :
                context.startActivity(new Intent(context, KontakDarurat.class));
                break;
            case 5 :
                context.startActivity(new Intent(context, Pengaturan.class));
                break;
        }
    }
}

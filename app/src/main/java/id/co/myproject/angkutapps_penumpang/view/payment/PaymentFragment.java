package id.co.myproject.angkutapps_penumpang.view.payment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.view.payment.RiwayatActivity;
import id.co.myproject.angkutapps_penumpang.view.payment.TopupActivity;
import id.co.myproject.angkutapps_penumpang.view.payment.TransferActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    TextView tvMarqueText;
    LinearLayout layout_riwayat, layout_topup, layout_transfer;
    LinearLayout pulsaService, paketDataService, listrikService, plnService, voucherGamesService, internetService, tokoOnlineService, pajakService;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);

    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pulsaService = view.findViewById(R.id.pulsaService);
        paketDataService = view.findViewById(R.id.paketDataService);
        listrikService = view.findViewById(R.id.listrikService);
        plnService = view.findViewById(R.id.plnService);
        voucherGamesService = view.findViewById(R.id.voucherGamesService);
        internetService = view.findViewById(R.id.internetService);
        tokoOnlineService = view.findViewById(R.id.tokoOnlineService);
        pajakService = view.findViewById(R.id.pajakService);
        layout_riwayat = view.findViewById(R.id.layout_riwayat);
        layout_topup = view.findViewById(R.id.layout_topup);
        layout_transfer = view.findViewById(R.id.layout_transfer);
        tvMarqueText = view.findViewById(R.id.marquetext);
        tvMarqueText.setSelected(true);

        layout_transfer.setOnClickListener(clickListener);
        layout_topup.setOnClickListener(clickListener);
        layout_riwayat.setOnClickListener(clickListener);
        pulsaService.setOnClickListener(clickListener);
        paketDataService.setOnClickListener(clickListener);
        listrikService.setOnClickListener(clickListener);
        plnService.setOnClickListener(clickListener);
        voucherGamesService.setOnClickListener(clickListener);
        internetService.setOnClickListener(clickListener);
        tokoOnlineService.setOnClickListener(clickListener);
        pajakService.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.layout_riwayat :
                    startActivity(new Intent(getActivity(), RiwayatActivity.class));
                    break;
                case R.id.layout_topup :
                    startActivity(new Intent(getActivity(), TopupActivity.class));
                    break;
                case R.id.layout_transfer :
                    startActivity(new Intent(getActivity(), TransferActivity.class));
                    break;
                case R.id.pulsaService :
                    Toast.makeText(getContext(), "Pulsa Service This", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.paketDataService :
                    Toast.makeText(getContext(), "Paket Data Service", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.listrikService :
                    Toast.makeText(getContext(), "Listrik Service", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.plnService :
                    Toast.makeText(getContext(), "PLN Service", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.voucherGamesService :
                    Toast.makeText(getContext(), "Voucher Service", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.internetService :
                    Toast.makeText(getContext(), "Internet Service", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tokoOnlineService :
                    Toast.makeText(getContext(), "Toko Online Service", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pajakService :
                    Toast.makeText(getContext(), "Pajak Service", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}

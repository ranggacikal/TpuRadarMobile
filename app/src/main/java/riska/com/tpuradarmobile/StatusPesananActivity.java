package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatusPesananActivity extends AppCompatActivity {

    @BindView(R.id.card_pesanan_berhasil)
    CardView cardPesananBerhasil;
    @BindView(R.id.card_bayar_berhasil)
    CardView cardBayarBerhasil;
    @BindView(R.id.card_berhasil_layout)
    CardView cardBerhasilLayout;
    @BindView(R.id.text_idpesanan_berhasil)
    TextView textIdpesananBerhasil;
    @BindView(R.id.card_berhasil)
    CardView cardBerhasil;
    @BindView(R.id.card_menunggu_validasi)
    CardView cardMenungguValidasi;

    public static final String EXTRA_VALIDASI_PEMBAYARAN = "extraValidasiPembayaran";
    public static final String EXTRA_ID_PESANAN = "extraIdPesanan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pesanan);
        ButterKnife.bind(this);

        String status_pembayaran = getIntent().getStringExtra(EXTRA_VALIDASI_PEMBAYARAN);

        if (status_pembayaran.equals("Valid")){
            cardBerhasil.setVisibility(View.VISIBLE);
            textIdpesananBerhasil.setText(getIntent().getStringExtra(EXTRA_ID_PESANAN));
        }else if (!status_pembayaran.equals("Valid")){
            cardMenungguValidasi.setVisibility(View.VISIBLE);
            cardBerhasil.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StatusPesananActivity.this, DataPesananActivity.class));
        finish();
    }
}

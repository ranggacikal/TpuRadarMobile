package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PembayaranActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PEMESANAN = "extraIdPemesanan";

    @BindView(R.id.text_idpesanan_pembayaran)
    TextView textIdpesananPembayaran;
    @BindView(R.id.text_norekening_pembayaran)
    TextView textNorekeningPembayaran;
    @BindView(R.id.text_atasnama_pembayaran)
    TextView textAtasnamaPembayaran;
    @BindView(R.id.text_rincianharga_pembayaran)
    TextView textRincianhargaPembayaran;
    @BindView(R.id.btn_bukti_pembayaran)
    Button btnBuktiPembayaran;
    @BindView(R.id.card_pesanan_pembayaran)
    CardView cardPesananPembayaran;
    @BindView(R.id.card_bayar_pembayaran)
    CardView cardBayarPembayaran;
    @BindView(R.id.card_berhasil_pembayaran)
    CardView cardBerhasilPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);

        textIdpesananPembayaran.setText(getIntent().getStringExtra(EXTRA_ID_PEMESANAN));

        int harga = 120000;
        Locale localID = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localID);

        textRincianhargaPembayaran.setText(formatRp.format(harga));

        btnBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PembayaranActivity.this, UploadBuktiPembayaranActivity.class);
                intent.putExtra(UploadBuktiPembayaranActivity.EXTRA_ID_PEMESANAN, getIntent().getStringExtra(EXTRA_ID_PEMESANAN));
                startActivity(intent);
                finish();
            }
        });
    }
}

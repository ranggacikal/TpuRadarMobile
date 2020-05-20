package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValidasiAdminActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PESMESANAN = "extraIdPemesanan";
    public static final String EXTRA_IMAGE_PEMESANAN = "extraImagePesanan";
    public static final String EXTRA_IMAGE_PEMBAYARAN = "extraImagePembayaran";
    public static final String EXTRA_IMAGE_BUKTI_PEMBAYARAN_PEMESANAN = "extraImageBuktiPembayaranPesanan";
    public static final String EXTRA_IMAGE_BUKTI_KEMATIAN_PEMESANAN = "extraImageBuktiKematianPesanan";
    @BindView(R.id.text_id_pemesanan_validasi)
    TextView textIdPemesananValidasi;
    @BindView(R.id.btn_validasi_bukti_kematian)
    Button btnValidasiBuktiKematian;
    @BindView(R.id.btn_validasi_bukti_pembayaran)
    Button btnValidasiBuktiPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_admin);
        ButterKnife.bind(this);

        textIdPemesananValidasi.setText(getIntent().getStringExtra(EXTRA_ID_PESMESANAN));

        String bukti_kematian_validasi = getIntent().getStringExtra(EXTRA_IMAGE_BUKTI_KEMATIAN_PEMESANAN);

        if (bukti_kematian_validasi.equals("Belum Valid")) {

            btnValidasiBuktiKematian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ValidasiAdminActivity.this, ValidasiBuktiKematianActivity.class);
                    intent.putExtra(ValidasiBuktiKematianActivity.EXTRA_ID_PESMESANAN, getIntent().getStringExtra(EXTRA_ID_PESMESANAN));
                    intent.putExtra(ValidasiBuktiKematianActivity.EXTRA_IMAGE_PEMESANAN, getIntent().getStringExtra(EXTRA_IMAGE_PEMESANAN));
                    startActivity(intent);
                    finish();
                }
            });

        }else if (bukti_kematian_validasi.equals("Valid") || bukti_kematian_validasi.equals("Tidak Valid")){
            btnValidasiBuktiKematian.setVisibility(View.GONE);
        }

        String bukti_pembayaran = getIntent().getStringExtra(EXTRA_IMAGE_BUKTI_PEMBAYARAN_PEMESANAN);


            btnValidasiBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentPembayaran = new Intent(ValidasiAdminActivity.this, ValidasiBuktiPembayaranActivity.class);
                    intentPembayaran.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_ID_PEMESANAN, getIntent().getStringExtra(EXTRA_ID_PESMESANAN));
                    intentPembayaran.putExtra(ValidasiBuktiPembayaranActivity.EXTRA_IMAGE_BUKTI_PEMBAYARAN_PEMESANAN, getIntent().getStringExtra(EXTRA_IMAGE_PEMBAYARAN));
                    startActivity(intentPembayaran);
                    finish();
                }
            });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ValidasiAdminActivity.this, PemesananAdminActivity.class));
        finish();
    }
}

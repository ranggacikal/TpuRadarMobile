package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseValidasiBuktiPembayaran;

public class ValidasiBuktiPembayaranActivity extends AppCompatActivity {

    @BindView(R.id.img_bukti_pembayaran_validasi)
    ImageView imgBuktiPembayaranValidasi;
    @BindView(R.id.btn_terima_bukti_pembayaran)
    Button btnTerimaBuktiPembayaran;
    @BindView(R.id.btn_tolak_bukti_pembayaran)
    Button btnTolakBuktiPembayaran;

    public static final String EXTRA_ID_PEMESANAN = "extraIdPemesanan";
    public static final String EXTRA_IMAGE_BUKTI_PEMBAYARAN_PEMESANAN = "extraBuktiPembayaranPemesanan";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_bukti_pembayaran);
        ButterKnife.bind(this);

        final String imgLink = getIntent().getStringExtra(EXTRA_IMAGE_BUKTI_PEMBAYARAN_PEMESANAN);

        Glide.with(ValidasiBuktiPembayaranActivity.this)
                .load(imgLink)
                .error(R.mipmap.ic_launcher)
                .into(imgBuktiPembayaranValidasi);

        btnTerimaBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerimaBuktiPembayaran();
            }
        });

        btnTolakBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TolakBuktiPembayaran();
            }
        });
    }

    private void TolakBuktiPembayaran() {

        String id_pemesanan = getIntent().getStringExtra(EXTRA_ID_PEMESANAN);
        String validasi_bukti_pembayaran = "Di Tolak";
        String status_pesanan = "Belum Selesai";

        ConfigRetrofit.service.ValidasiBuktiPembayaran(id_pemesanan, validasi_bukti_pembayaran, status_pesanan).enqueue(new Callback<ResponseValidasiBuktiPembayaran>() {
            @Override
            public void onResponse(Call<ResponseValidasiBuktiPembayaran> call, Response<ResponseValidasiBuktiPembayaran> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(ValidasiBuktiPembayaranActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ValidasiBuktiPembayaranActivity.this, PemesananAdminActivity.class));
                    finish();
                }else {
                    Toast.makeText(ValidasiBuktiPembayaranActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseValidasiBuktiPembayaran> call, Throwable t) {
                Toast.makeText(ValidasiBuktiPembayaranActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void TerimaBuktiPembayaran() {

        String id_pemesanan = getIntent().getStringExtra(EXTRA_ID_PEMESANAN);
        String validasi_bukti_pembayaran = "Valid";
        String status_pesanan = "Selesai";

        ConfigRetrofit.service.ValidasiBuktiPembayaran(id_pemesanan, validasi_bukti_pembayaran, status_pesanan).enqueue(new Callback<ResponseValidasiBuktiPembayaran>() {
            @Override
            public void onResponse(Call<ResponseValidasiBuktiPembayaran> call, Response<ResponseValidasiBuktiPembayaran> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(ValidasiBuktiPembayaranActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ValidasiBuktiPembayaranActivity.this, PemesananAdminActivity.class));
                    finish();
                }else {
                    Toast.makeText(ValidasiBuktiPembayaranActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseValidasiBuktiPembayaran> call, Throwable t) {
                Toast.makeText(ValidasiBuktiPembayaranActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ValidasiBuktiPembayaranActivity.this, PemesananAdminActivity.class));
        finish();
    }
}

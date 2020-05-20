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
import riska.com.tpuradarmobile.model.ResponseValidasiPerpanjangMakam;

public class ValidasiPerpanjangMakamActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PERPANJANG = "extraIdPerpanjang";
    public static final String EXTRA_IMG_KTP_PERPANJANG = "extraImgKtpPerpanjang";
    public static final String EXTRA_IMG_KK_PERPANJANG = "extraImgKKPerpanjang";
    public static final String EXTRA_IMG_IPTM_PERPANJANG = "extraImgIptmPerpanjang";
    public static final String EXTRA_IMG_BUKTIPEMBAYARAN_PERPANJANG = "extraImgBuktipembayaranPerpanjang";
    @BindView(R.id.img_ktp_validasi)
    ImageView imgKtpValidasi;
    @BindView(R.id.img_kk_validasi)
    ImageView imgKkValidasi;
    @BindView(R.id.img_iptm_validasi)
    ImageView imgIptmValidasi;
    @BindView(R.id.img_buktipembayaran_validasi_perpanjang)
    ImageView imgBuktipembayaranValidasiPerpanjang;
    @BindView(R.id.btn_terima_perpanjang)
    Button btnTerimaPerpanjang;
    @BindView(R.id.btn_tolak_perpanjang)
    Button btnTolakPerpanjang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_perpanjang_makam);
        ButterKnife.bind(this);

        final String imgKtp = getIntent().getStringExtra(EXTRA_IMG_KTP_PERPANJANG);
        final String imgKk = getIntent().getStringExtra(EXTRA_IMG_KK_PERPANJANG);
        final String imgIptm = getIntent().getStringExtra(EXTRA_IMG_IPTM_PERPANJANG);
        final String imgBuktiPembayaran = getIntent().getStringExtra(EXTRA_IMG_BUKTIPEMBAYARAN_PERPANJANG);

        Glide.with(ValidasiPerpanjangMakamActivity.this)
                .load(imgKtp)
                .into(imgKtpValidasi);

        Glide.with(ValidasiPerpanjangMakamActivity.this)
                .load(imgKk)
                .into(imgKkValidasi);

        Glide.with(ValidasiPerpanjangMakamActivity.this)
                .load(imgIptm)
                .into(imgIptmValidasi);

        Glide.with(ValidasiPerpanjangMakamActivity.this)
                .load(imgBuktiPembayaran)
                .into(imgBuktipembayaranValidasiPerpanjang);

        btnTerimaPerpanjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TerimaPerpanjangMakam();
            }
        });

        btnTolakPerpanjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TolakPerpanjang();
            }
        });
    }

    private void TolakPerpanjang() {

        String id_jenazah = getIntent().getStringExtra(EXTRA_ID_PERPANJANG);
        String validasi_bukti_pembayaran = "Di Tolak";

        ConfigRetrofit.service.ValidasiPerpanjang(id_jenazah, validasi_bukti_pembayaran).enqueue(new Callback<ResponseValidasiPerpanjangMakam>() {
            @Override
            public void onResponse(Call<ResponseValidasiPerpanjangMakam> call, Response<ResponseValidasiPerpanjangMakam> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    Toast.makeText(ValidasiPerpanjangMakamActivity.this, "Penolakan Validasi Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ValidasiPerpanjangMakamActivity.this, DataJenazahActivity.class));
                    finish();
                }else {
                    Toast.makeText(ValidasiPerpanjangMakamActivity.this, "Penolakan Validasi GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseValidasiPerpanjangMakam> call, Throwable t) {
                Toast.makeText(ValidasiPerpanjangMakamActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void TerimaPerpanjangMakam() {

        String id_jenazah = getIntent().getStringExtra(EXTRA_ID_PERPANJANG);
        String validasi_bukti_pembayaran = "Valid";

        ConfigRetrofit.service.ValidasiPerpanjang(id_jenazah, validasi_bukti_pembayaran).enqueue(new Callback<ResponseValidasiPerpanjangMakam>() {
            @Override
            public void onResponse(Call<ResponseValidasiPerpanjangMakam> call, Response<ResponseValidasiPerpanjangMakam> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    Toast.makeText(ValidasiPerpanjangMakamActivity.this, "Validasi Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ValidasiPerpanjangMakamActivity.this, DataJenazahActivity.class));
                    finish();
                }else{
                    Toast.makeText(ValidasiPerpanjangMakamActivity.this, "Validasi GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseValidasiPerpanjangMakam> call, Throwable t) {
                Toast.makeText(ValidasiPerpanjangMakamActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ValidasiPerpanjangMakamActivity.this, DataJenazahActivity.class));
        finish();
    }
}

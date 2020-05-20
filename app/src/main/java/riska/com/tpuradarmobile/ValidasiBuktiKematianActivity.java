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
import riska.com.tpuradarmobile.model.ResponseValidasiBuktiKematian;

public class ValidasiBuktiKematianActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PESMESANAN = "extraIdPemesanan";
    public static final String EXTRA_IMAGE_PEMESANAN = "extraImagePesanan";
    @BindView(R.id.img_bukti_kematian)
    ImageView imgBuktiKematian;
    @BindView(R.id.btn_terima_bukti_kematian)
    Button btnTerimaBuktiKematian;
    @BindView(R.id.btn_tolak_bukti_kematian)
    Button btnTolakBuktiKematian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_bukti_kematian);
        ButterKnife.bind(this);

        final String imgLink = getIntent().getStringExtra(EXTRA_IMAGE_PEMESANAN);

        Glide.with(ValidasiBuktiKematianActivity.this)
                .load(imgLink)
                .error(R.mipmap.ic_launcher)
                .into(imgBuktiKematian);

        btnTerimaBuktiKematian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidasiBuktiKematian();
            }
        });

        btnTolakBuktiKematian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TolakBuktiKematian();
            }
        });

    }

    private void TolakBuktiKematian() {

        String id_pemesanan = getIntent().getStringExtra(EXTRA_ID_PESMESANAN);
        String validasi_bukti_kematian = "Di Tolak";

        ConfigRetrofit.service.ValidasiBuktiKematian(id_pemesanan, validasi_bukti_kematian).enqueue(new Callback<ResponseValidasiBuktiKematian>() {
            @Override
            public void onResponse(Call<ResponseValidasiBuktiKematian> call, Response<ResponseValidasiBuktiKematian> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status==1){
                    Toast.makeText(ValidasiBuktiKematianActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ValidasiBuktiKematianActivity.this, PemesananAdminActivity.class));
                    finish();
                }else{
                    Toast.makeText(ValidasiBuktiKematianActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseValidasiBuktiKematian> call, Throwable t) {
                Toast.makeText(ValidasiBuktiKematianActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ValidasiBuktiKematian() {
        String id_pemesanan = getIntent().getStringExtra(EXTRA_ID_PESMESANAN);
        String validasi_bukti_kematian = "Valid";

        ConfigRetrofit.service.ValidasiBuktiKematian(id_pemesanan, validasi_bukti_kematian).enqueue(new Callback<ResponseValidasiBuktiKematian>() {
            @Override
            public void onResponse(Call<ResponseValidasiBuktiKematian> call, Response<ResponseValidasiBuktiKematian> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                if (status==1){
                    Toast.makeText(ValidasiBuktiKematianActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ValidasiBuktiKematianActivity.this, PemesananAdminActivity.class));
                    finish();
                }else{
                    Toast.makeText(ValidasiBuktiKematianActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseValidasiBuktiKematian> call, Throwable t) {
                Toast.makeText(ValidasiBuktiKematianActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ValidasiBuktiKematianActivity.this, PemesananAdminActivity.class));
        finish();
    }
}

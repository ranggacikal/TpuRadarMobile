package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.adapter.BeritaAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataBeritaItem;
import riska.com.tpuradarmobile.model.ResponseDataBerita;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_pesan)
    ImageView imgPesan;
    @BindView(R.id.linear_pesan)
    LinearLayout linearPesan;
    @BindView(R.id.img_pembayaran)
    ImageView imgPembayaran;
    @BindView(R.id.linear_pembayaran)
    LinearLayout linearPembayaran;
    @BindView(R.id.img_information)
    ImageView imgInformation;
    @BindView(R.id.linear_informasi)
    LinearLayout linearInformasi;
    @BindView(R.id.recycler_berita)
    RecyclerView recyclerBerita;
    @BindView(R.id.bottomNavigationMain)
    BottomNavigationView bottomNavigationMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationClicked();
        LoadBerita();

        linearPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PemesananActivity.class));
                finish();
            }
        });

        linearPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataPesananActivity.class));
                finish();
            }
        });

        linearInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InformasiUserActivity.class));
                finish();
            }
        });
    }

    private void LoadBerita() {

        ConfigRetrofit.service.GetBerita().enqueue(new Callback<ResponseDataBerita>() {
            @Override
            public void onResponse(Call<ResponseDataBerita> call, Response<ResponseDataBerita> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataBeritaItem> beritaList = response.body().getDataBerita();
                    BeritaAdapter adapter = new BeritaAdapter(MainActivity.this, beritaList);
                    recyclerBerita.setAdapter(adapter);
                    recyclerBerita.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }else{
                    Toast.makeText(MainActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataBerita> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void BottomNavigationClicked() {
        bottomNavigationMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_order:
                        startActivity(new Intent(MainActivity.this, DataPesananActivity.class));
                        finish();
                        break;

                    case R.id.action_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        finish();
                        break;
                }

                return false;
            }
        });

    }
}

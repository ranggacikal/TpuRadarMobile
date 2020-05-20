package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.adapter.BeritaAdminAdapter;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.DataBeritaItem;
import riska.com.tpuradarmobile.model.ResponseDataBerita;

public class BeritaAdminActivity extends AppCompatActivity {

    @BindView(R.id.recycler_berita_admin)
    RecyclerView recyclerBeritaAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_admin);
        ButterKnife.bind(this);

        LoadDataBerita();
    }

    private void LoadDataBerita() {

        ConfigRetrofit.service.GetBerita().enqueue(new Callback<ResponseDataBerita>() {
            @Override
            public void onResponse(Call<ResponseDataBerita> call, Response<ResponseDataBerita> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataBeritaItem> BeritaList = response.body().getDataBerita();
                    BeritaAdminAdapter adapter = new BeritaAdminAdapter(BeritaAdminActivity.this, BeritaList);
                    recyclerBeritaAdmin.setAdapter(adapter);
                    recyclerBeritaAdmin.setLayoutManager(new LinearLayoutManager(BeritaAdminActivity.this));
                }else{
                    Toast.makeText(BeritaAdminActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataBerita> call, Throwable t) {
                Toast.makeText(BeritaAdminActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tambah_berita, menu);
        MenuItem menuItem = menu.findItem(R.id.tambah_berita_admin_action);
        menuItem.setIcon(R.drawable.ic_tambah);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.tambah_berita_admin_action:
                startActivity(new Intent(this, InputBeritaActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BeritaAdminActivity.this, AdminActivity.class));
        finish();
    }
}

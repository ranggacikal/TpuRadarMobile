package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.card_pemesanan_admin)
    CardView cardPemesananAdmin;
    @BindView(R.id.card_updateinformasi_admin)
    CardView cardUpdateinformasiAdmin;
    @BindView(R.id.card_datajenazah_admin)
    CardView cardDatajenazahAdmin;
    @BindView(R.id.card_inputberita_admin)
    CardView cardInputberitaAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);

        cardPemesananAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, PemesananAdminActivity.class));
                finish();
            }
        });

        cardInputberitaAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, BeritaAdminActivity.class));
                finish();
            }
        });

        cardDatajenazahAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, DataJenazahActivity.class));
                finish();
            }
        });

        cardUpdateinformasiAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, InformasiAdminActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_admin, menu);
        MenuItem menuItem = menu.findItem(R.id.profile_admin_action);
        menuItem.setIcon(R.drawable.ic_profile_white);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.profile_admin_action:
                startActivity(new Intent(this, ProfileAdminActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailBeritaActivity extends AppCompatActivity {

    @BindView(R.id.img_detail_berita)
    ImageView imgDetailBerita;
    @BindView(R.id.text_detail_judul_berita)
    TextView textDetailJudulBerita;
    @BindView(R.id.text_detail_tanggal_berita)
    TextView textDetailTanggalBerita;
    @BindView(R.id.text_detail_isi_berita)
    TextView textDetailIsiBerita;

    public static final String EXTRA_IMAGE_BERITA = "extraImageBerita";
    public static final String EXTRA_JUDUL_BERITA = "extraJudulBerita";
    public static final String EXTRA_TANGGAL_BERITA = "extraTanggalBerita";
    public static final String EXTRA_ISI_BERITA = "extraIsiBerita";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        ButterKnife.bind(this);

        final String imgLink = getIntent().getStringExtra(EXTRA_IMAGE_BERITA);

        Glide.with(DetailBeritaActivity.this)
                .load(imgLink)
                .error(R.mipmap.ic_launcher)
                .into(imgDetailBerita);

        textDetailJudulBerita.setText(getIntent().getStringExtra(EXTRA_JUDUL_BERITA));
        textDetailTanggalBerita.setText(getIntent().getStringExtra(EXTRA_TANGGAL_BERITA));
        textDetailIsiBerita.setText(getIntent().getStringExtra(EXTRA_ISI_BERITA));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DetailBeritaActivity.this, MainActivity.class));
        finish();
    }
}

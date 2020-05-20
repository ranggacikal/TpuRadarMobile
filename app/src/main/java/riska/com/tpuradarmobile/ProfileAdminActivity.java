package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;

public class ProfileAdminActivity extends AppCompatActivity {

    @BindView(R.id.img_profile_admin)
    CircleImageView imgProfileAdmin;
    @BindView(R.id.text_nama_profile_admin)
    TextView textNamaProfileAdmin;
    @BindView(R.id.text_email_profile_admin)
    TextView textEmailProfileAdmin;
    @BindView(R.id.btn_logout_admin)
    Button btnLogoutAdmin;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        final String imgLink = preferencedConfig.getPreferenceImage();

        Glide.with(this)
                .load(imgLink)
                .error(R.mipmap.ic_launcher)
                .into(imgProfileAdmin);

        textNamaProfileAdmin.setText(preferencedConfig.getPreferenceNamaLengkap());
        textEmailProfileAdmin.setText(preferencedConfig.getPreferenceEmail());

        btnLogoutAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileAdminActivity.this, "Logged Out...", Toast.LENGTH_SHORT).show();
                preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, false);
                startActivity(new Intent(ProfileAdminActivity.this, LoginSebagaiActivity.class));
                finish();
            }
        });
    }
}

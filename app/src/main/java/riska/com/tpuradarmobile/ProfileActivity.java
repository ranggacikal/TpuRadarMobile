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

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.img_profile)
    CircleImageView imgProfile;
    @BindView(R.id.text_nama_profile)
    TextView textNamaProfile;
    @BindView(R.id.text_email_profile)
    TextView textEmailProfile;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    private SharedPreferencedConfig preferencedConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        textNamaProfile.setText(preferencedConfig.getPreferenceNamaLengkap());
        textEmailProfile.setText(preferencedConfig.getPreferenceEmail());

        final String linkImage = preferencedConfig.getPreferenceImage();

        Glide.with(ProfileActivity.this)
                .load(linkImage)
                .error(R.mipmap.ic_launcher)
                .into(imgProfile);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Logged Out...", Toast.LENGTH_SHORT).show();
                preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, false);
                startActivity(new Intent(ProfileActivity.this, LoginSebagaiActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        finish();
    }
}

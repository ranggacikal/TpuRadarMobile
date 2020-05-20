package riska.com.tpuradarmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;

public class LoginSebagaiActivity extends AppCompatActivity {

    @BindView(R.id.btn_sebagai_user)
    Button btnSebagaiUser;
    @BindView(R.id.btn_sebagai_admin)
    Button btnSebagaiAdmin;

    private SharedPreferencedConfig preferencedConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sebagai);
        ButterKnife.bind(this);
        preferencedConfig = new SharedPreferencedConfig(this);

        if (preferencedConfig.getPreferenceIsLogin()){
            String level = preferencedConfig.getPreferenceLevelUser();

            if (level.equals("User")){
                startActivity(new Intent(LoginSebagaiActivity.this, MainActivity.class));
                finish();
            }else if (level.equals("Admin")){
                startActivity(new Intent(LoginSebagaiActivity.this, AdminActivity.class));
                finish();
            }
        }

        btnSebagaiUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSebagaiActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnSebagaiAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSebagaiActivity.this, LoginAdminActivity.class));
                finish();
            }
        });

    }
}

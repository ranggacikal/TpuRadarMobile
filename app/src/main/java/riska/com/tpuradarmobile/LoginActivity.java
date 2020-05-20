package riska.com.tpuradarmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.SharedPreference.SharedPreferencedConfig;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseLoginUser;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_email_login)
    EditText edtEmailLogin;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.txtview_lupapassword)
    TextView txtviewLupapassword;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        txtviewLupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ValidasiResetPasswordActivity.class));
                finish();
            }
        });

        if (preferencedConfig.getPreferenceIsLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void LoginUser() {

        String email = edtEmailLogin.getText().toString();
        String password = edtPasswordLogin.getText().toString();

        if (email.isEmpty()){
            edtEmailLogin.setError("Email Tidak Boleh Kosong");
            edtEmailLogin.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmailLogin.setError("Harap Masukan Email Yang Valid");
            edtEmailLogin.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPasswordLogin.setError("Password Tidak Boleh Kosong");
            edtPasswordLogin.requestFocus();
            return;
        }

        final ProgressDialog progDialog = ProgressDialog.show(LoginActivity.this, "", "Loading..", false);

        ConfigRetrofit.service.LoginUser(email, password).enqueue(new Callback<ResponseLoginUser>() {
            @Override
            public void onResponse(Call<ResponseLoginUser> call, Response<ResponseLoginUser> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                progDialog.dismiss();

                if (status == 1){
                    String level = response.body().getDataLoginUser().getLevel();

                    if (level.equals("User")){
                        Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        String namaLengkap = response.body().getDataLoginUser().getNama();
                        String emailUser = response.body().getDataLoginUser().getEmailUser();
                        String noTelpon = response.body().getDataLoginUser().getNoTelpon();
                        String userId = response.body().getDataLoginUser().getUserId();
                        String levelUser = response.body().getDataLoginUser().getLevel();
                        String imageUser = response.body().getDataLoginUser().getImageUser();

                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_USER, userId);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_LENGKAP, namaLengkap);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, emailUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE, imageUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_LEVEL_USER, levelUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NO_TELPON, noTelpon);
                        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Username/Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLoginUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, LoginSebagaiActivity.class));
        finish();
    }
}

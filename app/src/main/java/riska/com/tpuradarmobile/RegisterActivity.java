package riska.com.tpuradarmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import riska.com.tpuradarmobile.api.ConfigRetrofit;
import riska.com.tpuradarmobile.model.ResponseRegisterUser;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.img_register_user)
    CircleImageView imgRegisterUser;
    @BindView(R.id.btn_pilih_img_register)
    Button btnPilihImgRegister;
    @BindView(R.id.edt_nama_register)
    EditText edtNamaRegister;
    @BindView(R.id.edt_notelpon_register)
    EditText edtNotelponRegister;
    @BindView(R.id.edt_email_register)
    EditText edtEmailRegister;
    @BindView(R.id.edt_password_register)
    EditText edtPasswordRegister;
    @BindView(R.id.edt_repassword_register)
    EditText edtRepasswordRegister;
    @BindView(R.id.btn_register_user)
    Button btnRegisterUser;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });

        btnPilihImgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });
    }

    private void PilihGambar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                    imgRegisterUser.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap!=null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        }else if (bitmap==null){
            Toast.makeText(this, "Gambar Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void RegisterUser() {

        String nama = edtNamaRegister.getText().toString();
        String noTelpon = edtNotelponRegister.getText().toString();
        String emailUser = edtEmailRegister.getText().toString();
        String passwordUser = edtPasswordRegister.getText().toString();
        String respassword = edtRepasswordRegister.getText().toString();
        String level = "User";
        String image = imageToString();

        if (nama.isEmpty()){
            edtNamaRegister.setError("Nama Lengkap Tidak Boleh Kosong");
            edtNamaRegister.requestFocus();
            return;
        }

        if (noTelpon.isEmpty()){
            edtNotelponRegister.setError("No Telpon Tidak Boleh Kosong");
            edtNotelponRegister.requestFocus();
            return;
        }

        if (emailUser.isEmpty()){
            edtEmailRegister.setError("Email Tidak Boleh Kosong");
            edtEmailRegister.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
            edtEmailRegister.setError("Harap Masukan Email Yang Valid");
            edtEmailRegister.requestFocus();
            return;
        }

        if (passwordUser.isEmpty()){
            edtPasswordRegister.setError("Password Tidak Boleh Kosong");
            edtPasswordRegister.requestFocus();
            return;
        }

        if (respassword.isEmpty()){
            edtRepasswordRegister.setError("Re-Passwird Tidak Boleh Kosong");
            edtRepasswordRegister.requestFocus();
            return;
        }

        if (!respassword.equals(passwordUser)){
            edtRepasswordRegister.setError("Tidak Sama Dangan Password");
            edtRepasswordRegister.requestFocus();
            return;
        }

        ConfigRetrofit.service.RegisterUser(nama, noTelpon, emailUser, passwordUser, level, image).enqueue(new Callback<ResponseRegisterUser>() {
            @Override
            public void onResponse(Call<ResponseRegisterUser> call, Response<ResponseRegisterUser> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    edtNamaRegister.setText("");
                    edtEmailRegister.setText("");
                    edtNotelponRegister.setText("");
                    edtPasswordRegister.setText("");
                    edtRepasswordRegister.setText("");
                    imgRegisterUser.setImageResource(R.drawable.avatar);
                    btnPilihImgRegister.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegisterUser> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}

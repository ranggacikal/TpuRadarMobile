package riska.com.tpuradarmobile;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CetakBuktiActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PEMESANAN = "extraIdPemesanan";
    public static final String EXTRA_NAMA_PEMESANAN = "extraNamaPemesanan";
    public static final String EXTRA_TANGGAL_PEMESANAN = "extraTanggalPemesanan";
    public static final String EXTRA_UKURAN_PETAK_PEMESANAN = "extraUkuranPetakPemesanan";
    @BindView(R.id.text_nama_pemesan_cetak)
    TextView textNamaPemesanCetak;
    @BindView(R.id.text_id_pemesan_cetak)
    TextView textIdPemesanCetak;
    @BindView(R.id.text_tanggal_pemesan_cetak)
    TextView textTanggalPemesanCetak;
    @BindView(R.id.text_ukuranpetak_pemesan_cetak)
    TextView textUkuranpetakPemesanCetak;
    @BindView(R.id.btn_cetak_bukti)
    Button btnCetakBukti;
    @BindView(R.id.pdfCetak)
    LinearLayout pdfCetak;

    private Bitmap bitmap;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetak_bukti);
        ButterKnife.bind(this);

        context = this;

        ActivityCompat.requestPermissions(CetakBuktiActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        textIdPemesanCetak.setText(getIntent().getStringExtra(EXTRA_ID_PEMESANAN));
        textNamaPemesanCetak.setText(getIntent().getStringExtra(EXTRA_NAMA_PEMESANAN));
        textTanggalPemesanCetak.setText(getIntent().getStringExtra(EXTRA_TANGGAL_PEMESANAN));
        textUkuranpetakPemesanCetak.setText(getIntent().getStringExtra(EXTRA_UKURAN_PETAK_PEMESANAN));

        btnCetakBukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("size"," "+pdfCetak.getWidth() +"  "+pdfCetak.getWidth());
                bitmap = loadBitmapFromView(pdfCetak, pdfCetak.getWidth(), pdfCetak.getHeight());
                createPdf();
            }
        });

    }

    private void createPdf() {

        String namafile = getIntent().getStringExtra(EXTRA_ID_PEMESANAN);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/"+namafile+".pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
            Log.e("CetakBuktiActivity", e.getMessage() );
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();
    }

    private void openGeneratedPDF() {
        String namafile = getIntent().getStringExtra(EXTRA_ID_PEMESANAN);
        File file = new File("/sdcard/"+namafile+".pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName()+".provider", file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(CetakBuktiActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }

    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }
}

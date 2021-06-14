package com.example.baicanhan_nguyenphuongdong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baicanhan_nguyenphuongdong.model.Product;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    private EditText etName, etDes, etPrice, etDate;
    private Button btnAdd, btnCancel;
    private ImageView img;
    private ImageButton camera, gallery;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_LIBRARY = 456;
    private SQLiteProductHelper sqLite;
    private List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initView();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_LIBRARY);
            }
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                dialog.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chuyen data img sang byte
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, byteArrayOutputStream);
                }
                byte[] photo = byteArrayOutputStream.toByteArray();

                String name = etName.getText().toString();
                String des = etDes.getText().toString();
                Double price = Double.parseDouble(etPrice.getText().toString());
                String date = etDate.getText().toString();
                Product product = new Product(photo, name, des, price, date);
                long add = sqLite.addProduct(product);
                if(add > 0){
                    Toast.makeText(AddProductActivity.this, "Them Thanh cong", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddProductActivity.this, AllProductActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddProductActivity.this, "Them That bai", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        etName = findViewById(R.id.etNameUD);
        etDes = findViewById(R.id.etDesUD);
        etPrice = findViewById(R.id.etPriceUD);
        etDate = findViewById(R.id.etDateUD);
        btnAdd = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnDelete);
        img = findViewById(R.id.imageViewUD);
        camera = findViewById(R.id.cameraUD);
        gallery = findViewById(R.id.galleryUD);
        sqLite = new SQLiteProductHelper(this);
        productList = new ArrayList<>();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA  && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_LIBRARY  && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
package com.example.baicanhan_nguyenphuongdong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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

public class UpdateDeleteProductActivity extends AppCompatActivity {
    private EditText name, des, price, date, id;
    private Button btnUpdate, btnDelete, btnCancel;
    private ImageView img;
    private ImageButton camera, gallery;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_LIBRARY = 456;
    private SQLiteProductHelper sqLite;
    private List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_product);
        initView();
        id.setEnabled(false);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("products");
        System.out.println(product);

        id.setText(String.valueOf(product.getId()));
        name.setText(product.getName());
        date.setText(product.getDate());
        price.setText(product.getPrice().toString());
        des.setText(product.getDescription());

        byte[] HA = product.getPhoto();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(HA, 0, HA.length);
        img.setImageBitmap(bitmap1);

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
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },y,m,d);
                dialog.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idud = product.getId();
                String nameud = name.getText().toString();
                String dateud = date.getText().toString();
                Double priceud = Double.parseDouble(price.getText().toString());
                String desud = des.getText().toString();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] photo = byteArrayOutputStream.toByteArray();

                Product productUpdate = new Product(idud, photo, nameud, desud, priceud, dateud);
                int update = sqLite.updateProduct(productUpdate);
                if(update > 0 ){
                    Toast.makeText(UpdateDeleteProductActivity.this, "Thay doi thanh cong", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(UpdateDeleteProductActivity.this, AllProductActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(UpdateDeleteProductActivity.this, "Thay doi that bai", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idud = product.getId();
                long delete = sqLite.deleteProduct(idud);
                if(delete > 0 ){
                    Toast.makeText(UpdateDeleteProductActivity.this, "Xoa thanh cong", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(UpdateDeleteProductActivity.this, AllProductActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(UpdateDeleteProductActivity.this, "Xoa that bai", Toast.LENGTH_LONG).show();
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
        id = findViewById(R.id.etID);
        name = findViewById(R.id.etNameUD);
        des = findViewById(R.id.etDesUD);
        price = findViewById(R.id.etPriceUD);
        date = findViewById(R.id.etDateUD);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
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
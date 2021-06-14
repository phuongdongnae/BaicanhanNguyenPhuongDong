package com.example.baicanhan_nguyenphuongdong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.example.baicanhan_nguyenphuongdong.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SQLiteProductHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Product.db";
    private static final int DATABASE_VERSION = 1;
    public SQLiteProductHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table product(id integer primary key autoincrement, image blob, name text, description text, price real, date text)";
        db.execSQL(sql);
    }

    public long addProduct(Product product){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO product values(null, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, product.getPhoto());
        statement.bindString(2, product.getName());
        statement.bindString(3, product.getDescription());
        statement.bindDouble(4, product.getPrice());
        statement.bindString(5, product.getDate());
        return statement.executeInsert();
    }

    public int updateProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", product.getPhoto());
        contentValues.put("name", product.getName());
        contentValues.put("description", product.getDescription());
        contentValues.put("price", product.getPrice());
        contentValues.put("date", product.getDate());
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(product.getId())};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("product", contentValues, whereClause, whereArgs);
    }

    public long deleteProduct(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("product", whereClause, whereArgs);
    }

    public List<Product> getAll(){
        List<Product> listProduct = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("product", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            listProduct.add(new Product(cursor.getInt(0), cursor.getBlob(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getString(5)));
        }
        return listProduct;
    }

    public List<Product> search(String key){
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereclause = "name LIKE '%" + key + "%'";

        Cursor cursor = sqLiteDatabase.query("product", null, whereclause, null, null, null, null);
        while (cursor.moveToNext()) {
            productList.add(new Product(cursor.getInt(0), cursor.getBlob(1), cursor.getString(2), cursor.getString(3), cursor.getDouble(4), cursor.getString(5)));
        }
        return productList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

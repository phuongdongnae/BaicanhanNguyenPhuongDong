package com.example.baicanhan_nguyenphuongdong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baicanhan_nguyenphuongdong.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText login_username, login_password;
    private Button btnLogin;
    private CheckBox showPass;
    private TextView createAccount;
    private User user;
    private SQLiteUserHelper sqLiteUserHelper;
    private static final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        sqLiteUserHelper = new SQLiteUserHelper(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showPass.setText("Hide Password");
                    login_password.setInputType(InputType.TYPE_CLASS_TEXT);
                    login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());// show password
                } else {
                    showPass.setText("Show Password");
                    login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());// hide password
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login_username.equals("") || login_username.length()==0 ||
                        login_password.equals("") || login_password.length()==0){
                    Toast.makeText(LoginActivity.this, "All fields need to required",Toast.LENGTH_SHORT).show();
                }
                else {
                    String u = login_username.getText().toString();
                    String p = login_password.getText().toString();
                    Boolean checkUser = sqLiteUserHelper.checkUser(u, p);
                    if(checkUser == true){
                        Toast.makeText(LoginActivity.this, "Sign In Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        User user1 = new User(u,p);
                        intent.putExtra("account",user1);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        login_username = findViewById(R.id.login_emailid);
        login_password = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btnLogin);
        showPass = findViewById(R.id.show_hide_password);
        createAccount = findViewById(R.id.createAccount);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                if (data != null){
                    user = (User) data.getSerializableExtra("data");
                    login_username.setText(user.getUsername());
                    login_password.setText(user.getPassword());
                }
            }
        }
    }
}
package com.example.baicanhan_nguyenphuongdong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baicanhan_nguyenphuongdong.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernamesu, passsu, confpasssu;
    private TextView already;
    private Button btnSignup;
    private CheckBox conditions;
    private SQLiteUserHelper sqLiteUserHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        sqLiteUserHelper = new SQLiteUserHelper(this);

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernamesu.getText().toString();
                String pass = passsu.getText().toString();
                String confpass = confpasssu.getText().toString();
                if(username.equals("") || username.length()==0 || pass.equals("") || pass.length()==0 || confpass.equals("") || confpass.length()==0){
                    Toast.makeText(RegisterActivity.this, "All fields need to required",Toast.LENGTH_SHORT).show();
                }
                else if (!confpass.equals(pass)){
                    Toast.makeText(RegisterActivity.this, "Both pass doesn't match",Toast.LENGTH_SHORT).show();
                }
                else if(!conditions.isChecked()){
                    Toast.makeText(RegisterActivity.this, "Please select Terms and Conditions",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuser = sqLiteUserHelper.checkUsername(username);
                    if(checkuser == false){
                        Boolean insert = sqLiteUserHelper.insertData(username, pass);
                        if(insert==true){
                            Toast.makeText(RegisterActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            User u = new User(username, pass);
                            Intent intent = new Intent();
                            intent.putExtra("data", u);
                            setResult(RESULT_OK, intent);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "User already exitst", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        usernamesu = findViewById(R.id.signup_username);
        passsu = findViewById(R.id.signup_password);
        confpasssu = findViewById(R.id.signup_confirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        already = findViewById(R.id.already_user);
        conditions = findViewById(R.id.terms_conditions);
    }
}
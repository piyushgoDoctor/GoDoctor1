package com.example.godoctor.myapplication1;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     EditText email;
     EditText mobile;
     EditText password;
    TextInputLayout inputLayoutEmail;
    TextInputLayout inputLayoutPassword;
    TextInputLayout inputLayoutMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= (EditText) findViewById(R.id.email);
        mobile= (EditText) findViewById(R.id.mobile);
        password= (EditText) findViewById(R.id.password);
        inputLayoutEmail= (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword= (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutMobile= (TextInputLayout) findViewById(R.id.input_layout_mobile);
        mobile.addTextChangedListener(new MyTextWatcher(mobile));
        email.addTextChangedListener(new MyTextWatcher(email));
        password.addTextChangedListener(new MyTextWatcher(password));

        Button signUp= (Button) findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String EMAIL_REGEX = "^(.+)@(.+)$";
//                String ema=email.getText().toString();
//                if(ema.matches(EMAIL_REGEX)){
//                    if(/*mobile.getText().toString().matches("(\\d{3})(\\[-])(\\d{4})$") &&*/ mobile.getText().toString().length()==10){
//                        if(password.getText().toString().matches("^#^")){
//
//                            Toast.makeText(MainActivity.this,"masti.",Toast.LENGTH_LONG).show();
//
//
//                        }else{
//                            Toast.makeText(MainActivity.this,"Password must contain a special character..",Toast.LENGTH_LONG).show();
//                        }
//                    }else{
//                        Toast.makeText(MainActivity.this,"Check Your Mobile.",Toast.LENGTH_LONG).show();
//                    }
//                }else{
//                    Toast.makeText(MainActivity.this,"Check Your Email.",Toast.LENGTH_LONG).show();
//                }

                submitForm();
                char[] pass=password.getText().toString().toCharArray();
                boolean paasT=false;
                for(int i=0;i<pass.length;i++){
                    if(pass[i]<=43 && pass[i]>=32){
                        paasT=true;
                        break;

                    }else paasT=false;
                }
                if(paasT){
                    Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
                    Intent in= new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(in);
                }else             inputLayoutMobile.setErrorEnabled(false);

            }
        });
    }
    private void submitForm() {
        if (!validateMobile()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }


    }

    private boolean validateMobile() {
        if (!mobile.getText().toString().trim().isEmpty() && mobile.getText().toString().length()<10) {
            inputLayoutMobile.setError("Enter Correct mobile number");
//            requestFocus(mobile);
            return false;
        } else {
            inputLayoutMobile.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email1 = email.getText().toString().trim();

        if (email1.isEmpty() || !isValidEmail(email1)) {
            inputLayoutEmail.setError("Enter Correct Email.");
//            requestFocus(email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Password Cannot be Empty.");
//            requestFocus(password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.email:
                    validateMobile();
                    break;
                case R.id.mobile:
                    validateEmail();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
            }
        }
    }
}

package com.goldshop;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.goldshop.service.Response;
import com.goldshop.service.ResponseListener;
import com.goldshop.service.ServerRequest;
import com.goldshop.utility.Connection;
import com.goldshop.utility.Utils;

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity implements ResponseListener{

    private EditText firstName,lastName,companyName,city,contactNo,email,password,confirmPassword;
    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        h=new Handler();

        firstName= (EditText) findViewById(R.id.firstName_editText_signUp);
        lastName= (EditText) findViewById(R.id.lastName_editText_signUp);
        companyName= (EditText) findViewById(R.id.companyName_editText_signUp);
        city= (EditText) findViewById(R.id.city_editText_signUp);
        contactNo= (EditText) findViewById(R.id.contactNo_editText_signUp);
        email= (EditText) findViewById(R.id.email_editText_signUp);
        password= (EditText) findViewById(R.id.password_editText_signUp);
        confirmPassword= (EditText) findViewById(R.id.confirm_password_editText_signUp);
    }
    public void backToLogin(View view){
        finish();
    }
    public void attemptSignUp(){
        String firstNameValue=firstName.getText().toString();
        String lastNameValue=lastName.getText().toString();
        String companyNameValue=companyName.getText().toString();
        String cityValue=city.getText().toString();
        String contactNoValue=contactNo.getText().toString();
        String emailValue=email.getText().toString();
        String passwordValue=password.getText().toString();
        String confirmPasswordValue=confirmPassword.getText().toString();

        boolean cancel=false;
        View focusView=null;

        if(TextUtils.isEmpty(firstNameValue)){
            firstName.setError("This field is required !!!");
            focusView=firstName;
            cancel=true;
        }else if(TextUtils.isEmpty(companyNameValue)){
            companyName.setError("This field is required !!!");
            focusView=companyName;
            cancel=true;
        }else if(TextUtils.isEmpty(cityValue)){
            city.setError("This field is required !!!");
            focusView=city;
            cancel=true;
        }else if(TextUtils.isEmpty(contactNoValue)){
            contactNo.setError("This field is required !!!");
            focusView=contactNo;
            cancel=true;
        }else if(TextUtils.isEmpty(emailValue)){
            email.setError("This field is required !!!");
            focusView=email;
            cancel=true;
        }else if(TextUtils.isEmpty(emailValue)){
            email.setError("Enter valid email address !!!");
            focusView=email;
            cancel=true;
        }else if(TextUtils.isEmpty(passwordValue)){
            password.setError("This field is required !!!");
            focusView=password;
            cancel=true;
        }else if(TextUtils.isEmpty(confirmPasswordValue)){
            confirmPassword.setError("This field is required !!!");
            focusView=confirmPassword;
            cancel=true;
        }else if(!passwordValue.equals(confirmPasswordValue)){
            confirmPassword.setError("password should be matched !!!");
            focusView=confirmPassword;
            cancel=true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            if (Utils.ChechInternetAvalebleOrNot(SignUpActivity.this)) {

                Utils.showLoader(SignUpActivity.this);
                ServerRequest
                        .postRequest(
                                Connection.BASE_URL + "signUp",
                                getSignUpData(firstNameValue,lastNameValue,companyNameValue,emailValue,passwordValue,cityValue,contactNoValue),
                                SignUpActivity.this,
                                ResponseListener.REQUEST_SIGN_UP);

            } else {
                //   Utils.shonterwSnakeBar(layout_view, "internet not connected !!!", Color.RED);Toast.makeText(LoginActivity.this,"Internet not connected !!!",Toast.LENGTH_LONG).show();
                Toast.makeText(SignUpActivity.this,"Internet not connected !!!",Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    public JSONObject getSignUpData(String firstName,String lastName,String company,String email,String password,String city,String mobileNo) {
        JSONObject json = new JSONObject();
        try {

            json.put("firstName", firstName);
            json.put("lastName", lastName);
            json.put("company", company);
            json.put("email", email);
            json.put("password", password);
            json.put("city", city);
            json.put("contactNo", mobileNo);
            json.put("mobileType", "0");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

    @Override
    public void onResponse(final Response response, final int rid) {

        Utils.dissmissLoader();
        h.post(new Runnable() {

            @Override
            public void run() {

                if (rid == ResponseListener.REQUEST_SIGN_UP) {

                    if (response.isError()) {
                        Toast.makeText(SignUpActivity.this, response.getErrorMsg(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getData() != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.getData());
                            String status=jsonObject.getString("status");

                            Toast.makeText(SignUpActivity.this,jsonObject.getString("msg"),Toast.LENGTH_LONG).show();
                            Log.d("json_response", response.getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }
    public void clickSignUp(View view){
       attemptSignUp();
    }
}

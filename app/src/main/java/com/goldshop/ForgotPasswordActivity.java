package com.goldshop;

import android.content.Intent;
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
import com.goldshop.utility.Preference;
import com.goldshop.utility.Utils;

import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity implements ResponseListener{

    private EditText email;
    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        h=new Handler();

        email= (EditText) findViewById(R.id.email_editText_forgotPass);
    }

    public void backToLogin(View view){
        finish();
    }
    public void clickConfirm(View view){
       attemptForgotPassword();
    }

    public void attemptForgotPassword(){
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailValue=email.getText().toString();

        boolean cancel=false;
        View focusView=null;

        if(TextUtils.isEmpty(emailValue)){
            email.setError("Email should not be empty !!!");
            focusView=email;
            cancel=true;
        }else if(!emailValue.matches(emailPattern)){
            email.setError("Enter valid email address !!!");
            focusView=email;
            cancel=true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            // business logic for forgot password
            if (Utils.ChechInternetAvalebleOrNot(ForgotPasswordActivity.this)) {

                Utils.showLoader(ForgotPasswordActivity.this);
                ServerRequest
                        .postRequest(
                                Connection.BASE_URL + "forgetPsw",
                                getForgotPassData(emailValue),
                                ForgotPasswordActivity.this,
                                ResponseListener.REQUEST_FORGET_PASSWORD);

            } else {
                //   Utils.showSnakeBar(layout_view, "internet not connected !!!", Color.RED);Toast.makeText(LoginActivity.this,"Internet not connected !!!",Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    public JSONObject getForgotPassData(String email) {
        JSONObject json = new JSONObject();
        try {
            json.put("email", email);
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

                if (rid == ResponseListener.REQUEST_FORGET_PASSWORD) {

                    if (response.isError()) {
                        Toast.makeText(ForgotPasswordActivity.this, response.getErrorMsg(),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.getData() != null) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.getData());
                            String status=jsonObject.getString("status");

                                Toast.makeText(ForgotPasswordActivity.this,jsonObject.getString("msg"),Toast.LENGTH_LONG).show();
                            Log.d("json_response", response.getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }
}

package com.example.administratorweiez_1.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    final static private String URL ="http://dign5455.cafe24.com/UserSignUp.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userName, String userID, String userPhone, String userPassword, Response.Listener<String> listener){

        super(Method.POST, URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("userName",userName);
        parameters.put("userID",userID);
        parameters.put("userPhone",userPhone);
        parameters.put("userPassword",userPassword);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}

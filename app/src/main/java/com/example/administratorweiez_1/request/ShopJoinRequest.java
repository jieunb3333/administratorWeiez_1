package com.example.administratorweiez_1.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ShopJoinRequest extends StringRequest {

    final static private String URL ="http://dign5455.cafe24.com/ShopJoin.php";
    private Map<String, String> parameters;

    public ShopJoinRequest(String shopName, String shopAddr, String shopPhone, String shopSelect, Response.Listener<String> listener){

        super(Method.POST, URL,listener,null);
        parameters = new HashMap<>();
        parameters.put("shopName",shopName);
        parameters.put("shopAddr",shopAddr);
        parameters.put("shopPhone",shopPhone);
        parameters.put("shopSelect",shopSelect);

    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
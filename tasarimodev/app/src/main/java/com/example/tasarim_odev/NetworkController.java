package com.example.tasarim_odev;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import  com.android.volley.toolbox.Volley;
public class NetworkController {
    private static Context mCtx;
    private static NetworkController mInstance;
    private RequestQueue mRequestQueue;

    private NetworkController(Context context){
        mCtx=context;
        this.mRequestQueue=getRequestQueue();
    }
    public static synchronized NetworkController getInstance(MainActivity3 context){
        NetworkController networkController;
        synchronized (NetworkController.class){
            if(mInstance==null){
                mInstance=new NetworkController(context);
            }
            networkController=mInstance;
        }
        return networkController;
    }
    RequestQueue getRequestQueue(){
        if(this.mRequestQueue==null){
            mRequestQueue=Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T>req){
        getRequestQueue().add(req);
    }
}

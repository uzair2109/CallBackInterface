package com.example.com.callbackinterface;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;

public class Authenticate extends AsyncTask<String, Integer, String>{

	public static String URL ="http://www.tatapower-ddl.com:1000/tatammg/MMGService.svc/";
	Context context;
	ProgressDialog pd = null;
	String response=null;
	String user_name,pwd;
	String device_imei="";
	public MyInterface myInf;
//	TrackApplication trackApp;
	
	public Authenticate(MyInterface ctx,Context mContext)
	{
//		this.context=ctx;
		myInf=ctx;
        this.context=mContext;

//		trackApp=(TrackApplication)ctx.getApplicationContext();
	}
	
	
	@Override
	protected void onPreExecute() {
//		pd = new ProgressDialog(context);
//		pd.setTitle("Please Wait");
//		pd.setMessage("Connecting to Server");
//		pd.setCancelable(false);
//		pd.setIndeterminate(true);
//		pd.show();
	}
	
    @Override
    protected String doInBackground(String... params) {
    	String res = null;
        try {
        	user_name = params[0];
//			user_name=Cryptography.Encrypt("pushpendra.chaudhary");
        	pwd=params[1];
          res = sendLoginRequest(params[0],params[1]);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
		return res;
    }
    
//    @Override
//    protected void onPostExecute(String response) {
////    	String response=null;
//    	if (pd!=null) {
//            pd.dismiss();
//        }
//			myInf.testFunctionOne(response);
//
//
//    }
    public String sendLoginRequest(String user,String pwd) throws ClientProtocolException, IOException, JSONException
    {
    	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//		String h=id;
//		System.out.println("REG_IDDDD :"+h);
    	device_imei = tm.getDeviceId();
		String method_name="login";
        
        try {
        JSONStringer data = new JSONStringer()
        .object()
        .key("user")
            .object()
                .key("USER_NAME").value(user)
                .key("PASSWORD").value(pwd)
                .key("DEVICE_ID").value("33")
                .key("IMEI_NO").value(device_imei)
               
            .endObject()
        .endObject();
        
        response=RestfulClient.postDatafromServer(URL+method_name, data.toString());
        Log.e("response", response);
        } catch(Exception e) {
            e.printStackTrace();
        }
		return response;
    }
}

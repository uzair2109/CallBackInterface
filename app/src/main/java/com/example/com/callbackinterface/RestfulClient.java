package com.example.com.callbackinterface;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestfulClient {

	static HttpResponse response = null;
	static int status;
	static String response_str;
//	private static String TARGET_DB_PATH = Environment.getExternalStorageDirectory()+"/TPDDL/";
//	static JsonReader json_reader;
	
	public static String getDatafromServer(String SERVER_URL, String data, @PicSelector.UserMode String type) {
		
		try {
			response_str=null;
			status=0;
			
			response=getHttpResponse(SERVER_URL, data);
			if(response!=null)
	           status=response.getStatusLine().getStatusCode();

//			HttpEntity etest = response.getEntity();
//            response_str = EntityUtils.toString(etest);
	          if(status== 200) //success
	          {
	              HttpEntity e = response.getEntity();
	              response_str = EntityUtils.toString(e);
//                  writeToFile(response_str);
//	            writeToFile(response_str);
					
	          }
//	          else{
//	        	  HttpEntity e = response.getEntity();
//	            response_str = EntityUtils.toString(e);
//	            return null;
//	          }
		}  catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response_str;
	}


	public static String postDatafromServer(String SERVER_URL,String data) {
        StringBuilder sb = new StringBuilder();
		try {

            URL url = new URL(SERVER_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
            connection.setReadTimeout(10000);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("charset", "utf-8");
			connection.setUseCaches (false);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
			wr.writeBytes(data);
			wr.flush();
			wr.close();
//

//			int HttpResult = connection.getResponseCode();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				BufferedReader br = new BufferedReader(
						new InputStreamReader(connection.getInputStream(), "utf-8"));

//				JsonReader json_reader = new JsonReader(new InputStreamReader(connection.getInputStream()));

				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
            } else {
				System.out.println(connection.getResponseMessage());
			}

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}



//	public static JsonReader getMasterDatafromServer(String SERVER_URL,String data)
//	{
//		try {
//			response_str=null;
//			status=0;
//			json_reader=null;
//
//			response=getHttpResponse(SERVER_URL, data);
//			if(response!=null)
//	           status=response.getStatusLine().getStatusCode();
//
////           HttpEntity e = response.getEntity();
////	           response_str = EntityUtils.toString(e);
////	           Log.e("response_str",response_str);
////	           writeToFile(response_str);
//
//	          if(status== 200) //success
//	          {
////	        	  generateNoteOnSD("Request for "+SERVER_URL+" received at "+getDate()+" , ");
//	        	  InputStream is = response.getEntity().getContent();
//	        	  json_reader = new JsonReader(new InputStreamReader(is));
//
////	        	  storeToFile(is, TARGET_DB_PATH+"json_resp.txt");
////	        	  zdm_srvc_mst = readJsonStream2(is);
//
//	          }
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return json_reader;
//	}



	public static HttpResponse getHttpResponse(String SERVER_URL,String data)
	{
		response=null;
		try {
			
			///Proxy Code by Uniken-relid
//			System.setProperty("http.proxyHost", "127.0.0.1");
//			System.setProperty("http.proxyPort", "11433");
			    
			 HttpClient client = new DefaultHttpClient();
			 HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
			 HttpPost post = new HttpPost(SERVER_URL);
	            
	            StringEntity se = new StringEntity(data);  
	            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//	            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "multipart/form-data"));
	            post.setEntity(se);
	            response = client.execute(post);
		 }
	 catch (ClientProtocolException e) {
		e.printStackTrace();
	} catch (IllegalStateException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		return response;
	}



//	public static void writeToFile(String data) {	// write on SD card file data in the text box
//		try {
//
//			String path=Config.TPDDL_PATH+"ShutDown.txt";
//			File myFile = new File(path);
//			myFile.createNewFile();
//			FileOutputStream fOut = new FileOutputStream(myFile);
//			OutputStreamWriter myOutWriter =
//									new OutputStreamWriter(fOut);
//			myOutWriter.append(data);
//			myOutWriter.close();
//			fOut.close();
////			Toast.makeText(getBaseContext(),
////					"Done writing SD 'mysdfile.txt'",
////					Toast.LENGTH_SHORT).show();
//		} catch (Exception e) {
//			System.out.println(e);
////			Toast.makeText(getBaseContext(), e.getMessage(),
////					Toast.LENGTH_SHORT).show();
//		}}
	
}

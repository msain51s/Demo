package com.goldshop.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.goldshop.utility.Preference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 1/28/2016.
 */
public class ServerRequest {
   static Preference preference;


    public static void postRequest(final String url, final JSONObject json,
                                   final ResponseListener r, final int rid) {
        System.out.println("");
        new Thread(new Runnable() {

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                try {
                    Response response = null;
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost post = new HttpPost(url);
                    // / JSONArray aa=json;
                    StringEntity stringEntity = new StringEntity(
                            json.toString());
                    Log.d("json_input",json.toString());
                    stringEntity.setContentType(new BasicHeader(
                            HTTP.CONTENT_TYPE, "application/json"));
                    post.setHeader("Accept", "application/json");
                    post.setHeader("Content-type", "application/json");
                    post.setEntity(stringEntity);
                    HttpResponse res = httpClient.execute(post);
        //            new Preference((LoginScreen)r).setTOKEN_ID(res.getHeaders("auth-token")[0].getValue());

                    StatusLine sl = res.getStatusLine();
                    if (sl.getStatusCode() == 200) {
                        HttpEntity entity = res.getEntity();
                        InputStream in = entity.getContent();
                        String resp = getString(in);
                        in.close();
					/*	if (isError(resp)) {
							response = new Response(getErrorMsg(resp), true);
						} else{*/
                        response = new Response(resp);
                        r.onResponse(response, rid);
                        return;
                        //}
                    } else if (sl.getStatusCode() == 400) {
                        r.onResponse(new Response(getErrorResponse(rid), true),
                                rid);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                r.onResponse(new Response(getErrorResponse(-1), true), rid);
            }
        }).start();

    }

    public static String getErrorResponse(int rid) {
        if (rid == ResponseListener.REQUEST_TEXTPAD) {
            return " invalid user name or password";

        } else if (rid == -1) {
            return " Service is down, please try again later";
        }
        return "";
    }

    private static String getString(InputStream in) {
        int c = -1;
        StringBuffer r = new StringBuffer();

        try {
            while ((c = in.read()) != -1)
                r.append((char) c);

            return r.toString();
        } catch (Exception e) {
        }

        return null;
    }

    private static boolean isError(String resp) {
        try {

            JSONArray jaa = new JSONArray(resp);
            JSONObject obj = jaa.getJSONObject(0);
            return !obj.getString("UserState").equalsIgnoreCase("3");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getErrorMsg(String resp) {
        String msg = "Service is down, please check again later";
        try {
            JSONObject obj = new JSONObject(resp);
            msg = obj.getString("LogInMessage");
            if (msg == null || msg.equals("")) {
                msg = "You did not login";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return msg;
    }

    public static void putRequest12(final String url, final JSONObject json,
                                     final ResponseListener r, final int rid, final Activity activity) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Response response = null;
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPut post = new HttpPut(url);
                    // / JSONArray aa=json;
                    StringEntity stringEntity = new StringEntity(json
                            .toString());
                    Log.d("json_input1",json.toString());
                    Log.d("url",url);
              //      stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
             //       post.setHeader("Accept", "application/json");
                    post.setHeader("Content-type","application/json");
                  /*  post.setHeader("auth-token",new Preference(activity).getTOKEN_ID());
                    Log.d("token",new Preference(activity).getTOKEN_ID());
                    post.setEntity(stringEntity);*/

                    HttpResponse res = httpClient.execute(post);
                    StatusLine sl = res.getStatusLine();
                    if (sl.getStatusCode() == 200) {
                        HttpEntity entity = res.getEntity();
                        InputStream in = entity.getContent();
                        String resp = getString2(in);
                        in.close();
                        response = new Response(resp);
                        r.onResponse(response, rid);
                        return;

                    } else if (sl.getStatusCode() == 400) {
                        r.onResponse(new Response(getErrorResponse2(rid), true), rid);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                r.onResponse(new Response(getErrorResponse(-1), true), rid);
            }
        }).start();

    }

    public static void postRequest12(final String url, final JSONObject json,
                                     final ResponseListener r, final int rid, final Activity activity) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Response response = null;
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost post = new HttpPost(url);
                    // / JSONArray aa=json;
                    StringEntity stringEntity = new StringEntity(json
                            .toString());
                    Log.d("json_input1",json.toString());
                    Log.d("url",url);
           //         stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                    //       post.setHeader("Accept", "application/json");
                    post.setHeader("Content-type","application/json");
                  /*  post.setHeader("auth-token",new Preference(activity).getTOKEN_ID());
                    Log.d("token",new Preference(activity).getTOKEN_ID());*/
                    post.setEntity(stringEntity);

                    HttpResponse res = httpClient.execute(post);
                    StatusLine sl = res.getStatusLine();
                    if (sl.getStatusCode() == 200) {
                        HttpEntity entity = res.getEntity();
                        InputStream in = entity.getContent();
                        String resp = getString2(in);
                        in.close();
                        response = new Response(resp);
                        r.onResponse(response, rid);
                        return;

                    } else if (sl.getStatusCode() == 400) {
                        r.onResponse(new Response(getErrorResponse2(rid), true), rid);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                r.onResponse(new Response(getErrorResponse(-1), true), rid);
            }
        }).start();

    }
    public static String getErrorResponse2(int rid) {
       /* if (rid == ResponseListener.REQUEST_SAVETABLE) {
            return " Data Not Save";

        } else*/ if (rid == -1) {
            return " Service is down, please try again later";
        }
        return "";
    }

    private static String getString2(InputStream in) {
	/*	int c = -1;
		StringBuffer r = new StringBuffer();

		try {
			while ((c = in.read()) != -1)
				r.append((char) c);

			return r.toString();
		} catch (Exception e) {
		}*/
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        try
        {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            return	total.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String getErrorMsg2(String resp) {
        String msg = "Service is down, please check again later";
        try {
            JSONObject obj = new JSONObject(resp);
            msg = obj.getString("Msg");
            if (msg == null || msg.equals("")) {
                msg = "Data Not save";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return msg;
    }

    public static void getRequest(final String url, final String value,
                                  final ResponseListener r, final int rid) {
        System.out.println("");
        new Thread(new Runnable() {

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                try {
                    Response response = null;
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpGet httpget = new HttpGet(url + value);

                    httpget.setHeader("Accept",
                            "application/json;charset=utf-8");
                    httpget.setHeader("Content-type",
                            "text/html;application/x-www-form-urlencoded;charset=utf-8");


                    HttpResponse res = httpClient.execute(httpget);
                    StatusLine sl = res.getStatusLine();
                    if (sl.getStatusCode() == 200) {
                        HttpEntity entity = res.getEntity();
                        InputStream in = entity.getContent();
                        String resp = getString2(in);
                        in.close();

                        response = new Response(resp);
                        r.onResponse(response, rid);

                        return;

                    } else if (sl.getStatusCode() == 400) {
                        r.onResponse(
                                new Response(getErrorResponse2(rid), true), rid);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                r.onResponse(new Response(getErrorResponse(-1), true), rid);
            }
        }).start();

    }

    public static void getRequest2(final String url, final String value,
                                   final ResponseListener r, final int rid,Context ctx) {
        preference=new Preference(ctx);
        new Thread(new Runnable() {

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                try {
                    Response response = null;
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpGet httpget = new HttpGet(url + value);

                    httpget.setHeader("Accept",
                            "application/json;charset=utf-8");
                    httpget.setHeader("Content-type",
                            "text/html;application/x-www-form-urlencoded;charset=utf-8");
          //          httpget.setHeader("auth-token","26609299197149706706884716090#06-37-2016"/*preference.getTOKEN_ID()*/);

                    HttpResponse res = httpClient.execute(httpget);
                    StatusLine sl = res.getStatusLine();
                    if (sl.getStatusCode() == 200) {
                        HttpEntity entity = res.getEntity();
                        InputStream in = entity.getContent();
                        String resp = getString2(in);
                        in.close();

                        response = new Response(resp);
                        r.onResponse(response, rid);

                        return;

                    } else if (sl.getStatusCode() == 400) {
                        r.onResponse(
                                new Response(getErrorResponse2(rid), true), rid);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                r.onResponse(new Response(getErrorResponse(-1), true), rid);
            }
        }).start();

    }

}


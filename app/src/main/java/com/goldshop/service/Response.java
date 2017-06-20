package com.goldshop.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Administrator on 1/28/2016.
 */
public class Response {
    /**
     *
     */
    public static final String KEY_MESSAGE = "message";
    protected String msg;
    String data;
    protected String successmsg;
    /**
     *
     */

    boolean isError = false;

    public Response(String data) throws Exception {
        this.data = data;
        try {

            Object json = new JSONTokener(data).nextValue();
            if (json instanceof JSONObject)
            {
                JSONObject doc = new JSONObject(data);
                successmsg = doc.getString("msg");
            }
            else if (json instanceof JSONArray)
            {
                JSONArray docarray = new JSONArray(data);
                JSONObject doc =docarray.getJSONObject(0);
                successmsg = doc.getString("msg");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println();
        }

    }

    /**
     * Creates a new Response object.
     *
     * @param data
     *            json data
     */
    public Response(String msg, boolean isError) {

        this.msg = msg;
        this.isError = isError;
    }

    public String getSuccessMessage() {
        return successmsg;
    }

    /**
     *
     *
     * @return true if error is returned from server
     */
    public boolean isError() {
        return isError;
    }

    /**
     *
     *
     * @return error message
     */
    public String getErrorMsg() {

        return msg;
    }

    /**
     *
     *
     * @return
     */
    public String getData() {
        return data;
    }
}

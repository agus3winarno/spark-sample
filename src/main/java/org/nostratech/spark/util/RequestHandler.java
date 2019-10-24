package org.nostratech.spark.util;

import com.google.gson.Gson;
import lombok.Data;

/**
 * agus w on 12/14/15.
 */
public abstract class RequestHandler {

    Gson gson = new Gson();

    @Data
    class Result {
        private String message;
        private Object result;
    }

    public String getResult(final String message) {
        Result result = new Result();
        try {
            Object obj = processRequest();
            result.setMessage(message);
            result.setResult(obj==null?"":obj);
        } catch (Exception e) {
            result.setMessage(message);
            result.setResult(e.getMessage());
        }
        return gson.toJson(result);
    }

    public abstract Object processRequest();
}

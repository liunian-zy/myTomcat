package io.liunian;

import java.io.IOException;
import java.io.InputStream;

public class MyRequest {
    private String requestMethod;
    private String requestUrl;

    public MyRequest(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        String str = null;

        if ((len = inputStream.read(buffer)) > 0) {
            str = new String(buffer);
        }
        assert str != null;
        String data = str.split("\n")[0];
        String[] params = data.split(" ");
        this.requestMethod = params[0];
        this.requestUrl = params[1];
    }


    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}

package learn;


import java.net.MalformedURLException;
import java.net.URL;

final class Mac {
    private String accessKey;
    private String secretKey;

    public Mac(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String sign(String data) throws Exception {
        byte[] sum = HMac.HmacSHA1Encrypt(data, this.secretKey);
        String sign = UrlSafeBase64.encodeToString(sum);
        return this.accessKey + ":" + sign;
    }

    public String signRequest(URL url, String method, byte[] body, String contentType) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s %s", method, url.getPath()));
        if (url.getQuery() != null) {
            sb.append(String.format("?%s", url.getQuery()));
        }

        sb.append(String.format("\nHost: %s", url.getHost()));
        if (url.getPort() > 0) {
            sb.append(String.format(":%d", url.getPort()));
        }

        if (contentType != null) {
            sb.append(String.format("\nContent-Type: %s", contentType));
        }

        // body
        sb.append("\n\n");
        if (incBody(body, contentType)) {
            sb.append(new String(body));
        }

        byte[] sum = HMac.HmacSHA1Encrypt(sb.toString(), this.secretKey);
        String sign = UrlSafeBase64.encodeToString(sum);
        return this.accessKey + ":" + sign;

    }

    private boolean incBody(byte[] body, String contentType) {
        int maxContentLength = 1024 * 1024;
        boolean typeOK = contentType != null && !contentType.equals("application/octet-stream");
        boolean lengthOK = body != null && body.length > 0 && body.length < maxContentLength;
        return typeOK && lengthOK;
    }

    //连麦 RoomToken
    public String signRoomToken(String roomAccess) throws Exception {
        String encodedRoomAcc = UrlSafeBase64.encodeToString(roomAccess);
        byte[] sign = HMac.HmacSHA1Encrypt(encodedRoomAcc, this.secretKey);
        String encodedSign = UrlSafeBase64.encodeToString(sign);
        return this.accessKey + ":" + encodedSign+":"+encodedRoomAcc;
    }

    public static void main(String[] args){
        URL url = null;
        Mac mac = new Mac("51hH1Zka-CI2C5N-e_O51sN3h2W8rEqkwjnikuOp",
                "qXhtDrnL3wnWC5hol8UtResRSTlsNiAOPbO2neTc");
        try {
            String contentType = "application/json";
//            url = new URL("http://pili.qiniuapi.com/v2/hubs/spotlitelive/streams/MTAwMw==/saveas");
            url = new URL("http://pili.qiniuapi.com/v2/hubs/spotlitelive/streams/MTU5MTE=/saveas");

//            String json = "{\"fname\":\"1003\",\"format\":\"mp4\"}";
            String json = "{\"fname\":\"15911\"}";
            byte[] body = json.getBytes("UTF-8");
            String macToken = mac.signRequest(url, "POST", body, contentType);

            System.out.print("token== " + macToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

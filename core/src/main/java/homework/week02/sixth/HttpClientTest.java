package homework.week02.sixth;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;

public class HttpClientTest {

    public static void main(String[] args) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            String url = "http://localhost:8801";
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(new HttpGet(url));
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            closeResource(httpclient);
            closeResource(response);
        }
    }

    private static void closeResource(Closeable resource) {
        if(resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

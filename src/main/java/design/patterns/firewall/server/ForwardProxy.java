package design.patterns.firewall.server;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.List;

public class ForwardProxy {

    private static final String HTTPS_PROTOCOL_PREFIX = "https://";

    final List<String> forbiddenHosts = Arrays.asList("instagram" +
            ".com", "facebook.com");

    public void getHTMLData(final String hostname) {
        if (forbiddenHosts.contains(hostname)) {
            throw new IllegalStateException(String.format("It's " +
                    "forbidden to visit site '%s'!", hostname));
        }

        final String hostnameWithProtocol =
                HTTPS_PROTOCOL_PREFIX + hostname;

        try (CloseableHttpClient httpClient =
                     HttpClients.createDefault()) {

            HttpGet request = new HttpGet(hostnameWithProtocol);

            try (CloseableHttpResponse response =
                         httpClient.execute(request)) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

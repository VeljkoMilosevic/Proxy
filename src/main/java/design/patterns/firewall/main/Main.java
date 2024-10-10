package design.patterns.firewall.main;

import design.patterns.firewall.server.ForwardProxy;

public class Main {
    public static void main(String[] args) {
        ForwardProxy forwardProxy = new ForwardProxy();
        forwardProxy.getHTMLData("google.com");
    }
}
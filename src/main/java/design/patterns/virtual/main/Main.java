package design.patterns.virtual.main;

import design.patterns.virtual.file.ProxyFile;

public class Main {

    public static void main(String[] args) {
        ProxyFile proxyFile = new ProxyFile();
        proxyFile.getFile();
    }
}

package design.patterns.virtual.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

public class RealFile implements File {

    private static final String FILE_PATH = "src/main/resources/file.txt";
    private double fileLength;
    private volatile String content = "";

    @Override
    public String getFile() {
        getFileTotalSize();
        InputStream inputStream = getFileInputStream();
        try (InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                content += currentLine;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return content;
    }

    private InputStream getFileInputStream() {
        try {
            return new FileInputStream(FILE_PATH);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void getFileTotalSize() {
        fileLength = new java.io.File(FILE_PATH).length();
    }

    public void calculateSize() {
        System.out.println("Downloaded size:" + (content.getBytes(StandardCharsets.UTF_8).length / fileLength) * 100);
    }
}

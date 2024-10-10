package design.patterns.virtual.file;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProxyFile implements File {

    RealFile realFile;
    private String fileContent;

    @Override
    public String getFile() {
        if (fileContent == null) {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            CountDownLatch latch = new CountDownLatch(1);
            realFile = new RealFile();

            Future<String> getRealObjectContent = getRealFileContent(executorService, latch);
            Future<Void> processInfo = getDownloadProcessInfo(executorService, latch);

            try {
                fileContent = getRealObjectContent.get();
                processInfo.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new FileHandlingException(e);
            } catch (ExecutionException e) {
                throw new FileHandlingException(e);
            } finally {
                executorService.shutdown();
            }
        }
        return fileContent;
    }

    private Future<String> getRealFileContent(ExecutorService executorService, CountDownLatch latch) {
        return (Future<String>) executorService.submit(() -> {
            try {
                realFile.getFile();
            } finally {
                latch.countDown();
            }
        });
    }

    private Future<Void> getDownloadProcessInfo(ExecutorService executorService, CountDownLatch latch) {
        return (Future<Void>) executorService.submit(() -> {
            while (latch.getCount() == 1) {
                realFile.calculateSize();
            }
        });
    }
}

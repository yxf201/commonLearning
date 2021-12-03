package concurrent.taskExecute.renderPage;

import java.util.concurrent.*;

public class RenderPageWithAd {
    private static final long TIME_BUDGET = 1000;
    private static final Ad DEFAULT_AD = null;

    private final ExecutorService exec;

    RenderPageWithAd(ExecutorService exec) {
        this.exec = exec;
    }

    Page renderPageWithAd() throws InterruptedException {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> f = exec.submit(this::getAd);

        Page page = renderPageBody();
        Ad ad = null;
        try {
            long timeLeft = endNanos - System.nanoTime();
            ad = f.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);
        }
        page.setAd(ad);
        return page;
    }

    private Page renderPageBody() {
        return null;
    }

    private Ad getAd() {
        return null;
    }

    private static class Page {
        Ad ad;
        public void setAd(Ad ad) {
            this.ad = ad;
        }
    }

    private static class Ad {
    }

}

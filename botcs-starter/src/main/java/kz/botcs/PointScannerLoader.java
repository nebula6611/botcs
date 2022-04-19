package kz.botcs;

import kz.botcs.point.PointController;
import kz.botcs.point.PointScanner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PointScannerLoader {

    private final PointScanner pointScanner;

    public PointScannerLoader(PointScanner pointScanner) {
        this.pointScanner = pointScanner;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(ApplicationReadyEvent event) {
        Collection<Object> controllers = event.getApplicationContext()
                .getBeansWithAnnotation(PointController.class).values();
        pointScanner.scan(controllers);
    }
}

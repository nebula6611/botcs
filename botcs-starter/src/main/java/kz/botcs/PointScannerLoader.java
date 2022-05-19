package kz.botcs;

import kz.botcs.point.PointController;
import kz.botcs.point.PointScan;
import kz.botcs.point.PointScanner;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.EventListener;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PointScannerLoader {

    private final PointScanner pointScanner;

    public PointScannerLoader(PointScanner pointScanner) {
        this.pointScanner = pointScanner;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(ApplicationReadyEvent event) throws Exception {
        ApplicationContext context = event.getApplicationContext();
        List<String> classNames = context.getBeansWithAnnotation(PointScan.class)
                .values().stream()
                .map(Object::getClass).map(Class::getPackageName)
                .distinct().collect(Collectors.toList());
        List<Class<?>> result = new ArrayList<>();
        for (String className : classNames) {
            ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(PointController.class));
            for (BeanDefinition beanDefinition : scanner.findCandidateComponents(className)) {
                String beanClassName = beanDefinition.getBeanClassName();
                if (beanClassName == null) continue;
                Class<?> aClass = ClassUtils.forName(beanClassName, context.getClassLoader());
                result.add(aClass);
            }
        }
        pointScanner.scan(result);
    }
}

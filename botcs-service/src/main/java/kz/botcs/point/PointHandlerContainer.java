package kz.botcs.point;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class PointHandlerContainer {
    private final List<PointHandler<? extends Annotation>> pointHandlers;

    public PointHandlerContainer(List<PointHandler<? extends Annotation>> pointHandlers) {
        List<PointHandler<? extends Annotation>> list = new ArrayList<>(pointHandlers);
        list.sort(Comparator.comparing(PointHandler::getOrder));
        this.pointHandlers = Collections.unmodifiableList(list);
    }

    public List<PointHandler<? extends Annotation>> getPointHandlers() {
        return pointHandlers;
    }
}

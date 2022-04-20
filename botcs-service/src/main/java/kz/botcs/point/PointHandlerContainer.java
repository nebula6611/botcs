package kz.botcs.point;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class PointHandlerContainer {
    private final List<PointHandler> pointHandlers;

    public PointHandlerContainer(List<PointHandler> pointHandlers) {
        List<PointHandler> list = new ArrayList<>(pointHandlers);
        list.sort(Comparator.comparing(PointHandler::getOrder));
        this.pointHandlers = Collections.unmodifiableList(list);
    }

    public List<PointHandler> getPointHandlers() {
        return pointHandlers;
    }
}

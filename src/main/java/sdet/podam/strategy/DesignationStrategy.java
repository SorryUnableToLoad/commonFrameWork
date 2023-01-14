package sdet.podam.strategy;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Stream;

public class DesignationStrategy implements AttributeStrategy<String> {


    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return Stream.of("Associate Engineer", "Test Engineer", "SDET-1", "SDET-2", "SDET-3")
                .findAny()
                .orElse("QA Lead");
    }
}

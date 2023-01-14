package sdet.podam.strategy;

import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Stream;

public class NameStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return Stream.of("Nuthan", "Likitha", "Suraj").findAny()
                .orElse("Somanath");
    }
}

package my.site.ithoughtilearned.service;

import my.site.ithoughtilearned.model.Visitors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class VisitorCollector implements Collector<Visitors, List<String>, List<String>> {
    @Override
    public Supplier<List<String>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<String>, Visitors> accumulator() {
        return (list, visitor) -> list.add(visitor.time);
    }

    @Override
    public BinaryOperator<List<String>> combiner() {
        return (list1, list2) -> {
            list1.addAll((list2));
            return list1;
        };
    }

    @Override
    public Function<List<String>, List<String>> finisher() {
        return Collections::unmodifiableList;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}

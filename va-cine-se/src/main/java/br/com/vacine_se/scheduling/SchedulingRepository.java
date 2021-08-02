package br.com.vacine_se.scheduling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.vacine_se.InMemoryRepository;

@Repository
public class SchedulingRepository implements InMemoryRepository<Scheduling> {

    private List<Scheduling> schedulings = new ArrayList<>();
    private int schedulingIdCount = 0;

    @Override
    public Optional<Scheduling> getById(int id) {
        return Optional.ofNullable(schedulings.get(id));
    }

    @Override
    public Collection<Scheduling> getAll() {
        return schedulings.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Scheduling save(Scheduling scheduling) {
    	schedulings.add(scheduling);
        scheduling.setId(this.schedulingIdCount);
        this.schedulingIdCount++;
        return scheduling;
    }

    @Override
    public Scheduling update(Scheduling scheduling) {
        return schedulings.set(scheduling.getId(), scheduling);
    }

    @Override
    public void delete(Scheduling scheduling) {
    	schedulings.set(scheduling.getId(), null);
    }
    
}

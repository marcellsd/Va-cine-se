package br.com.vacine_se.district;

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
public class DistrictRepository implements InMemoryRepository<District> {

    private List<District> districts = new ArrayList<>();

    @Override
    public Optional<District> getById(int id) {
        return Optional.ofNullable(districts.get(id));
    }

    @Override
    public Collection<District> getAll() {
        return districts.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public District save(District district) {
        districts.add(district);
        int index = districts.size() - 1;
        district.setId(index);
        return district;
    }

    @Override
    public District update(District district) {
        return districts.set(district.getId(), district);
    }

    @Override
    public void delete(District district) {
        districts.set(district.getId(), null);
    }
    
}

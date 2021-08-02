package br.com.vacine_se.admin;

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
public class AdminRepository implements InMemoryRepository<Admin> {

    private List<Admin> admins = new ArrayList<>();
    private int adminIdCount = 0;

    @Override
    public Optional<Admin> getById(int id) {
        return Optional.ofNullable(admins.get(id));
    }

    @Override
    public Collection<Admin> getAll() {
        return admins.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public Admin save(Admin admin) {
        admins.add(admin);
        admin.setId(this.adminIdCount);
        this.adminIdCount++;
        return admin;
    }

    @Override
    public Admin update(Admin admin) {
       return admins.set(admin.getId(), admin);
    }

    @Override
    public void delete(Admin admin) {
        admins.set(admin.getId(), null);
    }
    
}

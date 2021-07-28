package br.com.vacine_se.user;

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
public class UserRepository implements InMemoryRepository<User> {

    private List<User> users = new ArrayList<>();

    @Override
    public Optional<User> getById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Collection<User> getAll() {
        return users.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public User save(User user) {
        users.add(user);
        int index = users.size() - 1;
        user.setId(index);
        return user;
    }

    @Override
    public User update(User user) {
       return users.set(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.set(user.getId(), null);
    }

}

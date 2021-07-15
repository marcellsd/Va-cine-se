package br.com.vacine_se.scheduling;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemorySchedulingRepository extends CrudRepository<Scheduling, String>{}

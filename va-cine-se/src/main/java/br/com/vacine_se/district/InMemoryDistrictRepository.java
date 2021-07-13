package br.com.vacine_se.district;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryDistrictRepository extends CrudRepository<District, Long>{}


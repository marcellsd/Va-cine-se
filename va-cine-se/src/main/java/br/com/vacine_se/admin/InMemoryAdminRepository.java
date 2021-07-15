package br.com.vacine_se.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InMemoryAdminRepository extends CrudRepository<Admin, String>{}

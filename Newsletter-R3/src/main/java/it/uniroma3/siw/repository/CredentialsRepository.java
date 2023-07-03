package it.uniroma3.siw.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;


public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	Optional<Credentials> findByUsername(String username);

	List<Credentials> findByRole(String role);
	Credentials findByUser(User user);
}
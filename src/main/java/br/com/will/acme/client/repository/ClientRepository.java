package br.com.will.acme.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.will.acme.client.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query(value = "SELECT * FROM CLIENTS WHERE email LIKE '%gmail%'", nativeQuery = true)
	List<Client> getAllClientsWithGmail();
}

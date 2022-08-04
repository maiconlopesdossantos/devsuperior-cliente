package com.devsuperior.clientCRUD.repositories;

import com.devsuperior.clientCRUD.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}

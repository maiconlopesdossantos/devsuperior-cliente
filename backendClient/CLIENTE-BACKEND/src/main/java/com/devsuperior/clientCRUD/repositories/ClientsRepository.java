package com.devsuperior.clientCRUD.repositories;

import com.devsuperior.clientCRUD.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
}

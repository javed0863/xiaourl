package com.javedrpi.xiaourl.repository;

import com.javedrpi.xiaourl.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer> {
    Clients findByClientIdAndApiToken(String clientId, String apiToken);
}

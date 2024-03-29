package com.kapsch.demo.com.kapsch.repository;

import com.kapsch.demo.com.kapsch.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}

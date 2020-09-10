package com.hanimum.gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanimum.gateway.model.State;
import com.hanimum.gateway.model.StateName;

public interface StateRepository extends JpaRepository<State, Long> {
	Optional<State> findByName(StateName name);
	
}

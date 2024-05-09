package com.example.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.Optional;

@GraphQlRepository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByName(String name);
}

package com.example.daosism.Repositories;

import com.example.daosism.Models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    Location findLocationByAddress(String address);
}

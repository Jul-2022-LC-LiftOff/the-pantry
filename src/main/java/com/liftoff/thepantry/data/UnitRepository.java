package com.liftoff.thepantry.data;

import com.liftoff.thepantry.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
}

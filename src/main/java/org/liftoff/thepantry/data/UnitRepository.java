package org.liftoff.thepantry.data;

import org.liftoff.thepantry.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByName(String name);
}

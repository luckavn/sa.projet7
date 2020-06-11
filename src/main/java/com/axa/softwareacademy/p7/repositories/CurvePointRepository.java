package com.axa.softwareacademy.p7.repositories;

import com.axa.softwareacademy.p7.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {
}

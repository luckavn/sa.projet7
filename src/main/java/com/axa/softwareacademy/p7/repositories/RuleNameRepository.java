package com.axa.softwareacademy.p7.repositories;

import com.axa.softwareacademy.p7.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}

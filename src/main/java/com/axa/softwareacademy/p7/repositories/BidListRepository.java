package com.axa.softwareacademy.p7.repositories;

import com.axa.softwareacademy.p7.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {
    BidList findByBidListId(Integer id);
    Boolean getByBidListId(Integer id);
}

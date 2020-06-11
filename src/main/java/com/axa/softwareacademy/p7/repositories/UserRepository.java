package com.axa.softwareacademy.p7.repositories;

import com.axa.softwareacademy.p7.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * @param userName
     * @return
     */
    @Query(value = "select * from users where userName = :userName", nativeQuery = true)
    User findByUserName(@Param("userName") String userName);
}

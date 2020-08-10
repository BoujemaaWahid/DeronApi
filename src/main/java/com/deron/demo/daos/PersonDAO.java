package com.deron.demo.daos;

import com.deron.demo.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonDAO extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    @Query(value = "select * from Person where email=:email limit 1", nativeQuery = true)
    Person findPersonByEmail(@Param("email") String email);
}

package com.deron.demo.daos;
import com.deron.demo.entitys.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TraceDAO extends JpaRepository<Trace, Long>, JpaSpecificationExecutor<Trace> {
}

package com.cgfarm.repository;


import com.cgfarm.entity.ErrorReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorReportRepository extends JpaRepository<ErrorReport, Integer> {

}

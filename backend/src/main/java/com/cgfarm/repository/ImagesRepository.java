package com.cgfarm.repository;

import com.cgfarm.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface ImagesRepository extends JpaRepository<Images, Integer> {
}

package com.informatorio.tpJavaFinal.repository;



import com.informatorio.tpJavaFinal.entity.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNombre(String nombre);
}
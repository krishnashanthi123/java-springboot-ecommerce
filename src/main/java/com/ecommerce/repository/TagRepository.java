package com.ecommerce.repository;

import com.ecommerce.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tags, Integer> {

    Optional<Tags> findByTagName(String tagName);

	Optional<Tags> findById(Integer tagId);

}
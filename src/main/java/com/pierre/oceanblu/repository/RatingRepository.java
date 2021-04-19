package com.pierre.oceanblu.repository;

import com.pierre.oceanblu.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}

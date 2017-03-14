package br.com.zup.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.domain.entity.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

	Page<Coordinate> findAll(Pageable pageable);

}

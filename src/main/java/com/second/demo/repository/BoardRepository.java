package com.second.demo.repository;

import com.second.demo.entity.board2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<board2,Integer> {
}

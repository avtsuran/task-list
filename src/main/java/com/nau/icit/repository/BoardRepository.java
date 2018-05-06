package com.nau.icit.repository;

import com.nau.icit.model.Board;
import com.nau.icit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findBoardById(Long id);
    List<Board> findBoardsByUser(User user);
}

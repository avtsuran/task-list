package com.nau.icit.repository;

import com.nau.icit.model.Board;
import com.nau.icit.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    TaskList findTaskListById(Long id);
    List<TaskList> findTaskListsByBoardId(Long id);
    void deleteAllByBoard(Board board);
}

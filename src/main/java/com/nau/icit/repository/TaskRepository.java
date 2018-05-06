package com.nau.icit.repository;

import com.nau.icit.model.Task;
import com.nau.icit.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteAllByTaskList(TaskList taskList);
}

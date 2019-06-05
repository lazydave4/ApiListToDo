package com.dawid;

import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Tasks,Long> {
    Tasks findByDescription(String taskDescripiton);
}

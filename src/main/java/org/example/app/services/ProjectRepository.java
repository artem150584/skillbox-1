package org.example.app.services;

import org.example.web.dto.RemovedBook;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> reteiveAll();

    void store(T book);

    boolean removeItemById(RemovedBook removedBook);
}

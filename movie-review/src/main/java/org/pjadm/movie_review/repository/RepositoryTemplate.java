package org.pjadm.movie_review.repository;

import java.util.List;

public interface RepositoryTemplate<T> {

    public void save(T document);
    public T get(String id);
    public List<T> getAll();

}

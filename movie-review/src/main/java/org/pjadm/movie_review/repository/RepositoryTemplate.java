package org.pjadm.movie_review.repository;

import java.util.List;

public interface RepositoryTemplate<T> {

    public T save(T document);
    public List<T> getAll();
    public List<T> get(String id);
    public void delete(String id);

}

package org.pjadm.movie_review.repository;

import java.util.List;

public interface RepositoryTemplate<T> {

    public void save(T document);
    public List<T> get(String id);
    public List<T> getAll();
    public void delete(String id);

}

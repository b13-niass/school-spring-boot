package org.example.odc.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.Optional;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findByIdAndDeletedFalse(ID id);

    Collection<T> findByDeletedFalse();
}

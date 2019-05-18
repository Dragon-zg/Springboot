package com.jpa.service.base;

import com.jpa.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

/**
 * CurdService interface contains some common methods.
 *
 * @param <T>  domain type
 * @param <ID> id type
 * @author Dragon-zg
 */
public interface CurdService<T extends BaseEntity, ID> {
    /**
     * List All
     *
     * @return List
     */
    @NonNull
    List<T> listAll();

    /**
     * List all by {@link Sort}.
     *
     * @param sort sort
     * @return List
     */
    @NonNull
    List<T> listAll(@NonNull Sort sort);


    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec spec
     * @param sort sort
     * @return List
     */
    @NonNull
    List<T> listAll(@Nullable Specification<T> spec,  @NonNull Sort sort);

    /**
     * paging List by entity fields and {@link Pageable}
     *
     * @param searchParam searchParam
     * @param pageable    pageable
     * @return Page
     */
    @NonNull
    Page<T> pagingList(@NonNull Map<String, Object> searchParam, @NonNull Pageable pageable);
}

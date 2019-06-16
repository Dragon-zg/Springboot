package com.lnnk.jpa.service.base;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

/**
 * CurdService interface contains some common methods.
 *
 * @param <T>  entity type
 * @param <ID> id type
 * @author Lnnk
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
     * paging List by entity fields and {@link Pageable}
     *
     * @param searchParam searchParam
     * @param pageable    pageable
     * @return Page
     */
    @NonNull
    Page<T> pagingList(@NonNull Map<String, Object> searchParam, @NonNull Pageable pageable);

    /**
     * find entity by ID
     *
     * @param id 主键ID
     * @return T
     */
    T detail(@NonNull ID id);

    /**
     * delete entity by ID
     *
     * @param id 主键ID
     */
    void delete(@NonNull ID id);
}

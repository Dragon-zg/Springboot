package com.jpa.service.base;

import com.web.exception.CustomizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseService interface contains some common methods.
 *
 * @param <T>  domain type
 * @param <ID> id type
 * @author Dragon-zg
 */
public interface BaseService<T, ID> {
    /**
     * List All
     *
     * @return List
     */
    @NonNull
    List<T> listAll();

    /**
     * List all by sort
     *
     * @param sort sort
     * @return List
     */
    @NonNull
    List<T> listAll(@NonNull Sort sort);

    /**
     * List all by pageable
     *
     * @param searchParam searchParam
     * @param pageable    pageable
     * @return Page
     */
    @NonNull
    Page<T> pagingList(@NonNull Map<String, Object> searchParam, @NonNull Pageable pageable);

    /**
     * List all by entities
     *
     * @param ids ids
     * @return List
     */
    List<T> listAllByIds(@NonNull Collection<ID> ids);

    /**
     * Get by id
     *
     * @param id id
     * @return T
     * @throws CustomizedException If the specified id does not exist
     */
    T getById(@NonNull ID id);

    /**
     * Removes by id
     *
     * @param id id
     * @return T
     * @throws CustomizedException If the specified id does not exist
     */
    T removeById(@NonNull ID id);

    /**
     * Removes by id if present.
     *
     * @param id id
     * @return T
     */
    @Nullable
    T removeByIdOfNullable(@NonNull ID id);

    /**
     * Remove by domain
     *
     * @param domain domain
     */
    void remove(@NonNull T domain);

    /**
     * Remove by ids
     *
     * @param ids ids
     */
    void removeInBatch(@NonNull Collection<ID> ids);

    /**
     * Remove all by domains
     *
     * @param domains domains
     */
    void removeAll(@NonNull Collection<T> domains);
}

package com.jpa.service.base;

import com.jpa.entity.base.BaseEntity;
import com.jpa.repository.base.BaseRepository;
import com.jpa.search.DynamicSpecification;
import com.jpa.search.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * abstract service implementation.
 *
 * @param <T>  domain type
 * @param <ID> id type
 * @author Dragon-zg
 */
public abstract class AbstractCurdService<T extends BaseEntity, ID> implements CurdService<T, ID> {

    private final BaseRepository<T, ID> repository;

    protected AbstractCurdService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    /**
     * List All
     *
     * @return List
     */
    @Override
    public List<T> listAll() {
        return repository.findAll();
    }

    /**
     * List all by {@link Sort}.
     *
     * @param sort sort
     * @return List
     */
    @Override
    public List<T> listAll(Sort sort) {
        Assert.notNull(sort, "sort is must not null.");
        return repository.findAll(sort);
    }

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec spec
     * @param sort sort
     * @return List
     */
    @Override
    public List<T> listAll(Specification<T> spec, Sort sort) {
        Assert.notNull(spec, "specification is must not null.");
        Assert.notNull(sort, "sort is must not null.");
        return repository.findAll(spec, sort);
    }

    /**
     * paging List by entity fields and {@link Pageable}
     *
     * @param searchParam searchParam
     * @param pageable    pageable
     * @return Page
     */
    @Override
    public Page<T> pagingList(Map<String, Object> searchParam, Pageable pageable) {
        Assert.notNull(searchParam, "searchParam is must not null.");
        Assert.notNull(pageable, "pageable is must not null.");
        Collection<SearchFilter> filters = SearchFilter.parse(searchParam);
        Specification<T> specification = DynamicSpecification.bySearchFilter(filters);
        Page<T> page = repository.findAll(specification, pageable);
        return page;
    }
}

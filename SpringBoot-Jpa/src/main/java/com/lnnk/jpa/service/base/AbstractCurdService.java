package com.lnnk.jpa.service.base;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import com.lnnk.jpa.repository.base.BaseRepository;
import com.lnnk.jpa.search.DynamicSpecification;
import com.lnnk.jpa.search.SearchFilter;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * abstract service implementation.
 *
 * @param <T>  entity type
 * @param <ID> id type
 * @author Lnnk
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

    /**
     * find entity by id
     *
     * @param id
     * @return T
     */
    @Override
    public T detail(ID id) {
        Optional<T> optional = repository.findById(id);
        return optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
    }

    /**
     * delete entity by ID
     *
     * @param id 主键ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(final ID id) {
        Optional<T> optional = repository.findById(id);
        optional.ifPresent((entity) -> {
            entity.setDeleteFlag(true);
            repository.save(entity);
        });
    }
}

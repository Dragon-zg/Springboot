package com.lnnk.jpa.model.dto;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.l2cache.L2Cache;
import com.lnnk.jpa.model.enums.UseType;
import lombok.Data;

/**
 * L2CacheVO
 *
 * @author Lnnk
 * @date 2019/6/11 16:55
 **/
@Data
public class L2CacheDTO implements InputConverter<L2Cache> {

    private UseType useType;
}

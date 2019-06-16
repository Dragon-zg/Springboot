package com.lnnk.jpa.model.vo;

import com.lnnk.jpa.model.converter.OutputConverter;
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
public class L2CacheVO implements OutputConverter<L2CacheVO, L2Cache> {

    private String id;

    private UseType useType;
}

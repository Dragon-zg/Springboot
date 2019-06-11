package com.jpa.model.dto;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.l2cache.L2Cache;
import com.jpa.model.enums.UseType;
import lombok.Data;

/**
 * L2CacheVO
 *
 * @author wangqiang
 * @date 2019/6/11 16:55
 **/
@Data
public class L2CacheDTO implements InputConverter<L2Cache> {

    private UseType useType;
}

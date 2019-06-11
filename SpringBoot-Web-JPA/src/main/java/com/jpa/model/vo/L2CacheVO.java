package com.jpa.model.vo;

import com.jpa.model.converter.OutputConverter;
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
public class L2CacheVO implements OutputConverter<L2CacheVO, L2Cache> {

    private String id;

    private UseType useType;
}

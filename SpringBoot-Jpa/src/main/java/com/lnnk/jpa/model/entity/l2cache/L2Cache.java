package com.lnnk.jpa.model.entity.l2cache;

import com.lnnk.jpa.id.FormatTableGenerator;
import com.lnnk.jpa.model.entity.base.BaseEntity;
import com.lnnk.jpa.model.enums.UseType;
import com.lnnk.jpa.model.enums.converter.UseTypeConverter;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 演示二级缓存的实体
 * @author Lnnk
 * @date 2019/6/4 13:04
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jpa_l2_cache")
@Where(clause = "delete_flag = 0")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class L2Cache extends BaseEntity{

    /**
     * 自定义生成器主键ID
     */
    @Id
    @Column(name = "id", columnDefinition = "char(10) comment '自定义生成器主键ID'")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "L2CacheIdGenerator")
    @GenericGenerator(name = "L2CacheIdGenerator", strategy = "com.lnnk.jpa.id.FormatTableGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = FormatTableGenerator.FORMAT_PARAM, value = "C%1$09d"),
                    @org.hibernate.annotations.Parameter(name = FormatTableGenerator.TABLE_PARAM, value = "id_generator"),
                    @org.hibernate.annotations.Parameter(name = FormatTableGenerator.SEGMENT_COLUMN_PARAM, value = "table_name"),
                    @org.hibernate.annotations.Parameter(name = FormatTableGenerator.SEGMENT_VALUE_PARAM, value = "jpa_l2_cache"),
                    @org.hibernate.annotations.Parameter(name = FormatTableGenerator.VALUE_COLUMN_PARAM, value = "current_value"),
                    @org.hibernate.annotations.Parameter(name = FormatTableGenerator.INITIAL_PARAM, value = "100000"),
                    @org.hibernate.annotations.Parameter(name = FormatTableGenerator.INCREMENT_PARAM, value = "1")})
    private String id;

    /**
     * 使用状态(1.启用 2.禁用)
     */
    @Basic
    @Convert(converter = UseTypeConverter.class)
    @Column(name = "use_type", columnDefinition = "tinyint(1) unsigned not null default 1 comment '使用状态(1.启用 2.禁用)'")
    private UseType useType;
}

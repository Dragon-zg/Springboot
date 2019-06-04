package com.jpa.entity.L2Cache;

import com.jpa.entity.base.BaseEntity;
import com.jpa.id.FormatTableGenerator;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 演示二级缓存的实体
 * @author wangqiang
 * @date 2019/6/4 13:04
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("二级缓存实体")
@Entity
@Table(name = "jpa_l2_ache")
@Where(clause = "delete_flag = 0")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class L2Cache extends BaseEntity{

    @Id
    @Column(name = "id", columnDefinition = "char(10) 自定义")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "L2CacheIdGenerator")
    @GenericGenerator(name = "L2CacheIdGenerator", strategy = "com.jpa.id.FormatTableGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = FormatTableGenerator.FORMAT_PARAM, value = "C%1$09d"),
                    @org.hibernate.annotations.Parameter(name = com.jpa.id.TableGenerator.TABLE_PARAM, value = "id_generator"),
                    @org.hibernate.annotations.Parameter(name = com.jpa.id.TableGenerator.SEGMENT_COLUMN_PARAM, value = "table_name"),
                    @org.hibernate.annotations.Parameter(name = com.jpa.id.TableGenerator.SEGMENT_VALUE_PARAM, value = "jpa_l2_ache"),
                    @org.hibernate.annotations.Parameter(name = com.jpa.id.TableGenerator.VALUE_COLUMN_PARAM, value = "current_value"),
                    @org.hibernate.annotations.Parameter(name = com.jpa.id.TableGenerator.INITIAL_PARAM, value = "100000"),
                    @org.hibernate.annotations.Parameter(name = com.jpa.id.TableGenerator.INCREMENT_PARAM, value = "1")})
    private String id;
}

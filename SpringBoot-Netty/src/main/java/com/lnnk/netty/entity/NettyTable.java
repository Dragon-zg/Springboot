package com.lnnk.netty.entity;

import javax.persistence.*;

@Entity
@Table(name = "netty_table")
public class NettyTable {

    /**
     * 主键ID
     */

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 缓存内容
     */
    @Basic
    @Column(name = "content", columnDefinition = "varchar(255) default null comment '内容'")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

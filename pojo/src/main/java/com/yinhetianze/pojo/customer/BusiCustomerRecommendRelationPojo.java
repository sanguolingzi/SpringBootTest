package com.yinhetianze.pojo.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "busi_customer_recommend_relation")
public class BusiCustomerRecommendRelationPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 推荐人
     */
    @Column(name = "recom_customer_id")
    private Integer recomCustomerId;

    /**
     * 被推荐人
     */
    @Column(name = "recomed_customer_id")
    private Integer recomedCustomerId;

    /**
     * 0 正常 1 已删除
     */
    @Column(name = "del_flag")
    private Short delFlag;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取推荐人
     *
     * @return recom_customer_id - 推荐人
     */
    public Integer getRecomCustomerId() {
        return recomCustomerId;
    }

    /**
     * 设置推荐人
     *
     * @param recomCustomerId 推荐人
     */
    public void setRecomCustomerId(Integer recomCustomerId) {
        this.recomCustomerId = recomCustomerId;
    }

    /**
     * 获取被推荐人
     *
     * @return recomed_customer_id - 被推荐人
     */
    public Integer getRecomedCustomerId() {
        return recomedCustomerId;
    }

    /**
     * 设置被推荐人
     *
     * @param recomedCustomerId 被推荐人
     */
    public void setRecomedCustomerId(Integer recomedCustomerId) {
        this.recomedCustomerId = recomedCustomerId;
    }

    /**
     * 获取0 正常 1 已删除
     *
     * @return del_flag - 0 正常 1 已删除
     */
    public Short getDelFlag() {
        return delFlag;
    }

    /**
     * 设置0 正常 1 已删除
     *
     * @param delFlag 0 正常 1 已删除
     */
    public void setDelFlag(Short delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
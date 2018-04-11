package com.yinhetianze.pojo.customer;

import java.util.Date;
import javax.persistence.*;

@Table(name = "busi_customer_loginlog")
public class BusiCustomerLoginlogPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员/消费者id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 操作类型 0 登陆 1 退出
     */
    @Column(name = "operate_type")
    private Integer operateType;

    /**
     * 事件发生时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 1 pc 2 wap 3微信 4 app
     */
    @Column(name = "client_type")
    private Short clientType;

    /**
     * 0 正常 1 已删除
     */
    @Column(name = "del_flag")
    private Short delFlag;

    /**
     * session id
     */
    private String session;

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
     * 获取会员/消费者id
     *
     * @return customer_id - 会员/消费者id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置会员/消费者id
     *
     * @param customerId 会员/消费者id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取操作类型 0 登陆 1 退出
     *
     * @return operate_type - 操作类型 0 登陆 1 退出
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型 0 登陆 1 退出
     *
     * @param operateType 操作类型 0 登陆 1 退出
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取事件发生时间
     *
     * @return create_time - 事件发生时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置事件发生时间
     *
     * @param createTime 事件发生时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取1 pc 2 wap 3微信 4 app
     *
     * @return client_type - 1 pc 2 wap 3微信 4 app
     */
    public Short getClientType() {
        return clientType;
    }

    /**
     * 设置1 pc 2 wap 3微信 4 app
     *
     * @param clientType 1 pc 2 wap 3微信 4 app
     */
    public void setClientType(Short clientType) {
        this.clientType = clientType;
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
     * 获取session id
     *
     * @return session - session id
     */
    public String getSession() {
        return session;
    }

    /**
     * 设置session id
     *
     * @param session session id
     */
    public void setSession(String session) {
        this.session = session;
    }
}
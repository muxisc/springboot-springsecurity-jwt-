package com.maomiyibian.microservice.api.domain.role;

import java.io.Serializable;
import java.util.Date;


/**
 * TODO: 角色实体
 *
 * @author junyunxiao
 * @version 1.0
 * @date 2018/12/1 15:15
 */
public class Role implements Serializable {
    /**
     * 角色ID
     */
    private Long id;


    /**
     * 角色Code
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态 1:可用 0:不可用 
     */
    private String roleStates;

    /**
     * 角色备注
     */
    private String roleRemark;

    /**
     * 创建者id
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleStates() {
        return roleStates;
    }

    public void setRoleStates(String roleStates) {
        this.roleStates = roleStates == null ? null : roleStates.trim();
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark == null ? null : roleRemark.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleCode=").append(roleCode);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleStates=").append(roleStates);
        sb.append(", roleRemark=").append(roleRemark);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
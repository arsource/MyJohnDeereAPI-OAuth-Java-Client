
package com.deere.api.axiom.generated.v3;

import java.util.List;

public class AxiomUser extends Resource {

    private Integer version;
/*
    private Set<AxiomUserPreference> axiomUserPreferences = new HashSet<AxiomUserPreference>();
    private UnifiedUser unifiedUserBroker;
*/
    private String operatorId;
    private User unifiedUser;
    private String userName;
    private String firstName;
    private String lastName;
    private String fullName;
/*
    private DateTime mapVisitedDate;
    private DateTime operationCenterLastLogin;
    private UnifiedUserTypeEnum unifiedUserType;
*/
    private List<Long> contextOrgIds;
    private boolean syncWithSF;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public User getUnifiedUser() {
        return unifiedUser;
    }

    public void setUnifiedUser(User unifiedUser) {
        this.unifiedUser = unifiedUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Long> getContextOrgIds() {
        return contextOrgIds;
    }

    public void setContextOrgIds(List<Long> contextOrgIds) {
        this.contextOrgIds = contextOrgIds;
    }

    public boolean isSyncWithSF() {
        return syncWithSF;
    }

    public void setSyncWithSF(boolean syncWithSF) {
        this.syncWithSF = syncWithSF;
    }
}

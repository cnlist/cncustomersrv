package us.cnlist.services.customerserver.events;

import us.cnlist.services.customerserver.database.entity.SystemUser;

public class UserRegisteredEvent extends Event {

    private boolean failed;
    private String failReason;

    public UserRegisteredEvent(SystemUser user, boolean failed){
        super(user);
        this.failed=failed;
    }

    public boolean isFailed() {
        return failed;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}

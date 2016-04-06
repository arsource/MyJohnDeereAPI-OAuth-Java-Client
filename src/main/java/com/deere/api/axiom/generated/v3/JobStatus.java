
package com.deere.api.axiom.generated.v3;

public enum JobStatus {

    NOT_STARTED, PAUSED, IN_PROGRESS, COMPLETED;
    public static boolean isCompleted(JobStatus jobStatus) {
        return COMPLETED == jobStatus;
    }


}

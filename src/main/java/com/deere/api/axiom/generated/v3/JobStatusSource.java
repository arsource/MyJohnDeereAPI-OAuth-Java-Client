package com.deere.api.axiom.generated.v3;

public enum JobStatusSource {
    GROWER_OPS, MY_JOBS_MOBILE, STREAMING, MAC_DN2K;

    public static boolean isGrowerOps(JobStatusSource statusSource) {
        return GROWER_OPS == statusSource;
    }
}

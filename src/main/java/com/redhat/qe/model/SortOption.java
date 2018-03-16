package com.redhat.qe.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Data
@Builder
@ToString
public class SortOption {
    private String option;
    private Boolean ascending;

    public Boolean isAscending() {
        return ascending;
    }
}

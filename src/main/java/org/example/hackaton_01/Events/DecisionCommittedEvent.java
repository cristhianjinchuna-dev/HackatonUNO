package org.example.hackaton_01.Events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DecisionCommittedEvent extends ApplicationEvent {
    private final String branchType;

    public DecisionCommittedEvent(Object source, String branchType) {
        super(source);
        this.branchType = branchType;
    }
}

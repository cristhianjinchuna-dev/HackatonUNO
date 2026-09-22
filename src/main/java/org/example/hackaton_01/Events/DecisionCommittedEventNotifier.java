package org.example.hackaton_01.Events;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class DecisionCommittedEventNotifier {
    private final DecisionService decisionService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void sendEmailNotification(DecisionCommittedEvent decisionCommittedEvent) {
        // enviar correo
    }
}

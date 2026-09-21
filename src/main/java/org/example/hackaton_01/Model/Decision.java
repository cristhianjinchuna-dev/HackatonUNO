package org.example.hackaton_01.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Decision {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playthrough_id",  nullable = false)
    private Playthrough playthrough;

    @ManyToOne
    @JoinColumn(name = "node_id")
    private StoryNode node;

    private String rawImput;

    private String branchType;

    private String impactLevel;

    private String handlerUnit;

    private String outcomeCode;

    private String resolvedNodeCode;

    private String status;

    private Instant createdAt;

    private Instant updatedAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

}

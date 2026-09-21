package org.example.hackaton_01.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hackaton_01.account.domain.User;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Playthrough {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerTag;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String startNodeCode;

    @ManyToOne
    @JoinColumn(name = "currentNode_id")
    private StoryNode currentNode;

    private Integer licidity;

    private Integer controlLevel;

    private String status;

    private String endingCode;

    private Instant  createdAt;
    private Instant updatedAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

}

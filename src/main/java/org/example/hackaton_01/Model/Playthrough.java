package org.example.hackaton_01.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;
@Getter
@Setter
public class Playthrough {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String playerTag;
    private String startNodeCode;
    private Integer lucidity;
    private Integer controlLevel;
    private String status;
    private String endingCode	;
    private Instant createdAt;
    private Instant updatedAt;}

}

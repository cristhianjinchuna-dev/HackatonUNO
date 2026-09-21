package org.example.hackaton_01.Repository;

import org.example.hackaton_01.Model.Playthrough;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaythroughRepository extends JpaRepository<Playthrough, Long> {
}

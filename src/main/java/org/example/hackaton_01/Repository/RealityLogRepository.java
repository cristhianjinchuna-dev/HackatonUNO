package org.example.hackaton_01.Repository;

import org.example.hackaton_01.Model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealityLogRepository extends JpaRepository<Decision, Long> {
}

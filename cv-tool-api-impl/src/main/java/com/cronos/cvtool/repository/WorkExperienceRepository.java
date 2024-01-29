package com.cronos.cvtool.repository;
import com.cronos.cvtool.entity.candidate.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
}
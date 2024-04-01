package ru.gb.practice_spring.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.practice_spring.entity.Issue;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Issue o SET o.returned_at = :returned_at WHERE o.id = :id")
    void saveWithReturnedDate(Long id, LocalDateTime returned_at);
    @Query("SELECT o FROM Issue o WHERE o.idReader = :idReader")
    List<Issue> findIssuesByReaderId(Long idReader);
}

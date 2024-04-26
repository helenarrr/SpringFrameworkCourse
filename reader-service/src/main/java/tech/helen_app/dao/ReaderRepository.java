package tech.helen_app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.helen_app.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}

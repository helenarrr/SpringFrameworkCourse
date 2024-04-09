package ru.gb.practice_spring.security.dao;


import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.practice_spring.security.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Хранилище для работы с сущностью "user"
 */
@RepositoryRestResource(path="users")
public interface UserRepository extends JpaRepository<User, UUID> {

}

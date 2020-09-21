package gasi.ewf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gasi.ewf.entity.User;
import gasi.ewf.entity.UserAttempt;

@Repository
public interface UserAttempRepository extends JpaRepository<UserAttempt, Long> {
	Optional<UserAttempt> findByUser(User user);
}

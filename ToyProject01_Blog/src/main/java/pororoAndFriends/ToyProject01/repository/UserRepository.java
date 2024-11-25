package pororoAndFriends.ToyProject01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pororoAndFriends.ToyProject01.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}

package se.iths.gameserverlabone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);

    boolean existsByName(String name);

}

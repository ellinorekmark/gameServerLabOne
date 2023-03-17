package se.iths.gameserverlabone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
@Service
@SessionScope
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);


}

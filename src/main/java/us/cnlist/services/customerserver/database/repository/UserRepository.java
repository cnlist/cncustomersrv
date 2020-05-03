package us.cnlist.services.customerserver.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.cnlist.objects.people.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getUserById(Long userId);
    User getUserByLogin(String login);
    List<User> findUsersByLoginContaining(String match);
    boolean existsUserByLogin(String login);


}

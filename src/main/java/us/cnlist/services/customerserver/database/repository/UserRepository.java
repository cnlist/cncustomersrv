package us.cnlist.services.customerserver.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.cnlist.objects.people.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getUserById(Long userId);
    boolean existsUserByLogin(String login);

}

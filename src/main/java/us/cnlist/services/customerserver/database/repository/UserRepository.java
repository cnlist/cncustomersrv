package us.cnlist.services.customerserver.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.cnlist.services.customerserver.database.entity.SystemUser;

@Repository
public interface UserRepository extends CrudRepository<SystemUser, Long> {

    SystemUser findSystemUserByLogin(String login);

}

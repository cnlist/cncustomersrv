package us.cnlist.services.customerserver.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.cnlist.services.customerserver.database.entity.DbUserProfile;

@Repository
public interface ProfileRepository extends CrudRepository<DbUserProfile, Long> {

    @Query("SELECT x.userProfile from SystemUser x WHERE x.login =:email")
    DbUserProfile getProfileByEmail(String email);

}

package us.cnlist.services.customerserver.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.cnlist.services.customerserver.database.entity.CitizenProfile;

@Repository
public interface CitizenRepository extends CrudRepository<CitizenProfile, Long> {
}

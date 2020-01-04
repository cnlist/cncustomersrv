package us.cnlist.services.customerserver.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.cnlist.objects.contacts.ContactType;
import us.cnlist.objects.people.Citizen;
import us.cnlist.services.customerserver.database.entity.CitizenContact;

import java.util.List;

@Repository
public interface CitizenContactRepository extends CrudRepository<CitizenContact, Long> {

    @Query(value = "SELECT x FROM CitizenContact x WHERE x.citizenId =:citizenId")
    List<CitizenContact> loadContacts (Long citizenId);

    CitizenContact findCitizenContactByCitizenIdAndContactTypeAndValue(Long id, ContactType type, String val);


}

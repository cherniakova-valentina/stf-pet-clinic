package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepositories extends CrudRepository<Visit, Long> {
}

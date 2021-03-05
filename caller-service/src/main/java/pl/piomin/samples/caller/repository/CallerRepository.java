package pl.piomin.samples.caller.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.samples.caller.model.Caller;

public interface CallerRepository extends CrudRepository<Caller, Integer> {
}

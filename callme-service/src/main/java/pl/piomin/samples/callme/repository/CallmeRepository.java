package pl.piomin.samples.callme.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.samples.callme.model.Callme;

public interface CallmeRepository extends CrudRepository<Callme, Integer> {
}

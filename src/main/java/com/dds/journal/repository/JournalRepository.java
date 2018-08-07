package com.dds.journal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dds.journal.domain.Journal;

@Repository("journalRepository")
public interface JournalRepository extends CrudRepository<Journal, Long>{
	

}

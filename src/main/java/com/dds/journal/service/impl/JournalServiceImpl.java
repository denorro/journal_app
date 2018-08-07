package com.dds.journal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dds.journal.domain.Journal;
import com.dds.journal.repository.JournalRepository;
import com.dds.journal.service.JournalService;

@Service("journalService")
public class JournalServiceImpl implements JournalService {
	
	@Autowired
	private JournalRepository journalRepository;

	@Override
	public Iterable<Journal> getJournals() {
		return journalRepository.findAll();
	}

	@Override
	public Journal getJournal(Long id) {
		return journalRepository.findOne(id);
	}

	@Override
	public Journal saveJournal(Journal journal) {
		return journalRepository.save(journal);
	}

	@Override
	public void deleteJournal(Journal journal) {
		journalRepository.delete(journal);
	}

	@Override
	public void deleteJournals() {
		journalRepository.deleteAll();
		
	}
}

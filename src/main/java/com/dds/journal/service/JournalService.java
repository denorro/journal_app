package com.dds.journal.service;

import com.dds.journal.domain.Journal;

public interface JournalService {
	
	public Iterable<Journal> getJournals();
	public Journal getJournal(Long id);
	public Journal saveJournal(Journal journal);
	public void deleteJournal(Journal journal);
	public void deleteJournals();
}

package com.cenfotec.crud.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cenfotec.crud.domain.Antology;

public interface AntologyService {

	public void save(Antology antology);
	public Optional<Antology> get(Long id);
	public List<Antology> getAll();
	public List<Antology> find(String name);
	public List<Antology> findTopic(String topic);
	public List<Antology> findRelease(String release);
	
	
}

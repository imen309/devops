package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartementServiceImpl implements IDepartementService {

	private final DepartementRepository departementRepository;

	@Autowired
	public DepartementServiceImpl(DepartementRepository departementRepository) {
		this.departementRepository = departementRepository;
	}

	public List<Departement> retrieveAllDepartements() {
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement addDepartement(Departement d) {
		return departementRepository.save(d);
	}

	public Departement updateDepartement(Departement d) {
		return departementRepository.save(d);
	}

	public Departement retrieveDepartement(Integer idDepart) throws DepartementNotFoundException {
		Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

		if (optionalDepartement.isPresent()) {
			return optionalDepartement.get();
		} else {
			// Gérer le cas où le département n'est pas présent
			throw new DepartementNotFoundException("Département non trouvé pour l'ID : " + idDepart);
		}
	}

	public void deleteDepartement(Integer idDepartement) throws DepartementNotFoundException {
		Departement d = retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}

	private class DepartementNotFoundException extends Throwable {
		public DepartementNotFoundException(String s) {
		}
	}
}


package tn.esprit.spring.kaddem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class DepartementServiceImplTest {

        @Mock
        private DepartementRepository departementRepository;

        @InjectMocks
        private DepartementServiceImpl departementService;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testRetrieveAllDepartements() {
            // Mocking the repository behavior
            List<Departement> departements = new ArrayList<>();
            departements.add(new Departement("Informatique"));
            departements.add(new Departement("Mathématiques"));
            when(departementRepository.findAll()).thenReturn(departements);

            // Call the service method
            List<Departement> result = departementService.retrieveAllDepartements();

            // Verify the result
            assertEquals(2, result.size());
            assertEquals("Informatique", result.get(0).getNomDepart());
            assertEquals("Mathématiques", result.get(1).getNomDepart());
        }

        @Test
        public void testAddDepartement() {
            Departement departement = new Departement("Chimie");

            // Mocking the repository behavior
            when(departementRepository.save(departement)).thenReturn(departement);

            // Call the service method
            Departement savedDepartement = departementService.addDepartement(departement);

            // Verify the result
            assertEquals("Chimie", savedDepartement.getNomDepart());
        }

        @Test
        public void testUpdateDepartement() {
            Departement existingDepartement = new Departement(1, "Physique");

            // Mocking the repository behavior
            when(departementRepository.save(existingDepartement)).thenReturn(existingDepartement);

            // Call the service method
            Departement updatedDepartement = departementService.updateDepartement(existingDepartement);

            // Verify the result
            assertEquals("Physique", updatedDepartement.getNomDepart());
        }

        @Test
        public void testRetrieveDepartement() {
            int departementId = 1;
            Departement existingDepartement = new Departement(departementId, "Biologie");

            // Mocking the repository behavior
            when(departementRepository.findById(departementId)).thenReturn(Optional.of(existingDepartement));

            // Call the service method
            Departement retrievedDepartement = departementService.retrieveDepartement(departementId);

            // Verify the result
            assertEquals("Biologie", retrievedDepartement.getNomDepart());
        }

        @Test
        public void testDeleteDepartement() {
            int departementId = 1;
            Departement existingDepartement = new Departement(departementId, "Géologie");

            // Mocking the repository behavior
            when(departementRepository.findById(departementId)).thenReturn(Optional.of(existingDepartement));

            // Call the service method
            departementService.deleteDepartement(departementId);

            // Verify that delete method of repository is called
            verify(departementRepository, times(1)).delete(existingDepartement);
        }

}

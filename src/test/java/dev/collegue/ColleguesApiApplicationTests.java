package dev.collegue;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.collegue.model.Collegue;
import dev.collegue.repository.CollegueRepository;
import dev.collegue.service.CollegueService;
import exception.CollegueInvalideException;
import exception.CollegueNonTrouveException;

public class ColleguesApiApplicationTests {


	private CollegueService service;
	private CollegueRepository collegueRepository;
	private Collegue collegue;

	@Before
	public void setUp() {
		collegue = new Collegue(null, "Tartempion", "Toto", "toto@tartempion.com", LocalDate.of(1970, 01, 30),
				"http://toto.com/toto.jpeg");
		collegueRepository = Mockito.mock(CollegueRepository.class);
		
		service = new CollegueService(collegueRepository);
	}

	@Test
	public void testAjouterUnCollegueOK() throws CollegueInvalideException {
		Collegue actualCollegue = service.ajouterUnCollegue(collegue);
		Assert.assertNotNull("Le matricule du collègue ajouté n'est pas valorisé", actualCollegue.getMatricule());
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueKOCarNomTropCourt() throws CollegueInvalideException {
		collegue.setNom("T");
		service.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueKOCarPrenomTropCourt() throws CollegueInvalideException {
		collegue.setPrenoms("T");
		service.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueKOCarEmailSansArobade() throws CollegueInvalideException {
		collegue.setEmail("tototartempion.com");
		service.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueKOCarEmailTropCourt() throws CollegueInvalideException {
		collegue.setEmail("t@");
		service.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueKOCarPhotoUrlNeCommencePasParHttp() throws CollegueInvalideException {
		collegue.setPhotoUrl("ftp://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegue);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueKOCarMoinsDe18Ans() throws CollegueInvalideException {
		collegue.setDateDeNaissance(LocalDate.now());
		service.ajouterUnCollegue(collegue);
	}

	@Test
	public void testModifierEmailOK() throws CollegueInvalideException, CollegueNonTrouveException {
		service.ajouterUnCollegue(collegue);
		String expected = "toto@nouvelemail.com";
		Mockito.when(collegueRepository.findById(collegue.getMatricule())).thenReturn(Optional.of(collegue));
		service.modifierEmail(collegue.getMatricule(), expected);
		
		String actual = collegue.getEmail();
		Assert.assertThat("L'email n'a pas été modifié en " + expected, actual, Matchers.is(expected));
	}

	@Test(expected = CollegueInvalideException.class)
	public void testModifierEmailKOCollegueInvalid() throws CollegueInvalideException, CollegueNonTrouveException {
		collegue.setEmail("a");
		service.ajouterUnCollegue(collegue);
		service.modifierEmail(collegue.getMatricule(), "doesNotMatterSinceException@dummy.com");
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void testModifierEmailKOCollegueNonTrouve() throws CollegueInvalideException, CollegueNonTrouveException {
		service.ajouterUnCollegue(collegue);
		service.modifierEmail("ceMatriculeNExistePas", "doesNotMatterSinceException@dummy.com");
	}

	@Test
	public void testModifierPhotoUrlOK() throws CollegueInvalideException, CollegueNonTrouveException {
		service.ajouterUnCollegue(collegue);
		String expected = "http://unnouvelUrl.com";
		Mockito.when(collegueRepository.findById(collegue.getMatricule())).thenReturn(Optional.of(collegue));
		service.modifierPhotoUrl(collegue.getMatricule(), expected);
		String actual = collegue.getPhotoUrl();
		Assert.assertThat("L'url de la photo n'a pas été modifié en " + expected, actual, Matchers.is(expected));
	}

	@Test(expected = CollegueInvalideException.class)
	public void testModifierPhotoUrlKOCollegueInvalide() throws CollegueInvalideException, CollegueNonTrouveException {
		collegue.setPhotoUrl("ftp://erogijeroj.com");
		service.ajouterUnCollegue(collegue);
		service.modifierPhotoUrl(collegue.getMatricule(), "http://nouvelurl.com");
	}

}

package dev.collegue;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import dev.collegue.model.Collegue;
import dev.collegue.service.CollegueService;
import exception.CollegueInvalideException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColleguesApiApplicationTests {

	private CollegueService service;
	
	@Before
	public void setUp(){
		service = new CollegueService();
	}
	
	
	@Test
	public void testAjouterUnCollegueOk() throws CollegueInvalideException {
		Collegue collegueAAjouter = new Collegue(null, "Tartempion", "Toto", "toto@tartempion.com", LocalDate.of(1970, 01, 30), "http://toto.com/toto.jpeg");
		Collegue actualCollegue = service.ajouterUnCollegue(collegueAAjouter);
		Assert.assertNotNull("Le matricule du collègue ajouté n'est pas valorisé", actualCollegue.getMatricule());
	}
	
	@Test(expected=CollegueInvalideException.class)
	public void testAjouterUnCollegueKoCarNomTropCourt() throws CollegueInvalideException{
		Collegue collegueAAjouter = new Collegue(null, "A", "Toto", "toto@tartempion.com",  LocalDate.of(1970, 01, 30), "http://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegueAAjouter);
	}
	
	@Test(expected=CollegueInvalideException.class)
	public void testAjouterUnCollegueKoCarPrenomTropCourt() throws CollegueInvalideException{
		Collegue collegueAAjouter = new Collegue(null, "Tartempion", "T", "toto@tartempion.com",  LocalDate.of(1970, 01, 30), "http://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegueAAjouter);
	}
	
	@Test(expected=CollegueInvalideException.class)
	public void testAjouterUnCollegueKoCarEmailSansArobade() throws CollegueInvalideException{
		Collegue collegueAAjouter = new Collegue(null, "Tartempion", "Toto", "tototartempion.com",  LocalDate.of(1970, 01, 30), "http://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegueAAjouter);
	}
	
	@Test(expected=CollegueInvalideException.class)
	public void testAjouterUnCollegueKoCarEmailTropCourt() throws CollegueInvalideException{
		Collegue collegueAAjouter = new Collegue(null, "Tartempion", "Toto", "t@",  LocalDate.of(1970, 01, 30), "http://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegueAAjouter);
	}
	
	@Test(expected=CollegueInvalideException.class)
	public void testAjouterUnCollegueKoCarPhotoUrlNeCommencePasParHttp() throws CollegueInvalideException{
		Collegue collegueAAjouter = new Collegue(null, "Tartempion", "Toto", "toto@tartempion.com",  LocalDate.of(1970, 01, 30), "ftp://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegueAAjouter);
	}
	
	@Test(expected=CollegueInvalideException.class)
	public void testAjouterUnCollegueKoCarMoinsDe18Ans() throws CollegueInvalideException{
		Collegue collegueAAjouter = new Collegue(null, "Tartempion", "Toto", "toto@tartempion.com",  LocalDate.of(2017, 01, 30), "http://toto.com/toto.jpeg");
		service.ajouterUnCollegue(collegueAAjouter);
	}

}

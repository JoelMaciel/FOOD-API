package com.joel.food;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.joel.food.domain.model.Cozinha;
import com.joel.food.domain.repository.CozinhaRepository;
import com.joel.food.util.DatabaseCleaner;
import com.joel.food.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIntegrationIT {

	@LocalServerPort
	private int port;
	private static final int COZINHA_ID_INEXISTENTE = 100;

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource(
				"/json/correto/cozinha-chinesa.json");
		
		databaseCleaner.clearTables();
		prepararDados();
		

	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {

		given()
		.accept(ContentType.JSON)
		.when()
		.get()
		.then()
		.statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {

		given()
		.accept(ContentType.JSON)
		.when()
		.get()
		.then()
		.body("", Matchers.hasSize(quantidadeCozinhasCadastradas));
				
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		given()
		.body(jsonCorretoCozinhaChinesa)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when()
		.post()
		.then()
		 .statusCode(HttpStatus.SC_CREATED);
}
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {

		given()
        .pathParam("cozinhaId", cozinhaAmericana.getId())
        .accept(ContentType.JSON)
    .when()
        .get("/{cozinhaId}")
    .then()
        .statusCode(HttpStatus.SC_OK)
        .body("nome", equalTo(cozinhaAmericana.getNome()));
				
	}
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {

		 given()
	        .pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
	        .accept(ContentType.JSON)
	    .when()
	        .get("/{cozinhaId}")
	    .then()
	        .statusCode(HttpStatus.SC_NOT_FOUND);
			
				
	}
	
	
	
	private void prepararDados() {
		
		Cozinha cozinhaTailandesa = new Cozinha();
	    cozinhaTailandesa.setNome("Tailandesa");
	    cozinhaRepository.save(cozinhaTailandesa);

	    cozinhaAmericana = new Cozinha();
	    cozinhaAmericana.setNome("Americana");
	    cozinhaRepository.save(cozinhaAmericana);
	    
	    quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();

	}
//	@Autowired
//	private CadastroCozinhaService cadastroCozinha;
////
//	@Test
//	public void testarCadastroCozinhaComSucesso() {
//
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Chinesa");
//
//		novaCozinha = cadastroCozinha.salvar(novaCozinha);
//
//		assertThat(novaCozinha).isNotNull();
//		assertThat(novaCozinha.getId()).isNotNull();
//	}
//
//	@Test
//	public void testarCadastroCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//
//		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			cadastroCozinha.salvar(novaCozinha);
//
//		});
//		assertThat(erroEsperado).isNotNull();
//
//	}
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
//		EntidadeEmUsoException exception = assertThrows(EntidadeEmUsoException.class, () -> {
//			Long cozinhaId = 1L;
//			cadastroCozinha.excluir(cozinhaId);
//		});
//		
//		exception.printStackTrace();
//		assertThatExceptionOfType(EntidadeEmUsoException.class);
//	}
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//		CozinhaNaoEncontradaException exception = assertThrows(CozinhaNaoEncontradaException.class, () -> {
//			Long cozinhaId = 10L;
//			cadastroCozinha.excluir(cozinhaId);
//		});
//		exception.printStackTrace();
//		assertThatExceptionOfType(CozinhaNaoEncontradaException.class);
//	}

}

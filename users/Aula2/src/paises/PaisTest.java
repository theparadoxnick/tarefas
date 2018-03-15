package paises;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import paises.Pais;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
	Pais pais, copia;
	static int id = 0;

	@Before
	public void setUp() throws Exception {
		System.out.println("-- setup");
		pais = 	new Pais(id, "Pais1", 205002000, 8512000 );
		copia = new Pais(id, "Pais1", 205002000, 8512000 );
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
		if (pais.toString() == copia.toString()) System.out.println("Pais");
		
	}
	
	
	@Test
	public void test00Load() {
		System.out.println("-- load");
		Pais fixture = new Pais(1, "Coreia do Sul", 51250000, 1002100 );
		Pais novo = new Pais(1, null, 0, 0);
		novo.loadPais();
		assertEquals("teste load", novo, fixture);
	}
	
	@Test
	public void test01Criar() {
		System.out.println("-- create");
		pais.createPais();
		id = pais.getId();
		System.out.println(id);
		copia.setId(id);
		assertEquals("teste create", pais, copia);
	}
	

	@Test
	public void test02Atualizar() {
		System.out.println("-- update");
		pais.setPopulacao(999999);
		copia.setPopulacao(999999);		
		pais.updatePais();
		pais.loadPais();
		assertEquals("teste update", pais, copia);
	}
	@Test
	public void test03Excluir() {
		System.out.println("-- delete");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		pais.deletePais();
		pais.loadPais();
		assertEquals("teste delete", pais, copia);
	}
	
	@Test
	public void test04MaiorPopulacao(){
		System.out.println("-- maior pupulacao");
		String maiorPop = pais.maiorPopulacao();
		assertEquals("teste maiorpop", "Russia", maiorPop);
		
	}
	
	@Test
	public void test05MenorArea(){
		System.out.println("-- menor area");
		String menorArea = pais.menorArea();
		assertEquals("teste menorarea", "Italia", menorArea);
		
	}
	
	@Test
	public void test06vetorTresPaises(){
		System.out.println("-- vetor paises");
		Pais[] vet = pais.vetorTresPaises();
		vet[0].createPais();
		vet[1].createPais();
		vet[2].createPais();
		
	}
}
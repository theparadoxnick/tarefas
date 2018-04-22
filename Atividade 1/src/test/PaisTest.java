package test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.PaisDAO;
import model.PaisTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
	PaisTO pais, copia;
	PaisDAO dao = new PaisDAO();
	static int id = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	
	@Before
	public void setUp() throws Exception {
		System.out.println("-- setup");
		pais = 	new PaisTO(id, "Pais1", 205002000, 8512000 );
		copia = new PaisTO(id, "Pais1", 205002000, 8512000 );
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
		if (pais.toString() == copia.toString()) System.out.println("PORRA");
		
	}
	
	
	@Test
	public void test00Load() {
		System.out.println("-- load");
		
		//para funcionar o cliente 1 deve ter sido carregado no banco por fora
		PaisTO fixture = new PaisTO(1, "Brasil", 205002000, 8512000 );
		PaisTO novo = new PaisTO(1, null, 0, 0);
		dao.loadPais(novo);
		assertEquals("teste load", novo, fixture);
	}
	
	@Test
	public void test01Criar() {
		System.out.println("-- create");
		dao.createPais(pais);
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
		dao.updatePais(pais);
		dao.loadPais(pais);
		System.out.println(pais.toString());
		assertEquals("teste update", pais, copia);
	}
	@Test
	public void test03Excluir() {
		System.out.println("-- delete");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		dao.deletePais(pais);
		dao.loadPais(pais);
		assertEquals("teste delete", pais, copia);
	}
	
	@Test
	public void test04MaiorPopulacao(){
		System.out.println("-- maior pupulacao");
		String maiorPop = dao.maiorPopulacao();
		assertEquals("teste maiorpop", "China", maiorPop);
		
	}
	
	@Test
	public void test05MenorArea(){
		System.out.println("-- menor area");
		String menorArea = dao.menorArea();
		assertEquals("teste menorarea", "India", menorArea);
		
	}
	

}
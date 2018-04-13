package service;

import dao.PaisDAO;
import model.PaisTO;

public class PaisService {
	PaisDAO dao = new PaisDAO();
	
	public int createPais(PaisTO pais) {
		return dao.createPais(pais);
	}

	public PaisTO loadPais(PaisTO pais){
		PaisTO to = dao.loadPais(pais);
		return to;
	}
	
	public void updatePais(PaisTO pais){
		dao.updatePais(pais);
	}
	
	public void deletePais(PaisTO pais){
		dao.deletePais(pais);
	}
}

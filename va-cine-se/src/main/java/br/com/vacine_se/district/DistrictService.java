package br.com.vacine_se.district;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class DistrictService {

	private DistrictRepository repository;
	
	private static List<District> getDistrictFromData(){
		return List.of(
				new District("Alecrim",2 ,3),
				new District("Areia Preta",3 ,3),
				new District("Barro Vermelho",3 ,3),
				new District("Bom Pastor",1 ,3),
				new District("Candelária",2 ,1),
				new District("Capim Macio",3 ,1),
				new District("Cidade Alta",3 ,4),
				new District("Cidade Esperança",2 ,2),
				new District("Cidade Nova",1 ,2),
				new District("Dix-sept Rosado",2 ,3),
				new District("Felipe Camarão",1 ,2),
				new District("Guararapes",0 ,2),
				new District("Igapó",1 ,4),
				new District("Lagoa Azul",1 ,6),
				new District("Lagoa Nova", 2, 2),
				new District("Lagoa Seca",3 ,3),
				new District("Mãe Luíza",4 ,3),
				new District("Neópolis",3 ,1),
				new District("Nordeste",1 ,3),
				new District("Nossa Senhora da Apresentação",0 ,5),
				new District("Nossa Senhora de Nazaré",2 ,3),
				new District("Nova Descoberta",3, 2),
				new District("Pajuçara",2 ,6),
				new District("Petrópolis",3 ,4),
				new District("Pitimbu",2 ,1),
				new District("Planalto",1 ,1),
				new District("Ponta Negra",0 ,4),
				new District("Potengi",1 ,5),
				new District("Praia do Meio",3 ,4),
				new District("Quintas",2 ,3),
				new District("Redinha",2 ,5),
				new District("Ribeira",3 ,4),
				new District("Rocas",3 ,4),
				new District("Salinas",1 ,4),
				new District("Santos Reis",3 ,5),
				new District("Tirol",3 ,3)
				);
	}

	public DistrictService(DistrictRepository repository) {
		this.repository = repository;
		for (District dis : getDistrictFromData()) {
			this.repository.save(dis);
		}
	}
	
	public List<District> findAll() {
        List<District> list = new ArrayList<>();
        Iterable<District> Districts = repository.getAll();
        Districts.forEach(list::add);
        return list;
    }
	public Optional<District> find(int id) {
		return repository.getById(id);
	}
	
	public Integer getDistance(District d1, District d2) {
		int distance = Math.abs(d1.getCoordnateX()-d2.getCoordnateX()) + Math.abs(d1.getCoordnateY()-d2.getCoordnateY());
		return distance;
	}
}


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
				new District(1L, "Alecrim",2 ,3),
				new District(2L, "Areia Preta",3 ,3),
				new District(3L, "Barro Vermelho",3 ,3),
				new District(4L, "Bom Pastor",1 ,3),
				new District(5L, "Candelária",2 ,1),
				new District(6L, "Capim Macio",3 ,1),
				new District(7L, "Cidade Alta",3 ,4),
				new District(8L, "Cidade Esperança",2 ,2),
				new District(9L, "Cidade Nova",1 ,2),
				new District(10L, "Dix-sept Rosado",2 ,3),
				new District(11L, "Felipe Camarão",1 ,2),
				new District(12L, "Guararapes",0 ,2),
				new District(13L, "Igapó",1 ,4),
				new District(14L, "Lagoa Azul",1 ,6),
				new District(15L, "Lagoa Nova", 2, 2),
				new District(17L, "Lagoa Seca",3 ,3),
				new District(18L, "Mãe Luíza",4 ,3),
				new District(19L, "Neópolis",3 ,1),
				new District(20L, "Nordeste",1 ,3),
				new District(21L, "Nossa Senhora da Apresentação",0 ,5),
				new District(22L, "Nossa Senhora de Nazaré",2 ,3),
				new District(23L, "Nova Descoberta",3, 2),
				new District(24L, "Pajuçara",2 ,6),
				new District(25L, "Petrópolis",3 ,4),
				new District(26L, "Pitimbu",2 ,1),
				new District(27L, "Planalto",1 ,1),
				new District(28L, "Ponta Negra",0 ,4),
				new District(29L, "Potengi",1 ,5),
				new District(30L, "Praia do Meio",3 ,4),
				new District(31L, "Quintas",2 ,3),
				new District(32L, "Redinha",2 ,5),
				new District(33L, "Ribeira",3 ,4),
				new District(34L, "Rocas",3 ,4),
				new District(35L, "Salinas",1 ,4),
				new District(36L, "Santos Reis",3 ,5),
				new District(37L, "Tirol",3 ,3)
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


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
				new District("Alecrim",2 ,3, 20000),
				new District("Areia Preta",3 ,3, 15000),
				new District("Barro Vermelho",3 ,3, 10000),
				new District("Bom Pastor",1 ,3, 12000),
				new District("Candelária",2 ,1, 22000),
				new District("Capim Macio",3 ,1, 34000),
				new District("Cidade Alta",3 , 11000),
				new District("Cidade Esperança",2 ,2, 9800),
				new District("Cidade Nova",1 ,2, 5400),
				new District("Dix-sept Rosado",2 ,3, 8600),
				new District("Felipe Camarão",1 ,2, 11500),
				new District("Guararapes",0 ,2, 6300),
				new District("Igapó",1 ,4, 5400),
				new District("Lagoa Azul",1 ,6, 2780),
				new District("Lagoa Nova", 2, 2, 12000),
				new District("Lagoa Seca",3 ,3, 18000),
				new District("Mãe Luíza",4 ,3, 5000),
				new District("Neópolis",3 ,1, 10000),
				new District("Nordeste",1 ,3, 5400),
				new District("Nossa Senhora da Apresentação",0 ,5, 3900),
				new District("Nossa Senhora de Nazaré",2 ,3, 7900),
				new District("Nova Descoberta",3, 2, 2500),
				new District("Pajuçara",2 ,6, 4000),
				new District("Petrópolis",3 ,4, 8090),
				new District("Pitimbu",2 ,1, 7800),
				new District("Planalto",1 ,1, 5800),
				new District("Ponta Negra",0 ,4, 15800),
				new District("Potengi",1 ,5, 5900),
				new District("Praia do Meio",3 ,4, 8800),
				new District("Quintas",2 ,3, 5200),
				new District("Redinha",2 ,5, 5400),
				new District("Ribeira",3 ,4, 2809),
				new District("Rocas",3 ,4, 1980),
				new District("Salinas",1 ,4, 890),
				new District("Santos Reis",3 ,5, 5700),
				new District("Tirol",3 ,3, 12000)
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


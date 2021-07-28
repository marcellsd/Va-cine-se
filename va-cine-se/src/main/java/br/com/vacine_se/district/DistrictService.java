package br.com.vacine_se.district;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
@EnableMapRepositories
public class DistrictService {
	private final CrudRepository<District, Long> repository;
	
	public DistrictService(CrudRepository<District, Long> repository) {
		this.repository = repository;
		this.repository.saveAll(getDistrictFromData());
	}
	
	
	// TODO: Adicionar todos os bairros
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
				new District(16L, "Lagoa Seca",3 ,3),
				new District(16L, "Mãe Luíza",4 ,3),
				new District(16L, "Neópolis",3 ,1),
				new District(16L, "Nordeste",1 ,3),
				new District(16L, "Nossa Senhora da Apresentação",0 ,5),
				new District(16L, "Nossa Senhora de Nazaré",2 ,3),
				new District(16L, "Nova Descoberta",3, 2),
				new District(16L, "Pajuçara",2 ,6),
				new District(16L, "Petrópolis",3 ,4),
				new District(16L, "Pitimbu",2 ,1),
				new District(16L, "Planalto",1 ,1),
				new District(16L, "Ponta Negra",0 ,4),
				new District(16L, "Potengi",1 ,5),
				new District(16L, "Praia do Meio",3 ,4),
				new District(16L, "Quintas",2 ,3),
				new District(16L, "Redinha",2 ,5),
				new District(16L, "Ribeira",3 ,4),
				new District(16L, "Rocas",3 ,4),
				new District(16L, "Salinas",1 ,4),
				new District(16L, "Santos Reis",3 ,5),
				new District(16L, "Tirol",3 ,3)
				);
	}
	
	public List<District> findAll() {
        List<District> list = new ArrayList<>();
        Iterable<District> Districts = repository.findAll();
        Districts.forEach(list::add);
        return list;
    }
	public Optional<District> find(Long id) {
		return repository.findById(id);
	}
}


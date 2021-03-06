package br.com.vacine_se.vaccination_site;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.vacine_se.scheduling.SchedulingService;
import br.com.vacine_se.scheduling.Scheduling;
import br.com.vacine_se.user.User;
import br.com.vacine_se.user.UserService;


@Service
public class VaccinationSiteService {

	private VaccinationSiteRepository repository;

	private SchedulingService schedulingService;
	
	private static List<VaccinationSite> getVaccinationSiteFromData(){
		return List.of(
				new VaccinationSite("UBS Pajuçara", 22),
				new VaccinationSite("USF Nova Natal", 13),
				new VaccinationSite("USF Redinha", 30),
				new VaccinationSite("USF Vale Dourado", 12),
				new VaccinationSite("USF Nova Aliança", 19),
				new VaccinationSite("UBS Candelária", 4),
				new VaccinationSite("ESF Rosangela Lima", 25),
				new VaccinationSite("UBS Nova Descoberta", 21),
				new VaccinationSite("UBS Satélite", 24),
				new VaccinationSite("UBS São João", 35),
				new VaccinationSite("USF Rocas", 32),
				new VaccinationSite("UBS Alecrim", 0),
				new VaccinationSite("Unidade Mista Mãe Luiza", 16),
				new VaccinationSite("UBS Lagoa Seca", 15),
				new VaccinationSite("USF Nazaré", 20),
				new VaccinationSite("UM Felipe Camarão II", 10),
				new VaccinationSite("USF Cidade Nova", 8),
				new VaccinationSite("USF Bairro Nordeste", 18),
				new VaccinationSite("USF Quintas", 29),
				new VaccinationSite("USF Monte Líbano", 3)
				
				);
	}
	
	public VaccinationSiteService(VaccinationSiteRepository repository, SchedulingService ss) {
		this.repository = repository;
		this.schedulingService = ss;
		for (VaccinationSite vacin : getVaccinationSiteFromData()) {
			this.repository.save(vacin);
		}
	}
	
	
	public List<VaccinationSite> findAll() {
        List<VaccinationSite> list = new ArrayList<>();
        Iterable<VaccinationSite> vaccinationSites = repository.getAll();
        vaccinationSites.forEach(list::add);
        return list;
    }
	public Optional<VaccinationSite> find(int id) {
		return repository.getById(id);
	}
	
	public VaccinationSite create(VaccinationSite vaccinationSite) {
		return repository.save(vaccinationSite);
	}
	
	public VaccinationSite update(int id, VaccinationSite newVaccinationSite){
		VaccinationSite vaccinationSite = this.find(id).orElseThrow();
		newVaccinationSite.setId(vaccinationSite.getId());
		return repository.update(newVaccinationSite);
	}
	
	public void delete(int id) {
		VaccinationSite vaccinationSite = this.find(id).orElseThrow();
		repository.delete(vaccinationSite);
	}

	public String getQueueSize(int id) {
		int totalDoses = repository.getById(id).get().getTotalOfVaccines();
		int totalSchedules = schedulingService.totalSchedulesForADate(LocalDate.now().plusDays(30), id);

		int remainingDoses = totalDoses - totalSchedules;
		if (remainingDoses > 75) {
			return "Pouca fila";
		}
		if (remainingDoses > 50) {
			return "Fila moderada";
		}
		return "Fila grande";
	}
	
	public String applyVaccine(int vaccinationSiteId, int userId, UserService userService, SchedulingService schedulingService) {
		User user = userService.find(userId).orElseThrow();
		VaccinationSite vaccinationSite = this.find(vaccinationSiteId).orElseThrow();
		Scheduling scheduling = schedulingService.find(user.getSchedulingId()).orElseThrow();
		if (vaccinationSite.getTotalOfVaccines() > 0) {
			if(user.hasFirstDose() == false) {
				user.setFirstDose(true);
				scheduling.setDate(scheduling.getDate().plusDays(28));
				vaccinationSite.setTotalOfVacines(vaccinationSite.getTotalOfVaccines()-1);
				return "{" + user.getName() + " first dose has been applyed}";
			}
			else if(user.hasSecondDose() == false) {
				user.setSecondDose(true);
				schedulingService.delete(scheduling.getId());
				vaccinationSite.setTotalOfVacines(vaccinationSite.getTotalOfVaccines()-1);
				return "{" + user.getName() + " second dose has been applyed}";
			}
		}
		return "{This vaccination site has no vaccine dose left}";
	}
}


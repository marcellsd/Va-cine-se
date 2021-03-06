package br.com.vacine_se.user;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.scheduling.Scheduling;
import br.com.vacine_se.scheduling.SchedulingService;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;

@Service
public class UserService{

	private VaccinationSiteService vaccinationSiteService;
	private SchedulingService schedulingService;
	private UserRepository repository;
	private DistrictService districtService;


	public UserService(UserRepository repository, DistrictService districtService, VaccinationSiteService vaccinationSiteService, SchedulingService schedulingService) {
		this.repository = repository;
		this.districtService = districtService;
		this.vaccinationSiteService = vaccinationSiteService;
		this.schedulingService = schedulingService;
	}
	
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        Iterable<User> users = repository.getAll();
		// this.populate(list);
        users.forEach(list::add);
        return list;
    }

	public void populate(List<User> list) {
		User joao = new User("João", 34, 0, false, "123.456.789-10", "joao", "joao123", "joao@gmail.com", "987654321", 0);
		list.add(joao);
	}

	public Optional<User> find(int id) {
		return repository.getById(id);
	}
	
	public User create(User user) throws Exception {
		if (repository.cpfExists(user.getCpf())) {
			throw new Exception("CPF already exists");
		}
		if(repository.usernameExists(user.getUsername())) {
			throw new Exception("Username already exists");
		}
		int schedId = this.setUserScheduling(user, districtService, vaccinationSiteService, schedulingService);
		user.setSchedulingId(schedId);
		return repository.save(user);
	}
	
	public User update(int id, User newUser) throws NoSuchElementException, IndexOutOfBoundsException {
		try {
			User user = this.find(id).orElseThrow();
			newUser.setId(user.getId());
			return repository.update(newUser);
		} catch(NoSuchElementException e) {
			throw e;
		} catch(IndexOutOfBoundsException e) {
			throw e;
		}
      }
	
	public boolean delete(int id) throws NoSuchElementException, IndexOutOfBoundsException {
		try {
			User user = this.find(id).orElseThrow();
			repository.delete(user);
			return true;
		} catch(NoSuchElementException e) {
			throw e;
		} catch(IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	public int setUserScheduling(User user, DistrictService districtService, 
										VaccinationSiteService vaccinationSiteService,
										SchedulingService schedulingService) throws NoSuchElementException, IndexOutOfBoundsException{
		try {
			VaccinationSite nearestVacSite = null;
			int distance = Integer.MAX_VALUE;
				for (VaccinationSite vacSite : vaccinationSiteService.findAll()) {
					int currentDistance = districtService.getDistance(districtService.find(user.getDistrictId()).orElseThrow(),
																districtService.find(vacSite.getDistrictId()).orElseThrow());
					
					if (user.getDistrictId() == vacSite.getDistrictId()) {
						nearestVacSite = vacSite;
						Scheduling scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
						scheduling = schedulingService.create(scheduling);
						return scheduling.getId();
					}
					if(currentDistance < distance) {
						distance = currentDistance;
						nearestVacSite = vacSite;
					}
				}
				Scheduling scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
				scheduling = schedulingService.create(scheduling);
				return scheduling.getId();
				
			}  catch(NoSuchElementException e) {
				throw e;
			} catch(IndexOutOfBoundsException e) {
				throw e;
			}
	}
	
	public List<VaccinationSite> getNearestVaccinationSites(User user, DistrictService districtService, 
															VaccinationSiteService vaccinationSiteService) {
	
		List<VaccinationSite> unorderedList = vaccinationSiteService.findAll();
		List<VaccinationSite> orderedList = new ArrayList<>();
		VaccinationSite nearestVacSite = null;
		for (VaccinationSite vacSite : vaccinationSiteService.findAll()) {
			if (user.getDistrictId() == vacSite.getDistrictId()){
				orderedList.add(0,vacSite);
			}
			else {
				int vacSiteDistance = districtService.getDistance(districtService.find(user.getDistrictId()).orElseThrow(),
																districtService.find(vacSite.getDistrictId()).orElseThrow());
				int currentDistance = vacSiteDistance;	
				nearestVacSite = vacSite;
				int count = 0;
				int nearestMarker = 0;
				if (unorderedList.size()!=0) {
					for (VaccinationSite vacSiteIn : unorderedList) {
						int vacSiteInDistance = districtService.getDistance(districtService.find(user.getDistrictId()).orElseThrow(),
						districtService.find(vacSiteIn.getDistrictId()).orElseThrow());
						if (currentDistance >= vacSiteInDistance) {
							nearestVacSite = vacSiteIn;
							currentDistance = vacSiteInDistance;
							nearestMarker = count;
						}
						count++;
					}
					unorderedList.remove(nearestMarker);
				
				}
				orderedList.add(nearestVacSite);
				if (orderedList.size() == 5) return orderedList;
			}
		}
		return orderedList;
	}
}

package br.com.vacine_se.scheduling;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.user.User;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;


@Service
public class SchedulingServiceCovid extends SchedulingService {

	public SchedulingServiceCovid(SchedulingRepository repository) {
		super(repository);
	}

	public int setUserScheduling(User user, DistrictService districtService, 
										VaccinationSiteService vaccinationSiteService) throws NoSuchElementException, IndexOutOfBoundsException{
		try {
			VaccinationSite nearestVacSite = null;
			int distance = Integer.MAX_VALUE;
				for (VaccinationSite vacSite : vaccinationSiteService.findAll()) {
					int currentDistance = districtService.getDistance(districtService.find(user.getDistrictId()).orElseThrow(),
																districtService.find(vacSite.getDistrictId()).orElseThrow());
					
					if (user.getDistrictId() == vacSite.getDistrictId()) {
						nearestVacSite = vacSite;
						Scheduling scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
						scheduling = this.create(scheduling);
						return scheduling.getId();
					}
					if(currentDistance < distance) {
						distance = currentDistance;
						nearestVacSite = vacSite;
					}
				}
				Scheduling scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
				scheduling = create(scheduling);
				return scheduling.getId();
				
			}  catch(NoSuchElementException e) {
				throw e;
			} catch(IndexOutOfBoundsException e) {
				throw e;
			}
	}
}

package br.com.vacine_se.scheduling;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.user.User;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;

@Service
public class SchedulingServiceGripe extends SchedulingService{

    public SchedulingServiceGripe(SchedulingRepository repository) {
        super(repository);
    }

    @Override
    public int setUserScheduling(User user, DistrictService districtService,
            VaccinationSiteService vaccinationSiteService) {
        try {
			VaccinationSite nearestVacSite = getClosestVaccinationSite(user, vaccinationSiteService, districtService);

			Scheduling scheduling = null;
			if (nearestVacSite.getTotalOfVaccines() > 0) {
				scheduling = new Scheduling(LocalDate.now(), nearestVacSite.getId());
			}
			else {
				scheduling = new Scheduling(LocalDate.now().plusDays(30), nearestVacSite.getId());
			}

			scheduling = create(scheduling);
			return scheduling.getId();
				
			}  catch(NoSuchElementException e) {
				throw e;
			} catch(IndexOutOfBoundsException e) {
				throw e;
			}
    }
    
}

package br.com.vacine_se.vaccine_distribution;

import java.util.List;

import br.com.vacine_se.data.Data;
import br.com.vacine_se.district.District;
import br.com.vacine_se.district.DistrictService;
import br.com.vacine_se.vaccination_site.VaccinationSite;
import br.com.vacine_se.vaccination_site.VaccinationSiteRepository;
import br.com.vacine_se.vaccination_site.VaccinationSiteService;

public class StrategyPopulationProportional implements DistributionStrategy {
    private DistrictService districtService;
    private VaccinationSiteService vacinationSiteService;

    public StrategyPopulationProportional(DistrictService ds, VaccinationSiteService vs) {
        this.districtService = ds;
        this.vacinationSiteService = vs;
    }

    @Override
    public void distribute(Data data, VaccinationSiteRepository repository) {
        List<District> districts = districtService.findAll();
        List<VaccinationSite> sites = vacinationSiteService.findAll();
        int totalPopulation = 0;

        for (District district : districts) {
            totalPopulation += district.getPopulation();
        }

        int remain_ant = 0;
        int remain = data.getVaccinesQuantity();
        while (remain > 0) {
            int totalDistributedQuantity = 0;
            for (VaccinationSite site: sites) {
                float quantity = (float)(districtService.find(site.getDistrictId()).get().getPopulation()) / (float)totalPopulation * (float)remain;
                totalDistributedQuantity += (int)quantity;
                site.setTotalOfVacines(site.getTotalOfVaccines() + (int)quantity);
            }
            remain_ant = remain;
            remain -= totalDistributedQuantity;
            if (remain_ant == remain) break;
        }
        // arbitrary put vaccines remains
        vacinationSiteService.find(1).get().setTotalOfVacines(remain);
    }

    
}

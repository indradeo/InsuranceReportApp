package org.dev.InsuranceReportApp.service;


import org.dev.InsuranceReportApp.binder.SearchRequest;
import org.dev.InsuranceReportApp.entity.CitizenPlan;
import org.dev.InsuranceReportApp.repo.CitizenPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private CitizenPlanRepo repo;

    @Override
    public List<String> getPlanNames() {
        return repo.getPlanNames();
    }

    @Override
    public List<String> getPlanStatus() {
        return repo.getPlanStatus();
    }

  
	@Override
    public List<CitizenPlan> searchCitizens(SearchRequest req) {
    	
    	CitizenPlan entity= new CitizenPlan();
    	
    	if(null!=req.getPlanName()&& !"".equals(req.getPlanName())) {
    		entity.setPlanName(req.getPlanName());
    	}
    	if(null!=req.getPlanStatus()&& !"".equals(req.getPlanStatus())) {
    		entity.setPlanStatus(req.getPlanStatus());
    	}
    	if(null!=req.getGender()&& !"".equals(req.getGender())) {
    		entity.setGender(req.getGender());
    	}
    	
        return repo.findAll(Example.of(entity));
    }

    @Override
    public boolean exportExcel() {
        return false;
    }

    @Override
    public boolean exportPdf() {
        return false;
    }

	@Override
	public List<CitizenPlan> findAll() {
		
		return repo.findAll();
	}
}

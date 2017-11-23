package lh.service.impl;

import lh.dao.IDetailsDAO;
import lh.dao.ITypeDAO;
import lh.service.IDetailsService;
import lh.vo.Details;
import lh.vo.Emp;
import lh.vo.Subtype;
import lh.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsServiceImpl implements IDetailsService {
    @Autowired
    private IDetailsDAO detailsDAO;

    @Autowired
    private ITypeDAO typeDAO;

    @Override
    public boolean add(Details details) throws Exception {
        details.setAmount(1);
        return detailsDAO.doCreate(details);
    }

    @Override
    public List<Details> listPrebuy(Emp emp) throws Exception {
        return detailsDAO.findAllPrebuy(emp.getEid());
    }

    @Override
    public Details findById(Details details) throws Exception {
        Details d = detailsDAO.findById(details.getDid());
        Type type = typeDAO.findById(d.getTid());
        Subtype subtype = typeDAO.findSubTypeByStid(d.getStid());
        d.setType(type);
        d.setSubtype(subtype);
        return d;
    }

    @Override
    public boolean edit(Details details) throws Exception {
        return detailsDAO.doUpdate(details);
    }

    @Override
    public boolean checkTitle(Details details) throws Exception {
        return detailsDAO.findByTitle(details) > 0;
    }

    @Override
    public boolean updateAmount(Details details) throws Exception {
        return detailsDAO.updateAmount(details);
    }

    @Override
    public boolean deleteDetails(String[] dids) throws Exception {
        return detailsDAO.deleteDetails(dids);
    }
}

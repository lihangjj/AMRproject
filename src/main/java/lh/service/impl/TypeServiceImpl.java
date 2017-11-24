package lh.service.impl;

import lh.dao.ITypeDAO;
import lh.service.ITypeService;
import lh.vo.Subtype;
import lh.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private ITypeDAO typeDAO;

    @Override
    public List<Type> list() throws Exception {
        return typeDAO.findAll();
    }

    @Override
    public boolean edit(Type type) throws Exception {
        return typeDAO.doUpdate(type);
    }

    @Override
    public List<Subtype> findSubTypeByTid(Type type) throws Exception {
        return typeDAO.findSubTypeByTid(type);
    }

    @Override
    public boolean editSubtype(Subtype subtype) throws Exception {
        return typeDAO.doUpdateSubtype(subtype);
    }


}

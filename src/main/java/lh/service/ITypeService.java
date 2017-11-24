package lh.service;

import lh.vo.Subtype;
import lh.vo.Type;

import java.util.List;

public interface ITypeService {
    List<Type> list() throws Exception;

    boolean edit(Type type) throws Exception;

    List<Subtype> findSubTypeByTid(Type type) throws Exception;

    boolean editSubtype(Subtype subtype) throws Exception;
}

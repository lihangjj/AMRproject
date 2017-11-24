package lh.dao;

import lh.vo.Subtype;
import lh.vo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeDAO extends IDAO<Integer, Type> {
    List<Subtype> findSubTypeByTid(Type type);
    boolean doUpdateSubtype(Subtype subtype);
    Subtype findSubTypeByStid(Integer stid);

 }

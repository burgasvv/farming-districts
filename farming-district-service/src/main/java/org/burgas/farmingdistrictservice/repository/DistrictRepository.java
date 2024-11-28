package org.burgas.farmingdistrictservice.repository;

import org.burgas.farmingdistrictservice.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select distinct d.* from district d
                               where d.archive = false and concat(d.name,' ',d.code,' ',d.name,' ')
                                         ilike concat('%',?1,'%')
                    """
    )
    List<District> findDistrictsByFilter(String filter);

    @Query(
            nativeQuery = true,
            value = """
                    select distinct d.* from district d join farmer_district fd on d.id = fd.district_id
                                        where fd.farmer_id = ?1
                    """
    )
    List<District> findSowingFieldsForFarmer(Long farmerId);
}

package team.ustc.sensor.dao;

import org.springframework.stereotype.Repository;
import team.ustc.sensor.entity.Company;

import java.util.List;

@Repository
public interface CompanyDao {

    List<Company> searchCompany(String word);
    /**
     * 查询所有公司信息
     * @return 所有公司列表
     */
    List<Company> getAllCompanies();

    /**
     * 按公司名查询公司信息
     * @param companyName 查询公司名称
     * @return 查询到的公司信息
     */
    Company queryCompany(String companyName);

    /**
     * 根据公司名称自动创建公司
     * @param companyName 公司名称
     */
    void addCompany(String companyName,String updateTime);
}

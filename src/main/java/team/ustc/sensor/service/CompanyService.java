package team.ustc.sensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ustc.sensor.dao.CompanyDao;
import team.ustc.sensor.entity.Company;
import team.ustc.sensor.entity.GatewayView;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyDao companyDao;

    /* 关键字查询 */
    public List<Company> searchCompany(String word) {
        return companyDao.searchCompany(word);
    }

    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }

    public Company queryCompany(String companyName) {
        return companyDao.queryCompany(companyName);
    }

    public int addCompany(String companyName,String updateTime) {
        companyDao.addCompany(companyName,updateTime);
        if (companyDao.queryCompany(companyName) != null) {
            //插入成功时返回1
            return 1;
        } else {
            //插入不成功时返回-1
            return -1;
        }
    }
}

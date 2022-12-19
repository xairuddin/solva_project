package kz.solva.service;

import kz.solva.dto.LimitDto;
import kz.solva.mapper.LimitMapper;
import kz.solva.model.Category;
import kz.solva.model.Limit;
import kz.solva.repository.CategoryRepository;
import kz.solva.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class LimitService {
    private final LimitRepository limitRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public LimitService(LimitRepository limitRepository, CategoryRepository categoryRepository) {
        this.limitRepository = limitRepository;
        this.categoryRepository = categoryRepository;
    }

    public Limit newLimit(LimitDto limitDto) {
        Limit limit = new Limit();
        Limit LastLimit = new Limit();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        List<Limit> LastLimits = limitRepository.findLimitByCategoryOwnerIdAndAccount_from(limitDto.getCategory_id(), limitDto.getAccount_from());
        if (LastLimits.size() != 0){
            LastLimit = LastLimits.get(0);
        }else {
            LastLimit = null;
        }
        double limitRest = limitDto.getLimit_sum();
        if (LastLimit != null){
            limitRest = limitDto.getLimit_sum()-(LastLimits.get(0).getLimit_sum()-LastLimits.get(0).getLimit_rest());
        }
        Category category = categoryRepository.findById(limitDto.getCategory_id()).orElseThrow();
        limit.setAccount_from(limitDto.getAccount_from());
        limit.setLimit_sum(limitDto.getLimit_sum());
        limit.setLimit_rest(limitRest);
        limit.setLimit_datetime(date);
        limit.setLimit_currency_shortname("USD");
        limit.setCategoryOwner(category);

        return limitRepository.save(limit);
    }

    public Limit findById(Long id) {
        return limitRepository.findById(id).orElse(null);
    }

    public List<Limit> findLimitByAccount_fromAndExpense_category(Long account_from, String expense_category){
        return limitRepository.findLimitByAccount_fromAndExpense_category(account_from,expense_category);
    }
    public List<Limit> getAllLimits(){
        return limitRepository.findAll();
    }

    public void save(Limit limit){
        limitRepository.save(limit);
    }
    public List<Limit> findLimitsByAccount(Long account_from) {
        return limitRepository.findLimitByAccount_from(account_from);
    }
}

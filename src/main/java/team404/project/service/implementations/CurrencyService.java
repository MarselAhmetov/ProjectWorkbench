package team404.project.service.implementations;

import org.springframework.stereotype.Service;
import team404.project.model.enums.Currency;

@Service
public class CurrencyService {

    public Integer getCurrencyToRuble(Currency currency) {
        switch (currency) {
            case RUBLE:
                return 1;
            case DOLLAR:
                return 70;
            default:
                return 0;
        }
    }
}

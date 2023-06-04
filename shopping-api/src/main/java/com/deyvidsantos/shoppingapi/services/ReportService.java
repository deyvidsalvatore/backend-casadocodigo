package com.deyvidsantos.shoppingapi.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.deyvidsantos.shoppingapi.converter.DTOConverter;
import com.deyvidsantos.shoppingclient.dto.ShopDTO;
import com.deyvidsantos.shoppingclient.dto.ShopReportDTO;
import org.springframework.stereotype.Service;
import com.deyvidsantos.shoppingapi.models.Shop;
import com.deyvidsantos.shoppingapi.repositories.impl.ReportRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
    
    private final ReportRepositoryImpl reportRepository;

    public List<ShopDTO> getShopsByFilter(Date dateInicio, Date dateFim, Float valorMinimo) {
        List<Shop> shops = reportRepository.getShopByFilters(dateInicio, dateFim, valorMinimo);
        return shops
            .stream()
            .map(DTOConverter::convert)
            .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return reportRepository
                .getReportByDate(dataInicio, dataFim);
    }
}

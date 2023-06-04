package com.deyvidsantos.shoppingapi.controllers;

import java.util.Date;
import java.util.List;

import com.deyvidsantos.shoppingclient.dto.ShopDTO;
import com.deyvidsantos.shoppingclient.dto.ShopReportDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.deyvidsantos.shoppingapi.services.ReportService;
import com.deyvidsantos.shoppingapi.services.ShopService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final ReportService reportService;

    @GetMapping("/shopping")
    public List<ShopDTO> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShopByUser(@PathVariable String userIdentifier) {
        return shopService.getShopByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShopsByDate(@RequestBody ShopDTO shopDTO) {
        return shopService.getShopByDate(shopDTO);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO getShopById(@PathVariable Long id) {
        return shopService.findShopById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(
            @RequestHeader(name = "key", required=true) String key,
            @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
        @RequestParam(name = "dataInicio", required = true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
        @RequestParam(name = "dataFim", required = false)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
        @RequestParam(name = "valorMinimo", required = false) Float valorMinimo
    ) {
        return reportService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
        @RequestParam(name = "dataInicio", required=true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
        @RequestParam(name = "dataFim", required=true)
        @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim
    ) {
        return reportService.getReportByDate(dataInicio, dataFim);
    }
}

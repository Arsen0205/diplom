package com.example.diplom.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AnalyticsSupplierDtoResponse {
    private int totalPaidOrders;
    private BigDecimal totalProfit;
    private BigDecimal totalRevenue;
}

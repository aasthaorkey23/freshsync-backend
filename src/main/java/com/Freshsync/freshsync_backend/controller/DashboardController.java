package com.Freshsync.freshsync_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Freshsync.freshsync_backend.service.DashboardService;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/api/dashboard")
    public Map<String, Long> getDashboardCounts() {
        return dashboardService.getDashboardCounts();
    }
}
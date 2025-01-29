package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.AddSupportRequestDto;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.SupportRequest;
import com.example.diploma_thesis.repository.SupportRequestRepository;
import com.example.diploma_thesis.service.SupportRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/support")
public class SupportRequestController {

    private final SupportRequestService supportRequestService;


    @PostMapping("/create")
    public ResponseEntity<String> createRequest(@Valid @RequestBody AddSupportRequestDto request){
        supportRequestService.createRequest(request);
        return ResponseEntity.ok("Ваш запрос успешно создан");
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupportRequest>> getAllRequest(){
        return ResponseEntity.ok(supportRequestService.getAllRequests());
    }


    @GetMapping("/user")
    public ResponseEntity<List<SupportRequest>> getUserRequests(@Valid @RequestBody AddSupportRequestDto request) {
        return ResponseEntity.ok(supportRequestService.getUserRequest(request));
    }

}

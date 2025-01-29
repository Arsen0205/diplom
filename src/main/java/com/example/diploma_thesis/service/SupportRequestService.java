package com.example.diploma_thesis.service;


import com.example.diploma_thesis.dto.request.AddSupportRequestDto;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.SupportRequest;
import com.example.diploma_thesis.repository.SupportRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupportRequestService {

    private final SupportRequestRepository supportRequestRepository;

    public Response createRequest(AddSupportRequestDto request){
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setUserLogin(request.getUserLogin());
        supportRequest.setMessage(request.getText());
        supportRequestRepository.save(supportRequest);

        return new Response("Запрос отправлен!");
    }

    public List<SupportRequest> getAllRequests() {
        return supportRequestRepository.findAll();
    }

    public List<SupportRequest> getUserRequest(AddSupportRequestDto request){
        return  supportRequestRepository.findByUserLogin(request.getUserLogin());
    }



}

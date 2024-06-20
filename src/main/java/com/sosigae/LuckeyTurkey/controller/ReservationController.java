package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String showReservationPage(Model model) {
        model.addAttribute("searchResults", new ArrayList<>());
        return "reservation/reservation";
    }

    @GetMapping("/search")
    public String searchHospitalsAndDoctors(@RequestParam String type, @RequestParam String query, Model model) {
        if (type.equals("hospital")) {
            List<Hospital> hospitals = hospitalService.searchHospitalsByName(query);
            model.addAttribute("hospitals", hospitals);
        } else if (type.equals("doctor")) {
            List<Doctor> doctors = hospitalService.findDoctorsByDoctorName(query);
            model.addAttribute("doctors", doctors);
        }
        return "reservation/reservation";
    }

//    @PostMapping("/add")
//    public void addReservation(@ModelAttribute Reservation reservation) {
//        reservationService.addReservation(reservation);
//    }
@PostMapping("/add")
public String addReservation(@ModelAttribute("reservation") Reservation reservation, HttpSession session, Model model) {
    int userId = (int) session.getAttribute("userId");

//    if (userId == null) {
//        // 사용자 인증 실패 시 처리
//        model.addAttribute("error", "사용자 인증 실패");
//        return "error-page"; // 사용자 정의 에러 페이지로 리다이렉트 혹은 반환
//    }

    reservation.setUserId(userId);

    try {
        // 예약 추가 로직
        // 예약 서비스를 호출하여 예약을 추가하고 성공 페이지로 이동
        return "redirect:/reservation/reservation";
    } catch (Exception e) {
        // 예약 실패 시 처리
        model.addAttribute("error", "예약 등록 중 오류가 발생했습니다.");
        return "error-page"; // 사용자 정의 에러 페이지로 리다이렉트 혹은 반환
    }
}
}

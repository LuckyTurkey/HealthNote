package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReservationService;
import com.sosigae.LuckeyTurkey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
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
    @Autowired
    private UserService userService;

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

    @GetMapping("/hospitalId={hospitalId}")
    public String showReservationTimes(@PathVariable("hospitalId") int hospitalId,
                                       @RequestParam("reservationDate") String reservationDate,
                                       Model model) {
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        model.addAttribute("hospital", hospital);
        model.addAttribute("reservationDate", reservationDate);
        return "reservation/reservationCreate"; // 예약 시간 페이지
    }

    @PostMapping("/add")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation,
                                 @RequestParam("hospitalId") int hospitalId, HttpSession session, Model model) {
        try {
            String id =  (String)session.getAttribute("id");

            if (id == null) {
                model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
                return "redirect:/user/login"; // 사용자 정보가 없을 경우 처리
            }
            int userId = userService.findByUserId(id).getUser_id();


            // 예약 정보 설정
            LocalDateTime now = LocalDateTime.now();
            reservation.setCreatedAt(now);
            reservation.setUserId(userId);
            reservation.setHospitalId(hospitalId);
            reservation.setDoctorId(1); // 예약할 의사의 ID 설정
            reservation.setReservationDate(reservation.getReservationDate());
            reservation.setReservationTime(reservation.getReservationTime());

            // 예약 서비스 호출
            reservationService.addReservation(reservation);

            // 예약 성공 시 리다이렉트
            return "redirect:/reservation";
        } catch (Exception e) {
            // 예약 실패 시 처리
            model.addAttribute("error", "예약 등록 중 오류가 발생했습니다.");
            return "redirect:/user/login"; // 예약 실패 시 로그인 페이지로 리다이렉트
        }
    }


    @GetMapping("/success")
    public String successReservation(Model model) {
        return "reservation/success";
    }
}

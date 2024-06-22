package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.repository.ReservationRepository;
import com.sosigae.LuckeyTurkey.service.DoctorService;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReservationService;
import com.sosigae.LuckeyTurkey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private ReservationRepository reservationRepository;

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
        Hospital hospital = hospitalService.getHospitalId(hospitalId);
        model.addAttribute("hospital", hospital);
        model.addAttribute("reservationDate", reservationDate);
        return "reservation/reservationCreate"; // 예약 시간 페이지
    }
    //나의 예약
    @GetMapping("/my/{reservationId}")
    public String showMyReservation(@PathVariable("reservationId") int reservationId,
                                       Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        Hospital hospital = hospitalService.getHospitalId(reservation.getHospitalId());
        model.addAttribute("hospital", hospital);
        model.addAttribute("reservationDate", reservation.getReservationDate());
        model.addAttribute("reservationTime", reservation.getReservationTime());
        return "reservation/myReservationsDetail";
    }


    @PostMapping("/add")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation,
                                 @RequestParam("hospitalId") int hospitalId, HttpSession session, Model model) {
        try {
            String id =  (String)session.getAttribute("id");
            if (id == null) {
                model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
                return "redirect:/user/selectLogin"; // user 없을때
            }

            User user = userService.findUserById(id);
            int userId = user.getUserId();

            LocalDateTime now = LocalDateTime.now();
            reservation.setCreatedAt(now);
            reservation.setUserId(userId);
            reservation.setHospitalId(hospitalId);
            reservation.setDoctorId(1); // 예약할 의사의 ID 설정
            reservation.setReservationDate(reservation.getReservationDate());
            reservation.setReservationTime(reservation.getReservationTime());

            reservationService.addReservation(reservation);

            return "redirect:/reservation/success/" + reservation.getReservationId();
        } catch (Exception e) {
            model.addAttribute("error", "예약 등록 중 오류가 발생했습니다.");
            return "redirect:/user/selectLogin";
        }
    }

    @GetMapping("/available-times")
    @ResponseBody
    public List<String> getAvailableTimes(@RequestParam("hospitalId") int hospitalId, @RequestParam("reservationDate") String reservationDate) {
        return reservationService.getReservedTimes(hospitalId, reservationDate);
    }

    @GetMapping("/success/{reservationId}")
    public String successReservation(@PathVariable("reservationId") int reservationId, Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        Hospital hospital = hospitalService.getHospitalId(reservation.getHospitalId());

        model.addAttribute("reservation", reservation);
        model.addAttribute("hospitalName", hospital.getName());
        model.addAttribute("hospitalAddress", hospital.getAddress());
        return "reservation/success";
    }

    @GetMapping("/my")
    public String showMyReservations(HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");
        if (id == null) {
            model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
            return "redirect:/user/login";
        }

        User user = userService.findUserById(id);
        int userId = user.getUserId();

        List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
        model.addAttribute("reservations", reservations);
        return "reservation/myReservations";
    }

    @PostMapping("/update")
    public String updateReservation(@RequestParam("reservationId") int reservationId,
                                    @RequestParam("reservationTime") String reservationTime) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        reservation.setReservationTime(reservationTime);
        reservationService.updateReservation(reservation);
        return "redirect:/reservation/success/" + reservation.getReservationId();
    }

    @DeleteMapping("/delete/{reservationId}")
    @ResponseBody
    public ResponseEntity<Void> deleteReservation(@PathVariable int reservationId, RedirectAttributes redirectAttributes) {
        reservationService.deleteReservation(reservationId);

        redirectAttributes.addFlashAttribute("message", "예약이 삭제되었습니다.");

        return ResponseEntity.ok().build();
    }
}
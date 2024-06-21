package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.UserMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.service.*;

import com.sosigae.LuckeyTurkey.domain.Review;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.HospitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;


    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    // 병원 상세 조회
    @GetMapping("/hospital/{hospitalId}")
    public String getHospitalDetail(@PathVariable int hospitalId, Model model, HttpSession session) {
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId);
        List<Doctor> doctors = hospitalService.getDocInfoList(hospitalId);
        List<Review> reviews = reviewService.getReviewByHospitalId(hospitalId);

        for (Review r : reviews) {
            // pk로 유저 찾기
            User user = userService.findUserByUserId(r.getUserId());

            if (user != null) {
                r.setUserName(user.getName());
            }
            System.out.println("각 리뷰 아이디 : " + r.getUserId());
            System.out.println("각 리뷰 쓴 사람 이름 : "  + r.getUserName());
        }

        model.addAttribute("hospital", hospital);
        model.addAttribute("doctors", doctors);
        model.addAttribute("reviews", reviews);

        // 세션 아이디
        String sessionId = (String) session.getAttribute("id");
        System.out.println("세션 아이디: " + sessionId);

        // id로 유저 찾기
        User sessionUser = userService.findUserById(sessionId);

        if (sessionUser != null) {
            System.out.println("세션 유저 정보 : " + sessionUser.getName() + " "
                    + sessionUser.getId() + " " + sessionUser.getUserId());
            model.addAttribute("userId", sessionUser.getUserId());
        }

        return "hospital/detail";
    }


    // 병원 검색
    @GetMapping("/reservation/search/hospital")
    public List<Hospital> searchHospitals(@RequestParam String name) {
        return hospitalService.searchHospitalsByName(name);
    }

    // 스케줄 리스트 출력
    @GetMapping("/hospital/{hospitalId}/reservationList")
    public String getHospitalReservationList(@PathVariable int hospitalId, Model model) {
        List <Reservation> reservations = reservationService.getReservationByHospitalId(hospitalId);
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId);
        List<Doctor> doctors = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for(Reservation reservation : reservations) {
            int doctorId = reservation.getDoctorId();
            Doctor doctor = doctorService.findDoctorByDoctorId(doctorId);
            doctors.add(doctor);

            int userId = reservation.getUserId();
            User user = userService.findUserByUserId(userId);
            users.add(user);
        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("hospital", hospital);
        model.addAttribute("doctors", doctors);
        model.addAttribute("users", users);

        return "/hospital/reservationList";
    }

    // 스케출 추가 폼 출력
    @GetMapping("/hospital/{hospitalId}/newReservation")
    public String showReservationForm(@PathVariable int hospitalId, Model model) {
        model.addAttribute("reservation", new Reservation());
        return "/hospital/addReservation";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    // 스케줄 폼 제출
    @PostMapping("/hospital/{hospitalId}/submitReservation")
    public String submitReservation(@Valid @PathVariable int hospitalId,
                                    @ModelAttribute("reservation") Reservation reservation,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "/hospital/addReservation"; // 유효성 검사 에러 시 다시 폼을 보여줌
        }

        // 의사 ID가 제대로 전달되었는지 확인
        System.out.println("의사 ID 확인 : " + reservation.getDoctorId());

        // 사용자 정보 설정
        User user = userService.findUserByNameAndPhone(reservation.getName(), reservation.getPhone());
        if (user == null) {
            model.addAttribute("errorMessage", "사용자 정보를 찾을 수 없습니다.");
            return "/hospital/addReservation";
        }
        reservation.setUserId(user.getUserId());

        // 병원 ID 설정
        reservation.setHospitalId(hospitalId);

        System.out.println("---------------");
        System.out.println("doctorId 확인 : " + reservation.getDoctorId());
        System.out.println("hospitalId 확인 : " + reservation.getHospitalId());
        System.out.println("---------------");

        // 예약 서비스를 통해 예약 추가
        reservationService.addReservation(reservation);

        // 리다이렉트 경로 설정
        return "redirect:/hospital/" + hospitalId + "/reservationList";
    }


    @PostMapping("/hospital/{hospitalId}/searchDoctor")
    public String searchDoctor(
            @RequestParam("searchText") String searchText,
            @PathVariable int hospitalId,
            Model model) {

        List<Doctor> doctors;

        if (!searchText.isEmpty() && hospitalId != 0) {
            doctors = hospitalService.searchDoctorByNameAndHospitalId(searchText, hospitalId);
            if (doctors.isEmpty()) {
                doctors = hospitalService.searchDoctorByDepartmentAndHospitalId(searchText, hospitalId);
            }
        } else {
            doctors = new ArrayList<>();
        }
        model.addAttribute("doctors", doctors);
        model.addAttribute("reservation", new Reservation());
        return "/hospital/addReservation";
    }

}

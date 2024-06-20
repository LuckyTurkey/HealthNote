package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.UserMapper;
import com.sosigae.LuckeyTurkey.domain.Doctor;
import com.sosigae.LuckeyTurkey.domain.Hospital;
<<<<<<< HEAD
import com.sosigae.LuckeyTurkey.domain.Reservation;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReservationService;
=======
import com.sosigae.LuckeyTurkey.domain.Review;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReviewService;
>>>>>>> c6be3583038ccb90de0f75f5b6aad54031693a53
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private ReservationService reservationService;

    private ReviewService reviewService;

    @Autowired
    private UserMapper userMapper;


    // 병원 상세 조회
    @GetMapping("/hospital/{hospitalId}")
    public String getHospitalDetail(@PathVariable int hospitalId, Model model) {
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId);
        List<Doctor> doctors = hospitalService.getDocInfoList(hospitalId);
        List<Review> reviews = reviewService.getReviewByHospitalId(hospitalId);

        for (Review r : reviews){
            User user = userMapper.findByUser_Id(r.getUserId());

            if(user != null){
                r.setUserName(user.getName());
            }
        }
        model.addAttribute("hospital", hospital);
        model.addAttribute("doctors", doctors);
        model.addAttribute("reviews", reviews);
        return "hospital/detail";
    }

    @GetMapping("/reservation/search/hospital")
    public List<Hospital> searchHospitals(@RequestParam String name) {
        return hospitalService.searchHospitalsByName(name);
    }

    // 스케줄 리스트 출력
    @GetMapping("/hospital/{hospitalId}/reservationList")
    public String getHospitalReservationList(@PathVariable int hospitalId, Model model) {
        List <Reservation> reservations = reservationService.getReservationByHospitalId(hospitalId);
        Hospital hospital = hospitalService.getHospitalInfo();
        model.addAttribute("reservations", reservations);

        return "/hospital/reservationList";
    }

    @GetMapping("/hospital/{hospitalId}/newReservation")
    public String showReservationForm(@PathVariable int hospitalId, Model model) {
        List <Reservation> reservations = reservationService.getReservationByHospitalId(hospitalId);
        model.addAttribute("reservations", reservations);
        return "/hospital/reservationList";
    }
}

package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.UserMapper;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.Review;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private UserMapper userMapper;

    // 리뷰 폼 보여주기
    @GetMapping("/review/form")
    public String reviewForm(@RequestParam("hospitalId") int hospitalId, Model model) {
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId); // 병원 정보를 가져옴
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("hospitalName", hospital.getName());
        return "review/reviewForm";
    }

    // 리뷰 제출
    @PostMapping("/review/submit")
    public String submitReview(@RequestParam int rating,
                               @RequestParam String comments,
                               @RequestParam int hospitalId,
                               @RequestParam("imageUpload") MultipartFile imageUpload,
                               HttpSession session) {

        System.out.println("ImageUpload: " + (imageUpload != null ? imageUpload.getOriginalFilename() : "null"));
        System.out.println("Session ID: " + session.getAttribute("id"));

        // 이미지 업로드
        String photoFileName = null;

        if (imageUpload != null && !imageUpload.isEmpty()) {
            try {
                String directory = "src/main/resources/static/images/";
                byte[] bytes = imageUpload.getBytes();
                Path path = Paths.get(directory + imageUpload.getOriginalFilename());
                Files.write(path, bytes);
                photoFileName = imageUpload.getOriginalFilename();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // id -> user_id 조회하기
        String id = (String) session.getAttribute("id");
        if (id == null) {
            return "redirect:/user/login";
        }

        User user = userMapper.findByUserId(id);
        if (user == null) {
            return "redirect:/user/login";
        }
        int userId = user.getUserId();

        Review review = new Review();
        review.setUserId(userId);
        review.setHospitalId(hospitalId);
        review.setScore((float) rating);
        review.setContent(comments);
        review.setPhoto(photoFileName);

        reviewService.saveReview(review);

        return "redirect:/hospital/" + hospitalId;
    }

    // 리뷰 수정
    @GetMapping("/review/edit")
    public String editReviewForm(@RequestParam int reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return "redirect:/error";
        }
        model.addAttribute("review", review);
        return "review/editReviewForm";
    }


    // 리뷰 삭제
}
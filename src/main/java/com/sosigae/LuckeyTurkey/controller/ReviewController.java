package com.sosigae.LuckeyTurkey.controller;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.UserMapper;
import com.sosigae.LuckeyTurkey.domain.Hospital;
import com.sosigae.LuckeyTurkey.domain.Review;
import com.sosigae.LuckeyTurkey.domain.User;
import com.sosigae.LuckeyTurkey.service.HospitalService;
import com.sosigae.LuckeyTurkey.service.ReviewService;
import com.sosigae.LuckeyTurkey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private UserService userService;

    // 리뷰 폼 보여주기
    @GetMapping("/review/form")
    public String reviewForm(@RequestParam("hospitalId") int hospitalId, Model model) {
        Hospital hospital = hospitalService.getHospitalInfo(hospitalId);
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("hospitalName", hospital.getName());
        return "review/reviewForm";
    }

    // 리뷰 저장
    @PostMapping("/review/submit")
    public String submitReview(@RequestParam int rating,
                               @RequestParam String comments,
                               @RequestParam int hospitalId,
                               @RequestParam("imageUpload") MultipartFile imageUpload,
                               HttpSession session) {

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

        // 입력 id -> (pk)user_id 조회하기
        String id = (String) session.getAttribute("id");
        System.out.println("세션 아이디 : " + id);
        if (id == null) {
            return "redirect:/user/login";
        }

        User user = userService.findUserById(id);
        if (user == null) {
            System.out.println("-- 해당 사용자 없음 --");
            return "redirect:/user/login";
        }
        Integer userId = user.getUserId();
        System.out.println("세션 pk id :" + userId );

        Review review = new Review();
        review.setUserId(userId);
        review.setHospitalId(hospitalId);
        review.setScore((float) rating);
        review.setContent(comments);
        review.setPhoto(photoFileName);

        reviewService.saveReview(review);

        return "redirect:/hospital/" + hospitalId;
    }

    // 리뷰 수정 폼 보여주기
    @GetMapping("/review/edit/{reviewId}")
    public String showEditReviewForm(@PathVariable int reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return "redirect:/error";
        }
        model.addAttribute("review", review);
        model.addAttribute("hospitalId", review.getHospitalId());
        return "review/editReviewForm";
    }

    // 리뷰 수정
    @PostMapping("/review/update")
    public String updateReview(@RequestParam int reviewId,
                               @RequestParam int rating,
                               @RequestParam String comments,
                               @RequestParam int hospitalId,
                               @RequestParam("imageUpload") MultipartFile imageUpload) {

        Review review = reviewService.getReviewById(reviewId);
        if (review == null) {
            return "redirect:/error";
        }

        // 이미지 업로드 로직
        String photoFileName = review.getPhoto();
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

        review.setScore((float) rating);
        review.setContent(comments);
        review.setPhoto(photoFileName);

        reviewService.saveReview(review);

        return "redirect:/hospital/" + hospitalId;
    }

    // 리뷰 삭제
    @PostMapping("/review/delete")
    public String deleteReview(@RequestParam int reviewId, @RequestParam int hospitalId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/hospital/" + hospitalId;
    }
}

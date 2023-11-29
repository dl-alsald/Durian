package com.durianfirst.namu.controller;

import com.durianfirst.namu.entity.Member;
import com.durianfirst.namu.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@Log4j2
public class MainController {

    @Autowired
    private MemberRepository memberRepository;

    /*
    * @PreAuthorize/@PostAuthorize 접근 제한 표현식
    * authenticated() : 인증된 사용자들만 허용
    * permitAll() : 모두 허용
    * anonymous() : 익명의 사용자 허용
    * hasRole(~) : 특정한 권한이 있는 사용자 허용
    * hasAnyRole(~) : 여러 권한 중 하나만 존재해도 허용
    * */

    @GetMapping("/application-chat")
    public void application_chat(){}

    @GetMapping("/application-checkout")
    public void application_checkout(){}

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/application-email")
    public void application_email(){}

    @GetMapping("/application-gallery")
    public void application_gallery(){}

    @GetMapping("/auth-forgot-password")
    public void auth_forget_password(){}

    @GetMapping("/auth-login")
    public void auth_login(){}

    @GetMapping("/auth-register")
    public void auth_register(){}

    @GetMapping("/error-403")
    public void error_403(){}

    @GetMapping("/error-404")
    public void error_404(){}

    @GetMapping("/error-500")
    public void error_500(){}

    @GetMapping("/extra-component-avatar")
    public void extra_component_avatar(){}

    @GetMapping("/extra-component-divider")
    public void extra_component_divider(){}

    @GetMapping("/extra-component-rating")
    public void extra_component_rating(){}

    @GetMapping("/extra-component-sweetalert")
    public void extra_component_sweetalert(){}

    @GetMapping("/extra-component-toastify")
    public void extra_component_toastify(){}

    @GetMapping("/form-editor-ckeditor")
    public void form_editor_ckeditor(){}

    @GetMapping("/form-editor-quill")
    public void form_editor_quill(){}

    @GetMapping("/form-editor-summernote")
    public void form_editor_summernote(){}

    @GetMapping("/form-editor-tinymce")
    public void form_editor_tinymce(){}

    @GetMapping("/form-element-checkbox")
    public void form_editor_checkbox(){}

    @GetMapping("/form-element-input")
    public void form_editor_input(){}

    @GetMapping("/form-element-input-group")
    public void form_editor_input_group(){}

    @GetMapping("/form-element-radio")
    public void form_editor_radio(){}

    @GetMapping("/form-element-select")
    public void form_editor_select(){}

    @GetMapping("/form-element-textarea")
    public void form_editor_textarea(){}

    @GetMapping("/form-layout")
    public void form_editor_layout(){}

    @GetMapping({"/index","/"})
    public String index(Principal principal, Model model){
        //principal로 로그인 정보 가져옴
        // model로 index로 넘겨줌
        if (principal != null) {

        String mid = principal.getName();                   //mid에 로그인 정보를 받음
        Member member = memberRepository.findBymid(mid);    //findbymid로 유저 정보 찾아서 member에 저장

        log.info("유저 아이디 : " + principal.getName());

        model.addAttribute("member",member);    //model로 member에 담긴 정보를 인덱스 프론트에 넘김

        return "/admin/index";
        } else {
        // 로그인이 안 된 경우 로그인 없는 뷰로 이동
        return "/member/login";
    }
    }

    @GetMapping("/layout-default")
    public void layout_default(){}

    @GetMapping("/layout-horizontal")
    public void layout_horizontal(){}

    @GetMapping("/layout-vertical-1-column")
    public void layout_vertical_1_column(){}

    @GetMapping("/layout-vertical-navbar")
    public void layout_vertical_navbar(){}

    @GetMapping("/sidebar-items")
    public void sidebar_items(){}

    @GetMapping("/table")
    public void table(){}

    @GetMapping("/table-datatable")
    public void table_datatable(){}

    @GetMapping("/ui-chart-apexcharts")
    public void ui_chart_apexcharts(){}

    @GetMapping("/ui-chart-chartjs")
    public void ui_chart_chartjs(){}

    @GetMapping("/ui-file-uploader")
    public void ui_file_uploader(){}

    @GetMapping("/ui-icons-bootstrap-icons")
    public void ui_icons_bootstrap_icons(){}

    @GetMapping("/ui-icons-dripicons")
    public void ui_icons_dripicons(){}

    @GetMapping("/ui-icons-fontawesome")
    public void ui_icons_fontawesome(){}

    @GetMapping("/ui-map-google-map")
    public void ui_map_google_map(){}

    @GetMapping("/ui-map-jsvectormap")
    public void ui_map_jsvectormap(){}

    @GetMapping("/ui-widgets-chatbox")
    public void ui_widgets_chatbox(){}

    @GetMapping("/ui-widgets-pricing")
    public void ui_widgets_pricing(){}

    @GetMapping("/ui-widgets-todolist")
    public void ui_widgets_todolist(){}

}

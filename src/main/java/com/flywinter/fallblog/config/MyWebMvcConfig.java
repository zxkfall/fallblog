package com.flywinter.fallblog.config;

import com.flywinter.fallblog.entity.TWebViewPeople;
import com.flywinter.fallblog.mapper.TWebInfoMapper;
import com.flywinter.fallblog.mapper.TWebViewPeopleMapper;
import com.flywinter.fallblog.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User:Zhang Xingkun
 * Date:2021/8/22 18:53
 * Description:
 */
//①implements WebMvcConfigurer（官方推荐）

//        ②extends WebMvcConfigurationSupport 这种会替换所有配置
@Slf4j
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    TWebViewPeopleMapper webViewPeopleMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        String device = request.getHeader("User-Agent");
                        String ip = IpUtil.getIp(request);
                        TWebViewPeople webViewPeople = new TWebViewPeople();
                        webViewPeople.setCreateTime(LocalDateTime.now());
                        webViewPeople.setUpdateTime(LocalDateTime.now());
                        webViewPeople.setDevice(device);
                        webViewPeople.setIp(ip);
                        int insert = webViewPeopleMapper.insert(webViewPeople);
                        log.debug("insert:" + insert);
                        log.debug("True ip:" + ip);
                        log.debug("Device:" + device);
                        log.debug("preHandler");
                        return HandlerInterceptor.super.preHandle(request, response, handler);
                    }

                    @Override
                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                        Integer viewPeople = webViewPeopleMapper.selectCount(null);
                        ModelMap modelMap = modelAndView.getModelMap();
                        modelMap.addAttribute("globalviewpeople", viewPeople);
                        log.debug("postHandle");
//                        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
                    }

                    @Override
                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                        log.debug("afterCompletion");
                        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
                    }
                }).addPathPatterns("/", "/about", "/archive", "/category", "/category/**","/article/**")
                .excludePathPatterns("/admin/**", "/admin", "/css/**",
                        "/js/**", "/image/**", "/img/**", "/font/**", "/lib/**",
                        "/plugin/**", "/scss/**",
                        "/vendors/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
//    只能在程序第一次运行的时候执行，适合添加静态常量
//    @Autowired
//    @Qualifier("thymeleafViewResolver")
//    private ThymeleafViewResolver thymeleafViewResolver;
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        Integer viewPeople = webViewPeopleMapper.selectCount(null);
//        if (thymeleafViewResolver != null) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("globalviewpeople", viewPeople);
//            thymeleafViewResolver.setStaticVariables(map);
//        }
//        WebMvcConfigurer.super.configureViewResolvers(registry);
//    }
}

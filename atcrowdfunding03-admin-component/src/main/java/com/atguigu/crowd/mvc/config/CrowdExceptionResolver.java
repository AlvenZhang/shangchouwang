package com.atguigu.crowd.mvc.config;


import com.atguigu.crowd.exception.AccessForbiddenException;
import com.atguigu.crowd.exception.LoginAcctAlreadyInUseException;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@ControllerAdvice表示当前类是一个异常处理类，处理全局异常
@ControllerAdvice
public class CrowdExceptionResolver {

//    @ExceptionHandler(value = AccessForbiddenException.class)
//    public ModelAndView resolveAccessForbiddenException(
//
//            AccessForbiddenException exception,
//
//            HttpServletRequest request,
//
//            HttpServletResponse response
//            ) throws IOException {
//
//        System.out.println("访问到异常处理");
//
//        String viewName = "admin-login";
//
//        return commonResolveException(exception,request,response,viewName);
//    }

    /**
     * 注册失败，用户名已经存在，返回添加页面
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView resolveLoginAcctAlreadyInUseException(

            LoginAcctAlreadyInUseException exception,

            HttpServletRequest request,

            HttpServletResponse response
    ) throws IOException {

        String viewName = "admin-add";

        return commonResolveException(exception,request,response,viewName);
    }


    /**
     * 登录失败，返回登录页面
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(

            LoginFailedException exception,

            HttpServletRequest request,

            HttpServletResponse response
    ) throws IOException {


        String viewName = "admin-login";

        return commonResolveException(exception, request, response, viewName);
    }


//    @ExceptionHandler(value = NullPointerException.class)
//    public ModelAndView nullpointer(
//
//            NullPointerException exception,
//
//            HttpServletRequest request,
//
//            HttpServletResponse response
//        ) throws IOException {
//
//
//        return commonResolveException(exception,request,response,"system-error");
//
//    }


    /**
     * 通用异常处理方法
     *
     * @param exception springMVC捕捉到的异常对象
     * @param request   为了判断当前请求是否是ajax请求
     * @param response  为了能够将json字符串作为当前请求的响应数据返回给浏览器
     * @param viewName  指定要前往的视图的名称
     * @return
     * @throws IOException
     */
    private ModelAndView commonResolveException(

            Exception exception,

            HttpServletRequest request,

            HttpServletResponse response,

            String viewName
    ) throws IOException {

        //1、判断当前请求是否是ajax请求
        Boolean isAjax = CrowdUtil.judgeRequestType(request);

        //2、如果是ajax请求
        if (isAjax) {

            //3、从当前异常对象中获取异常信息
            String message = exception.getMessage();

            //4、创建ResultEntity
            ResultEntity<Object> failed = ResultEntity.failed(message);

            //5、创建Gson对象
            Gson gson = new Gson();

            //6、把resultEntity装换为json
            String jsonString = gson.toJson(failed);

            //7、把当前json数据作为请求的响应体数据返回给浏览器
            response.getWriter().write(jsonString);

            return null;
        }

        //9、创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        //10、将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);

        //11、设置目标视图的名称
        modelAndView.setViewName(viewName);

        //12、返回modelandview对象
        return modelAndView;


    }

}

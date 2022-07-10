package com.example.holdprojectsystem.controller;

import com.example.holdprojectsystem.entity.FlutterDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/Flutter")
@Api(value = "支撑项目展示接口", tags = "支撑项目展示接口" )
public class FlutterController {

    @RequestMapping(value="/flutterHomework", method = RequestMethod.GET)
    @ApiOperation(value = "获取点赞与评论数量", notes = "获取点赞与评论数量")
    public FlutterDemo getFlutter() {
        FlutterDemo flutterDemo = new FlutterDemo();
        flutterDemo.setLike(100);
        flutterDemo.setComment(50);
        return flutterDemo;
    }
}

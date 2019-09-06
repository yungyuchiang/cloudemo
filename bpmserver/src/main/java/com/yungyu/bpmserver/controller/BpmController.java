package com.yungyu.bpmserver.controller;

import com.yungyu.bpmserver.entity.Leave;
import com.yungyu.bpmserver.entity.ResultSet;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/bpm")
public class BpmController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/user")
    public ResultSet getUser(){
        ResultSet resultSet = new ResultSet();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "yungyu");
        map.put("age", 24);
        map.put("phone", "13802390423");
        resultSet.setCode(ResultSet.SUCCESS);
        resultSet.setMsg("请求成功");
        resultSet.setData(map);
        return resultSet;
    }

    @PostMapping("/post/leave")
    public ResultSet postLeave(@Valid @RequestBody Leave leave){
        ResultSet rs = new ResultSet();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("studentLeaveProcess");
        String id = processInstance.getId();
        System.out.println("流程id: " + id);
        System.out.println("流程: " + processInstance);
        return rs;
    }

    @PostMapping("/submit/{processId}")
    public ResultSet submit(@PathVariable(value = "processId")String processId, String isAllowed){
        ResultSet rs = new ResultSet();
        System.out.println(processId);
        return rs;
    }

}

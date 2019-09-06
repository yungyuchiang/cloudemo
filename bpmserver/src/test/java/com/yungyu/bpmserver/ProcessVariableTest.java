package com.yungyu.bpmserver;

import com.yungyu.bpmserver.entity.Student;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BpmApp.class)
public class ProcessVariableTest {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void start(){
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("studentLeaveProcess");
        System.out.println("流程实例ID:"+processInstance.getId());
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
    }

    @Test
    public void setVariablesValues(){
        String taskId = "25012";
        taskService.setVariable(taskId, "days", 2);
        taskService.setVariable(taskId, "date", new Date());
        taskService.setVariable(taskId, "reason", "发烧");
        Student s = new Student();
        s.setId(1);
        s.setName("张三");
        taskService.setVariable(taskId, "student", s);
    }

    @Test
    public void completeTask(){
        taskService.complete("7509");
    }

    @Test
    public void getVariablesValues(){
        String taskId = "30002";
        Integer days=(Integer) taskService.getVariable(taskId, "days");
        Date date=(Date) taskService.getVariable(taskId, "date");
        String reason=(String) taskService.getVariable(taskId, "reason");
        Student student=(Student) taskService.getVariable(taskId, "student");
        System.out.println("请假天数："+days);
        System.out.println("请假日期："+date);
        System.out.println("请假原因："+reason);
        System.out.println("请假对象："+student.getId()+","+student.getName());
    }

}

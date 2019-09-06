package com.yungyu.bpmserver;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BpmApp.class)
public class HelloWorldProcess {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void start(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess");
        System.out.println("流程实例ID:"+processInstance.getId());
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
    }

    @Test
    public void findTask(){
        List<Task> taskList = taskService.createTaskQuery() // 创建任务查询
                                .taskAssignee("java1234_小峰") // 指定某个人
                                .list();
        for(Task task:taskList){
            System.out.println("任务ID:"+task.getId());
            System.out.println("任务名称："+task.getName());
            System.out.println("任务创建时间："+task.getCreateTime());
            System.out.println("任务委派人："+task.getAssignee());
            System.out.println("流程实例ID:"+task.getProcessInstanceId());
        }
    }

    @Test
    public void completeTask(){
        taskService.complete("5012");
    }

    @Test
    public void getState(){
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("12501")
                .singleResult();
        if(instance != null){
            System.out.println("流程正在执行！");
            System.out.println("流程实例ID:"+instance.getId());
            System.out.println("流程定义ID:"+instance.getProcessDefinitionId());
        }else{
            System.out.println("流程已经执行结束！");
        }
    }

    /**
     * 获取流程图图片
     */
    @Test
    public void getTaskImage() throws FileNotFoundException {
        InputStream ips = repositoryService.getResourceAsStream("2501", "G:\\IDEAProjects\\cloudemo\\bpmserver\\target\\classes\\processes\\simple.oneTaskProcess.png");
        OutputStream ops = new FileOutputStream("D:/simple.oneTaskProcess.png");

        byte[] temp = new byte[2048];
        int length;
        try {
            while ((length = ips.read(temp)) != -1) {
                ops.write(temp, 0, length);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                ips.close();
                ops.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}

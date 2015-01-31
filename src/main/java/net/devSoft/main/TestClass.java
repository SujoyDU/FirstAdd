package net.devSoft.main;

import net.devSoft.controller.demoController;
import net.devSoft.dao.demoDao;
import net.devSoft.domain.demoDomain;
import net.devSoft.filter.demoFilter;
import net.devSoft.listener.demoListener;
import net.devSoft.profiler.demoProfile;
import net.devSoft.service.demoService;
import net.devSoft.util.demoUtil;

/**
 * Created by Sujoy on 1/29/2015.
 */
public class TestClass  {
    public static void main(String args[]) {
        System.out.println("Hello Github!!!");
        int testVar = 3;
        for(int i=0 ; i<3; i++) {
            testVar++;
        }
        System.out.println(testVar);
        boolean doesItWork=false;
        if(testVar==6){
            doesItWork = true;
        }
        if(doesItWork) System.out.println("git integration successfull!!");
        CallClass call = new demoController();
        call.callme();
        call = new demoDao();
        call.callme();
        call = new demoDomain();
        call.callme();
        call = new demoFilter();
        call.callme();
        call = new demoListener();
        call.callme();
        call = new demoProfile();
        call.callme();
        call = new demoService();
        call.callme();
        call = new demoUtil();
        call.callme();

    }




}

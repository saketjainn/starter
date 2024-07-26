package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MYAPI extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx=Vertx.vertx();
        vertx.deployVerticle(new MYAPI());
    }
    
    Router router =Router.router(vertx);

    @Override
    public void start() throws Exception {
            router.route().handler(BodyHandler.create());

        router.post("/user").handler(context->{
           String uname=context.getBodyAsJson().getString("uname");
           String password=context.getBodyAsJson().getString("password");
           int count=0;
           boolean flag=false;
        //    if(isPallindorm(uname,password,count)){
        //     count=
        //     flag=true;
        //    }

            count =isPallindorm(uname,password);
            if(count>0){
                flag=true;
            }
           if(flag){
            context.response().end("everythis is fine" + "no .pallin :" + count);
           }
           else{
            context.response().end("not a pallindromic substring");
           }

        });

        vertx.createHttpServer().requestHandler(router).listen(8085,res->{
            if(res.succeeded()){
                System.out.println("server on");
            }
            else{
                System.out.println(res.cause().getMessage());
            }
        });
    }

    private int isPallindorm(String uname, String password) {
       int l1=uname.length();
       int l2=password.length();
       
       int maxlength=0;
  
       for(int i=0;i<l1;i++){
        for(int j=i+1;j<=l1;j++){ //Sir i was commiting mistake here in condition checking i have used (j<l2) but actually it should be(j<=l1)
            String sub=uname.substring(i,j);
         //  System.out.println(j);

            if(isPallin(sub) && password.contains(sub)){
               // System.out.println("hehe");
                int count=0;
                count=sub.length();
                if(maxlength < count){
                 maxlength=count;
               // return count;
                }
            }
        }
       }  
     
        return maxlength;
     
       

    }

    private boolean isPallin(String sub) {
       int left=0;
       int right=sub.length()-1;

       while(left<right){
        if(sub.charAt(left) != sub.charAt(right)){
            return false;
        }
        left++;
        right--;
       }
       return true;
    }
}

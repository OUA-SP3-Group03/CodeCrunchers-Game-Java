package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.HttpService;

import java.awt.*;
import java.io.IOException;

public class HttpServiceProvider extends Provider {

    private HttpService httpService;
    private App app;

    @Override
    public void boot(App app) {
        //create the app variable
        this.app = app;

        //create new HttpService
        this.httpService = new HttpService();
    }

    @Override
    public boolean performTick() {
        return false;
    }

    @Override
    public boolean performRender() {
        return false;
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void tick() {

    }

    public String login(String email, String password){

        String data = "type=game&email="+email+"&password="+password;
        String response;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl() + "/auth/login", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

        return response;

    }

    public String check(String token){

        String data = "type=game&token="+token;
        String response;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl() + "/auth/check", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

        return response;
    }

    public String logout(String token){

        String data = "type=game&token="+token;
        String response;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl()+"/auth/logout", data);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }
        return response;
    }
}

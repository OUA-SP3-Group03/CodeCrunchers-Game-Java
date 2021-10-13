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

        //DEBUG!
        System.out.println(this.check("b78923382d597d6e921c050a602808825f2b8df4068b4d1fbb02fe3ae23232ca90277a1f7466863d8e31bc55905d6674b74f"));

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
            response = this.httpService.postRequest(this.app.apiUrl() + "/auth/login", data);
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
            response = this.httpService.postRequest(this.app.apiUrl() + "/auth/check", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

        return response;

    }
}

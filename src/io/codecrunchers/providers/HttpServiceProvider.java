package io.codecrunchers.providers;

import io.codecrunchers.core.Provider;
import io.codecrunchers.facades.App;
import io.codecrunchers.service.HttpService;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HttpServiceProvider extends Provider {

    private HttpService httpService;
    private App app;
    private String[] currentUser;

    @Override
    public void boot(App app) {
        //create the app variable
        this.app = app;

        //create new HttpService
        this.httpService = new HttpService(app);

        this.booted = true;
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

    public boolean login(String email, String password){

        String data = "type=game&email="+email+"&password="+password;
        String response;
        boolean outcome = false;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl() + "/auth/login", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

        JSONObject result = new JSONObject(response);

        if((boolean)result.get("success")){
            this.saveToken(result.getString("token"));
            this.currentUser = this.getUserInfo();
            outcome = true;
        }

        return outcome;

    }

    public boolean check(){
        String data = "type=game&token="+this.loadToken();
        String response;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl() + "/auth/check", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

        JSONObject result = new JSONObject(response);

        if((boolean) result.get("success")){
            this.currentUser = this.getUserInfo();
        }

        return (boolean) result.get("success");
    }

    private void saveToken(String token){
        try {
            File session = new File("session.txt");
            session.createNewFile();

                FileWriter fileWriter = new FileWriter("session.txt");
                fileWriter.write(token);
                fileWriter.close();

            } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String loadToken(){

        String token = "";

        try {
            File session = new File("session.txt");
            session.createNewFile();

            Scanner scanner = new Scanner(session);
            while(scanner.hasNextLine()){
                token = scanner.nextLine();
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;

    }

    public String[] getUserInfo(){

        String data = "type=game&token="+this.loadToken();
        String response;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl() + "/auth/get", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

        JSONObject result = new JSONObject(response);
        String[] userData = new String[0];


        if((boolean)result.get("success")){
            userData  = new String[5];
            userData[0] = result.getString("username");
            userData[1] = result.getString("user_id");
            userData[2] = result.getString("first_name");
            userData[3] = result.getString("last_name");
            userData[4] = result.getString("email");
        }

        return userData;
    }

    public void saveScore(int score){
        int user_id = Integer.parseInt(this.currentUser[1]);
        String data = "user_id="+user_id+"&score="+score+"&level=random_gen";
        String response;

        try {
            response = this.httpService.postRequest(this.app.config().apiUrl() + "/score/add", data);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response = "no response from http request";
        }

    }

}

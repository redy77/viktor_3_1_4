package com.viktor.lesson_314;

import com.viktor.lesson_314.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Main {

    public static String URL = "http://91.241.64.178:7081/api/users";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        HttpHeaders headers = callGetAll();

        putUser(headers);
        changeUser(headers);
        deleteUser(headers);
    }

    private static HttpHeaders callGetAll(){
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        String sessionId = response.getHeaders().getFirst("Set-Cookie").replace("; Path=/; HttpOnly", "");
        headers.set("Cookie", sessionId);
        System.out.println(response);
        return headers;
    }

    private static void putUser(HttpHeaders headers){
        User user = new User(3L, "James", "Brown", (byte) 32);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(response);
    }

    private static void changeUser(HttpHeaders headers){
        User user = new User(3L, "Thomas", "Shelby", (byte) 32);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(response);

    }

    private static void deleteUser(HttpHeaders headers){
        HttpEntity<User> entity = new HttpEntity<>(headers);
        URL += "/3";
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.DELETE, entity, String.class);
        System.out.println(response);

    }
}

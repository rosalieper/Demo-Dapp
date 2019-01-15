package com.example.rosalie.myapplication.api;

public class ApiUrl {

    private static final String ROOT_URL = "http://192.168.8.103:3000/";

    public static String amount;
    public static String adress;
    public static final String URL_GENERATE = ROOT_URL + "create_account";
    public static final String URL_SEND = ROOT_URL + "send";
    public static final String URL_GET_KEY = ROOT_URL + "recieve";
}
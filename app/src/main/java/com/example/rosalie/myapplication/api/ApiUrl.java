package com.example.rosalie.myapplication.api;

public class ApiUrl {

    private static final String ROOT_URL = "http://192.168.8.104:3000/";

    //global variable for the send option
    public static String amount;
    public static String adress;
    //API requests
    public static final String URL_GENERATE = ROOT_URL + "create_account";
    public static final String URL_SEND = ROOT_URL + "send";
    public static final String URL_GET_KEY = ROOT_URL + "recieve";
}